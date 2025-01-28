/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proy121verano;

/**
 *
 * @author FRANKLIN
 */
import java.sql.*;
public class GestorRestaurante {
    private static final String URL = "jdbc:mysql://sql10.freesqldatabase.com:3306/sql10759871";
    private static final String USER = "sql10759871";
    private static final String PASSWORD = "DVC3dgtF3E";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
        
    }
    public void MostrarDetallesPedido() {
        String query = "SELECT * FROM pedidos"; // Asegúrate de que el nombre de la tabla sea correcto
        try (Connection conexion = getConnection();
             Statement statement = conexion.createStatement();
             ResultSet rs = statement.executeQuery(query)) {

            System.out.println("Detalles de los pedidos:");
            while (rs.next()) {
                String id = rs.getString("id");
                String clienteId = rs.getString("cliente_id");
                double total = rs.getDouble("total");

                System.out.println("ID Pedido: " + id);
                System.out.println("ID Cliente: " + clienteId);
                System.out.println("Total: " + total);
                System.out.println("---------------------------");
            }

        } catch (SQLException e) {
            System.out.println("Error al mostrar los detalles de los pedidos: " + e.getMessage());
        }
    }

    private Connection conexion;

    // Constructor: Establece la conexión a la base de datos
    public GestorRestaurante() {
        try {
            conexion = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Conexión exitosa con la base de datos");
        } catch (SQLException e) {
            System.err.println("Error al conectar con la base de datos: " + e.getMessage());
        }
    }

    // Método para cerrar la conexión
    public void cerrarConexion() {
        try {
            if (conexion != null) {
                conexion.close();
                System.out.println("Conexión cerrada correctamente");
            }
        } catch (SQLException e) {
            System.err.println("Error al cerrar la conexión: " + e.getMessage());
        }
    }

    // Función para registrar un cliente
    public void registrarCliente(String nombre, String telefono) {
        String sql = "INSERT INTO clientes (nombre, telefono) VALUES (?, ?)";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setString(1, nombre);
            stmt.setString(2, telefono);
            stmt.executeUpdate();
            System.out.println("Cliente registrado exitosamente");
        } catch (SQLException e) {
            System.err.println("Error al registrar cliente: " + e.getMessage());
        }
    }

    // Función para mostrar todos los clientes
    public void mostrarClientes() {
        String sql = "SELECT * FROM clientes";
        try (Statement stmt = conexion.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id_cliente") +
                        ", Nombre: " + rs.getString("nombre") +
                        ", Teléfono: " + rs.getString("telefono"));
            }
        } catch (SQLException e) {
            System.err.println("Error al mostrar clientes: " + e.getMessage());
        }
    }

    // Función para registrar un plato
    public void registrarPlato(String nombre, double precio, String categoria) {
        String sql = "INSERT INTO platos (nombre, precio, categoria) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setString(1, nombre);
            stmt.setDouble(2, precio);
            stmt.setString(3, categoria);
            stmt.executeUpdate();
            System.out.println("Plato registrado exitosamente");
        } catch (SQLException e) {
            System.err.println("Error al registrar plato: " + e.getMessage());
        }
    }

    // Función para mostrar todos los platos
    public void mostrarPlatos() {
        String sql = "SELECT * FROM platos";
        try (Statement stmt = conexion.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id_plato") +
                        ", Nombre: " + rs.getString("nombre") +
                        ", Precio: " + rs.getDouble("precio") +
                        ", Categoría: " + rs.getString("categoria"));
            }
        } catch (SQLException e) {
            System.err.println("Error al mostrar platos: " + e.getMessage());
        }
    }

    // Función para registrar un pedido
    public void registrarPedido(int idCliente, Plato[] platos, int[] cantidades) {
        String sqlPedido = "INSERT INTO pedidos (id_cliente, total) VALUES (?, ?)";
        String sqlDetalle = "INSERT INTO detalle_pedido (id_pedido, id_plato, cantidad) VALUES (?, ?, ?)";

        try (PreparedStatement stmtPedido = conexion.prepareStatement(sqlPedido, Statement.RETURN_GENERATED_KEYS)) {
            // Calcular el total
            double total = 0;
            for (int i = 0; i < platos.length; i++) {
                total += platos[i].getPrecio() * cantidades[i];
            }

            // Registrar el pedido
            stmtPedido.setInt(1, idCliente);
            stmtPedido.setDouble(2, total);
            stmtPedido.executeUpdate();

            // Obtener el ID del pedido generado
            ResultSet rs = stmtPedido.getGeneratedKeys();
            if (rs.next()) {
                int idPedido = rs.getInt(1);

                // Registrar el detalle del pedido
                try (PreparedStatement stmtDetalle = conexion.prepareStatement(sqlDetalle)) {
                    for (int i = 0; i < platos.length; i++) {
                        stmtDetalle.setInt(1, idPedido);
                        stmtDetalle.setInt(2, platos[i].getIdPlato());
                        stmtDetalle.setInt(3, cantidades[i]);
                        stmtDetalle.executeUpdate();
                    }
                }
            }
            System.out.println("Pedido registrado exitosamente");
        } catch (SQLException e) {
            System.err.println("Error al registrar pedido: " + e.getMessage());
        }
    }

    // Función para mostrar todos los pedidos
    public void mostrarPedidos() {
        String sql = "SELECT p.id_pedido, c.nombre AS cliente, p.total " +
                     "FROM pedidos p JOIN clientes c ON p.id_cliente = c.id_cliente";
        try (Statement stmt = conexion.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                System.out.println("ID Pedido: " + rs.getInt("id_pedido") +
                        ", Cliente: " + rs.getString("cliente") +
                        ", Total: " + rs.getDouble("total"));
            }
        } catch (SQLException e) {
            System.err.println("Error al mostrar pedidos: " + e.getMessage());
        }
    }
}
