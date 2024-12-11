package Vista;

import Conexion.ConexionDB;
import DAO.DetalleVentaDAO;
import DAO.ProductoDAO;
import DAO.UsuarioDAO;
import DAO.VentaDAO;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;

public class MenuPrincipal extends JFrame {

    private String nombreUsuario; // Nombre del usuario actual
    private String rolUsuario;    // Rol del usuario actual

    public MenuPrincipal(String nombreUsuario, String rolUsuario) {
        this.nombreUsuario = nombreUsuario;
        this.rolUsuario = rolUsuario;

        setTitle("Menú Principal");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Crear panel principal
        JPanel panelPrincipal = new JPanel(new BorderLayout());

        // Panel central con botones
        JPanel panelBotones = new JPanel(new GridLayout(3, 1, 10, 10));

        JButton btnInventario = new JButton("Gestión de Inventario");
        btnInventario.addActionListener(e -> abrirPantallaInventario());

        JButton btnUsuarios = new JButton("Gestión de Usuarios");
        btnUsuarios.addActionListener(e -> abrirPantallaGestionUsuarios());

        JButton btnVentas = new JButton("Realizar Venta");
        btnVentas.addActionListener(e -> abrirPantallaVentas());

        JButton btnSalir = new JButton("Salir");
        btnSalir.addActionListener(e -> System.exit(0));

        panelBotones.add(btnInventario);
        panelBotones.add(btnUsuarios);
        panelBotones.add(btnVentas);

        panelBotones.add(btnSalir);

        panelPrincipal.add(panelBotones, BorderLayout.CENTER);

        add(panelPrincipal);
    }

    // Método para abrir la pantalla de inventario
    private void abrirPantallaInventario() {
        Connection connection = ConexionDB.getConnection();
        ProductoDAO productoDAO = new ProductoDAO(connection);

        SwingUtilities.invokeLater(() -> {
            new PantallaInventario(productoDAO).setVisible(true);
        });
    }

    // Método para abrir la pantalla de gestión de usuarios
    private void abrirPantallaGestionUsuarios() {
        Connection connection = ConexionDB.getConnection();
        UsuarioDAO usuarioDAO = new UsuarioDAO(connection);

        SwingUtilities.invokeLater(() -> {
            new PantallaGestionUsuarios(usuarioDAO).setVisible(true);
        });
    }
    private void abrirPantallaVentas() {
        // Inicializar la conexión a la base de datos
        Connection connection = ConexionDB.getConnection();

        // Crear instancias de los DAOs
        ProductoDAO productoDAO = new ProductoDAO(connection);
        VentaDAO ventaDAO = new VentaDAO(connection);
        DetalleVentaDAO detalleVentaDAO = new DetalleVentaDAO(connection);

        // Crear una instancia de la pantalla de ventas
        PantallaVentas pantallaVentas = new PantallaVentas(productoDAO, ventaDAO, detalleVentaDAO);
        pantallaVentas.setVisible(true);
    }


}
