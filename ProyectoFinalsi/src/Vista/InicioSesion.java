package Vista;

import Conexion.ConexionDB;
import DAO.UsuarioDAO;
import Modelo.Usuario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;

public class InicioSesion extends JFrame {
    private JTextField txtUsuario;
    private JPasswordField txtPassword;
    private JButton btnIniciar;
    private JLabel lblMensaje;
    private UsuarioDAO usuarioDAO;

    public InicioSesion(UsuarioDAO usuarioDAO) {
        this.usuarioDAO = usuarioDAO;

        setTitle("Inicio de Sesión");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new GridBagLayout());

        txtUsuario = new JTextField(15);
        txtPassword = new JPasswordField(15);
        btnIniciar = new JButton("Iniciar Sesión");
        lblMensaje = new JLabel("");

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(new JLabel("Usuario:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        add(txtUsuario, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        add(new JLabel("Contraseña:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        add(txtPassword, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(btnIniciar, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        add(lblMensaje, gbc);

        btnIniciar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                validarCredenciales();
            }
        });
    }

    private void validarCredenciales() {
        String usuario = txtUsuario.getText();
        String contraseña = String.valueOf(txtPassword.getPassword());

        Usuario usuarioValido = usuarioDAO.validarUsuario(usuario, contraseña);
        if (usuarioValido != null) {
            lblMensaje.setText("Bienvenido, " + usuarioValido.getRol());
            lblMensaje.setForeground(Color.GREEN);
            abrirMenu(usuarioValido.getRol(), usuarioValido.getUsuario());
        } else {
            lblMensaje.setText("Credenciales incorrectas");
            lblMensaje.setForeground(Color.RED);
        }
    }

    private void abrirMenu(String rol, String nombreUsuario) {
        // Crear instancia del Menú Principal
        MenuPrincipal menuPrincipal = new MenuPrincipal(nombreUsuario, rol);


        menuPrincipal.setVisible(true);


        dispose();
    }

}
