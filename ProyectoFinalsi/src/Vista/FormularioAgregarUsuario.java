package Vista;

import DAO.UsuarioDAO;

import javax.swing.*;
import java.awt.*;

public class FormularioAgregarUsuario extends JDialog {
    private JTextField txtNombre;
    private JTextField txtUsuario;
    private JPasswordField txtContraseña;
    private JComboBox<String> cbRol;
    private JTextField txtTelefono;
    private UsuarioDAO usuarioDAO;
    private Runnable callback;

    public FormularioAgregarUsuario(JFrame parent, UsuarioDAO usuarioDAO, Runnable callback) {
        super(parent, "Agregar Usuario", true);
        this.usuarioDAO = usuarioDAO;
        this.callback = callback;

        setSize(400, 300);
        setLocationRelativeTo(parent);
        setLayout(new GridLayout(6, 2, 10, 10));

        // Campos del formulario
        add(new JLabel("Nombre:"));
        txtNombre = new JTextField();
        add(txtNombre);

        add(new JLabel("Usuario:"));
        txtUsuario = new JTextField();
        add(txtUsuario);

        add(new JLabel("Contraseña:"));
        txtContraseña = new JPasswordField();
        add(txtContraseña);

        add(new JLabel("Rol:"));
        cbRol = new JComboBox<>(new String[]{"Administrador", "Empleado"});
        add(cbRol);

        add(new JLabel("Teléfono:"));
        txtTelefono = new JTextField();
        add(txtTelefono);

        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.addActionListener(e -> guardarUsuario());
        add(btnGuardar);

        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(e -> dispose());
        add(btnCancelar);
    }

    private void guardarUsuario() {
        try {
            String nombre = txtNombre.getText().trim();
            String usuario = txtUsuario.getText().trim();
            String contraseña = new String(txtContraseña.getPassword()).trim();
            String rol = (String) cbRol.getSelectedItem();
            String telefono = txtTelefono.getText().trim();

            if (nombre.isEmpty() || usuario.isEmpty() || contraseña.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Todos los campos son obligatorios.", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            usuarioDAO.agregarUsuario(nombre, usuario, contraseña, rol, telefono);
            JOptionPane.showMessageDialog(this, "Usuario agregado con éxito.");
            dispose();

            if (callback != null) {
                callback.run();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al agregar usuario: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
