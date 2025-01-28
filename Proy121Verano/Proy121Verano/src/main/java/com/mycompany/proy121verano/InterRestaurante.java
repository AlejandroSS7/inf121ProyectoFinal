/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.mycompany.proy121verano;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.BorderFactory;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author FRANKLIN
 */
public class InterRestaurante extends javax.swing.JFrame {

    private JComboBox<String> comboBoxPlatos;
    private Connection connection;
    private JFrame frame;

    /**
     * Creates new form InterRestaurante
     */
    public InterRestaurante() {
        this.connection = connection;
        comboBoxPlatos = new JComboBox<>();
        init();
    }

    private void init() {
        frame = new JFrame("Gestión de Restaurante");
        frame.setSize(700, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Crear barra de menú
        JMenuBar menuBar = new JMenuBar();

        // Menú Cliente
        JMenu menuCliente = new JMenu("Cliente");
        JMenuItem itemCambiarNombreCliente = new JMenuItem("Cambiar Nombre de Cliente");
        JMenuItem itemVerCliente = new JMenuItem("Ver Clientes");
        JMenuItem itemEliminarCliente = new JMenuItem("Eliminar Cliente");
        JMenuItem itemBuscarCliente = new JMenuItem("Buscar Cliente");
        JMenuItem itemSalir = new JMenuItem("Salir");

        // Agregar acciones al menú Cliente
        itemCambiarNombreCliente.addActionListener(e -> registrarCliente());
        itemVerCliente.addActionListener(e -> verClientes());
        itemEliminarCliente.addActionListener(e -> eliminarCliente());
        itemBuscarCliente.addActionListener(e -> buscarCliente());
        itemSalir.addActionListener(e -> System.exit(0));

        // Añadir los ítems al menú Cliente
        menuCliente.add(itemCambiarNombreCliente);
        menuCliente.add(itemVerCliente);
        menuCliente.add(itemEliminarCliente);
        menuCliente.add(itemBuscarCliente);
        menuCliente.addSeparator();
        menuCliente.add(itemSalir);

        // Menú Empleado
        JMenu menuEmpleado = new JMenu("Empleado");
        JMenuItem itemCambiarNombreEmpleado = new JMenuItem("Cambiar Nombre de Empleado");
        JMenuItem itemVerEmpleado = new JMenuItem("Ver Empleados");
        JMenuItem itemEliminarEmpleado = new JMenuItem("Eliminar Empleado");
        JMenuItem itemBuscarEmpleado = new JMenuItem("Buscar Empleado");

        // Agregar acciones al menú Empleado
        itemCambiarNombreEmpleado.addActionListener(e -> registrarEmpleado());
        itemVerEmpleado.addActionListener(e -> verEmpleados());
        itemEliminarEmpleado.addActionListener(e -> eliminarEmpleado());
        itemBuscarEmpleado.addActionListener(e -> buscarEmpleado());

        // Añadir los ítems al menú Empleado
        menuEmpleado.add(itemCambiarNombreEmpleado);
        menuEmpleado.add(itemVerEmpleado);
        menuEmpleado.add(itemEliminarEmpleado);
        menuEmpleado.add(itemBuscarEmpleado);

        //menu Plato
        JMenu menuPlato = new JMenu("Plato");
        JMenuItem itemRegistrarPlato = new JMenuItem("Registrar Plato");
        JMenuItem itemVerPlato = new JMenuItem("Ver Platos");
        JMenuItem itemEliminarPlato = new JMenuItem("Eliminar Plato");
        JMenuItem itemBuscarPlato = new JMenuItem("Buscar Plato");

        // Agregar acciones al menú Plato
        itemRegistrarPlato.addActionListener(e -> registrarPlato());
        itemVerPlato.addActionListener(e -> verPlatos());
        itemEliminarPlato.addActionListener(e -> eliminarPlato());
        itemBuscarPlato.addActionListener(e -> buscarPlato());

        // Añadir los ítems al menú Plato
        menuPlato.add(itemRegistrarPlato);
        menuPlato.add(itemVerPlato);
        menuPlato.add(itemEliminarPlato);
        menuPlato.add(itemBuscarPlato);

        // Menú Mesa
        JMenu menuMesa = new JMenu("Mesa");
        JMenuItem itemCrearMesa = new JMenuItem("Crear Mesa");
        JMenuItem itemVerMesa = new JMenuItem("Ver Mesas");
        JMenuItem itemEliminarMesa = new JMenuItem("Eliminar Mesa");
        JMenuItem itemBuscarMesa = new JMenuItem("Buscar Mesa");

        // Agregar acciones al menú Mesa
        itemCrearMesa.addActionListener(e -> crearMesa());
        itemVerMesa.addActionListener(e -> verMesas());
        itemEliminarMesa.addActionListener(e -> eliminarMesa());
        itemBuscarMesa.addActionListener(e -> buscarMesa());

        // Añadir los ítems al menú Mesa
        menuMesa.add(itemCrearMesa);
        menuMesa.add(itemVerMesa);
        menuMesa.add(itemEliminarMesa);
        menuMesa.add(itemBuscarMesa);

        // Añadir menús al menú bar
        menuBar.add(menuPlato);
        menuBar.add(menuMesa);
        menuBar.add(menuEmpleado);
        menuBar.add(menuCliente);

        // Panel central con botones
        JPanel centralPanel = new JPanel();
        centralPanel.setLayout(new GridLayout(5, 2, 10, 10)); // 5 filas, 2 columnas con espaciado

        JButton btnCuentaTotalMesa = new JButton("mostrar Plato Principal");
        JButton btnAgregarDescuento = new JButton("enlistar Empleados con Mayor Sueldo");
        JButton btnListarPlatosMasVendidos = new JButton("Mostrar Pedidos Plato");
        JButton btnListarMesasDisponibles = new JButton("Mostrar Postres");

        btnCuentaTotalMesa.addActionListener(e -> mostrarPlatoCategoria());
        btnAgregarDescuento.addActionListener(e -> {
            int x = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el salario:"));
            enlistarEmpleadosSalarioMayor(x);
        });
        btnListarPlatosMasVendidos.addActionListener(e -> listarPedidosPlatos());
        btnListarMesasDisponibles.addActionListener(e -> mostrarPostres());

        centralPanel.add(btnCuentaTotalMesa);
        centralPanel.add(btnAgregarDescuento);
        centralPanel.add(btnListarPlatosMasVendidos);
        centralPanel.add(btnListarMesasDisponibles);

        // Contenedor principal
        JPanel container = new JPanel(new BorderLayout());
        container.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Márgenes de 20 píxeles
        container.add(centralPanel, BorderLayout.CENTER);

        // Añadir contenedor y menú bar al frame
        frame.add(container, BorderLayout.CENTER);
        frame.setJMenuBar(menuBar);
        frame.setVisible(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(InterRestaurante.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(InterRestaurante.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(InterRestaurante.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(InterRestaurante.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new InterRestaurante().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
    private void cambiarNombreCliente() {
        String idCliente = JOptionPane.showInputDialog("Ingrese el ID del Cliente:");
        String nuevoNombre = JOptionPane.showInputDialog("Ingrese el nuevo nombre:");

        String query = "UPDATE Cliente SET nombre = ? WHERE id_cliente = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, nuevoNombre);
            stmt.setString(2, idCliente);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, "Nombre actualizado correctamente.");
            } else {
                JOptionPane.showMessageDialog(null, "Cliente no encontrado.");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al actualizar el nombre: " + e.getMessage());
        }
    }

    private void verClientes() {
        String query = "SELECT * FROM Cliente";
        try (PreparedStatement stmt = connection.prepareStatement(query); ResultSet rs = stmt.executeQuery()) {

            StringBuilder clientes = new StringBuilder("Clientes:\n");
            while (rs.next()) {
                clientes.append("ID: ").append(rs.getString("id_cliente"))
                        .append(", Nombre: ").append(rs.getString("nombre"))
                        .append("\n");
            }
            JOptionPane.showMessageDialog(null, clientes.toString());
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al obtener clientes: " + e.getMessage());
        }
    }

    private void eliminarCliente() {
        String idCliente = JOptionPane.showInputDialog("Ingrese el ID del Cliente a eliminar:");
        String query = "DELETE FROM Cliente WHERE id_cliente = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, idCliente);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, "Cliente eliminado correctamente.");
            } else {
                JOptionPane.showMessageDialog(null, "Cliente no encontrado.");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar cliente: " + e.getMessage());
        }
    }

    private void buscarCliente() {
        String idCliente = JOptionPane.showInputDialog("Ingrese el ID del Cliente:");
        String query = "SELECT * FROM Cliente WHERE id_cliente = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, idCliente);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String cliente = "ID: " + rs.getString("id_cliente") + ", Nombre: " + rs.getString("nombre");
                JOptionPane.showMessageDialog(null, cliente);
            } else {
                JOptionPane.showMessageDialog(null, "Cliente no encontrado.");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al buscar cliente: " + e.getMessage());
        }
    }

    private void cambiarNombreEmpleado() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void verEmpleados() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void eliminarEmpleado() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void buscarEmpleado() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void mostrarPlatoCategoria() {
        String query = "SELECT nombre, precio FROM platos WHERE categoria = 'Plato Principal'";
        try (Connection conexion = GestorRestaurante.getConnection(); Statement statement = conexion.createStatement(); ResultSet rs = statement.executeQuery(query)) {
            StringBuilder platos = new StringBuilder("Platos Principales:\n");
            while (rs.next()) {
                String nombrePlato = rs.getString("nombre");
                double precioPlato = rs.getDouble("precio");
                platos.append("Nombre: ").append(nombrePlato).append(", Precio: ").append(precioPlato).append("\n");
            }
            JOptionPane.showMessageDialog(null, platos.toString());
        } catch (SQLException e) {
            System.out.println("Error al mostrar los platos: " + e.getMessage());
        }
    }

    private void enlistarEmpleadosSalarioMayor(int x) {
        String query = "SELECT nombre, salario FROM empleados WHERE salario > ?";
        try (Connection conexion = GestorRestaurante.getConnection(); PreparedStatement stmt = conexion.prepareStatement(query)) {
            stmt.setInt(1, x);
            ResultSet rs = stmt.executeQuery();
            StringBuilder empleados = new StringBuilder("Empleados con salario mayor a " + x + ":\n");
            while (rs.next()) {
                String nombreEmpleado = rs.getString("nombre");
                double salarioEmpleado = rs.getDouble("salario");
                empleados.append("Nombre: ").append(nombreEmpleado).append(", Salario: ").append(salarioEmpleado).append("\n");
            }
            JOptionPane.showMessageDialog(null, empleados.toString());
        } catch (SQLException e) {
            System.out.println("Error al mostrar los empleados: " + e.getMessage());
        }
    }

    private void listarPedidosPlatos() {
        String query = "SELECT * FROM pedidos";
        String query2 = "SELECT nombre FROM platos WHERE id = ?";
//tienen que mostrar nombre de platos con el mismo id_plato
        try (Connection conexion = GestorRestaurante.getConnection(); Statement statement = conexion.createStatement(); ResultSet rs = statement.executeQuery(query)) {
            StringBuilder pedidos = new StringBuilder("Pedidos:\n");
            while (rs.next()) {
                int idPedido = rs.getInt("id");
                String idCliente = rs.getString("cliente_id");
                double total = rs.getDouble("total");
                int idPlato = rs.getInt("id_plato");

                // Fetch the name of the dish
                try (PreparedStatement stmt2 = conexion.prepareStatement(query2)) {
                    stmt2.setInt(1, idPlato);
                    ResultSet rs2 = stmt2.executeQuery();
                    if (rs2.next()) {
                        String nombrePlato = rs2.getString("nombre");
                        pedidos.append("ID Pedido: ").append(idPedido)
                                .append(", ID Cliente: ").append(idCliente)
                                .append(", Total: ").append(total)
                                .append(", Plato: ").append(nombrePlato)
                                .append("\n");
                    }
                }
            }
            JOptionPane.showMessageDialog(null, pedidos.toString());
        } catch (SQLException e) {
            System.out.println("Error al mostrar los pedidos: " + e.getMessage());
        }
    }

    private void mostrarPostres() {
        String query = "SELECT nombre, precio FROM platos WHERE categoria = 'Postre'";
        try (Connection conexion = GestorRestaurante.getConnection(); Statement statement = conexion.createStatement(); ResultSet rs = statement.executeQuery(query)) {
            StringBuilder platos = new StringBuilder("Postres:\n");
            while (rs.next()) {
                String nombrePlato = rs.getString("nombre");
                double precioPlato = rs.getDouble("precio");
                platos.append("Nombre: ").append(nombrePlato).append(", Precio: ").append(precioPlato).append("\n");
            }
            JOptionPane.showMessageDialog(null, platos.toString());
        } catch (SQLException e) {
            System.out.println("Error al mostrar los platos: " + e.getMessage());
        }
    }

    private void registrarCliente() {
    }

    private void registrarEmpleado() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    private void registrarPlato() {
        String nombre = JOptionPane.showInputDialog("Ingrese el nombre del plato:");
        double precio = Double.parseDouble(JOptionPane.showInputDialog("Ingrese el precio del plato:"));
        String categoria = JOptionPane.showInputDialog("Ingrese la categoría del plato:");
        String query = "INSERT INTO platos (nombre, precio, categoria) VALUES (?,?,?)";
        try (Connection conexion = GestorRestaurante.getConnection(); PreparedStatement stmt = conexion.prepareStatement(query)) {
            stmt.setString(1, nombre);
            stmt.setDouble(2, precio);
            stmt.setString(3, categoria);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, "Plato registrado correctamente.");
            } else {
                JOptionPane.showMessageDialog(null, "Error al registrar el plato.");
            }
        } catch (SQLException e) {
            System.out.println("Error al registrar el plato: " + e.getMessage());
        }

    }

    private void verPlatos() {
        String query = "SELECT * FROM platos";
        try (Connection conexion = GestorRestaurante.getConnection(); Statement statement = conexion.createStatement(); ResultSet rs = statement.executeQuery(query)) {
            StringBuilder platos = new StringBuilder("Platos:\n");
            while (rs.next()) {
                int idPlato = rs.getInt("id");
                String nombrePlato = rs.getString("nombre");
                double precioPlato = rs.getDouble("precio");
                String categoriaPlato = rs.getString("categoria");
                platos.append("ID: ").append(idPlato).append(", Nombre: ").append(nombrePlato)
                        .append(", Precio: ").append(precioPlato).append(", Categoría: ").append(categoriaPlato)
                        .append("\n");
            }
            JOptionPane.showMessageDialog(null, platos.toString());
        } catch (SQLException e) {
            System.out.println("Error al mostrar los platos: " + e.getMessage());
        }
    }

    private void buscarPlato() {
        String nombre = JOptionPane.showInputDialog(null, "Ingrese el nombre del plato:");
        String query = "SELECT * FROM platos WHERE nombre = ?";
        try (Connection conexion = GestorRestaurante.getConnection(); PreparedStatement stmt = conexion.prepareStatement(query)) {
            stmt.setString(1, nombre);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                int idPlato = rs.getInt("id");
                String nombrePlato = rs.getString("nombre");
                double precioPlato = rs.getDouble("precio");
                String categoriaPlato = rs.getString("categoria");
                String plato = "ID: " + idPlato + ", Nombre: " + nombrePlato + ", Precio: " + precioPlato + ", Categoría: " + categoriaPlato;
                JOptionPane.showMessageDialog(null, plato);
            } else {
                JOptionPane.showMessageDialog(null, "Plato no encontrado.");
            }
        } catch (SQLException e) {
            System.out.println("Error al buscar el plato: " + e.getMessage());
        }
    }

    private void eliminarPlato() {
        String nombre = JOptionPane.showInputDialog("Ingrese el nombre del plato a eliminar:");
        String query = "DELETE FROM platos WHERE nombre = ?";
        try (Connection conexion = GestorRestaurante.getConnection(); PreparedStatement stmt = conexion.prepareStatement(query)) {
            stmt.setString(1, nombre);
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, "Plato eliminado correctamente.");
            } else {
                JOptionPane.showMessageDialog(null, "Plato no encontrado.");
            }
        } catch (SQLException e) {
            System.out.println("Error al eliminar el plato: " + e.getMessage());
        }
    }

    private void crearMesa() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    private void verMesas() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    private void buscarMesa() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    private void eliminarMesa() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

}
