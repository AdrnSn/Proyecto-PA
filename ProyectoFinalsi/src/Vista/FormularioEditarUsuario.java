package Vista;

import DAO.UsuarioDAO;

import javax.swing.*;
import java.awt.*;

public class FormularioEditarUsuario extends JDialog {
    private JTextField txtNombre;
    private JTextField txtUsuario;
    private JPasswordField txtContraseña;
    private JComboBox<String> cbRol;
    private JTextField txtTelefono;
    private UsuarioDAO usuarioDAO;
    private int idUsuario;
    private Runnable callback;

    public FormularioEditarUsuario(JFrame parent, UsuarioDAO usuarioDAO, int idUsuario, String nombre, String usuario, String rol, String telefono, Runnable callback) {
        super(parent, "Editar Usuario", true);
        this.usuarioDAO = usuarioDAO;
        this.idUsuario = idUsuario;
        this.callback = callback;

        setSize(400, 300);
        setLocationRelativeTo(parent);
        setLayout(new GridLayout(6, 2, 10, 10));

        // Campos del formulario
        add(new JLabel("Nombre:"));
        txtNombre = new JTextField(nombre);
        add(txtNombre);

        add(new JLabel("Usuario:"));
        txtUsuario = new JTextField(usuario);
        add(txtUsuario);

        add(new JLabel("Contraseña:"));
        txtContraseña = new JPasswordField();
        add(txtContraseña);

        add(new JLabel("Rol:"));
        cbRol = new JComboBox<>(new String[]{"Administrador", "Empleado"});
        cbRol.setSelectedItem(rol);
        add(cbRol);

        add(new JLabel("Teléfono:"));
        txtTelefono = new JTextField(telefono);
        add(txtTelefono);

        JButton btnGuardar = new JButton("Guardar Cambios");
        btnGuardar.addActionListener(e -> guardarCambios());
        add(btnGuardar);

        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(e -> dispose());
        add(btnCancelar);
    }

    private void guardarCambios() {
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

            usuarioDAO.modificarUsuario(idUsuario, nombre, usuario, contraseña, rol, telefono);
            JOptionPane.showMessageDialog(this, "Usuario modificado con éxito.");
            dispose();

            if (callback != null) {
                callback.run();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al modificar usuario: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
