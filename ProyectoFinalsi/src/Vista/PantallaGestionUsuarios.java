package Vista;

import DAO.UsuarioDAO;
import Modelo.Usuario;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;


public class PantallaGestionUsuarios extends JFrame {
    private UsuarioDAO usuarioDAO;
    private JTable tablaUsuarios;
    private DefaultTableModel modeloTabla;

    public PantallaGestionUsuarios(UsuarioDAO usuarioDAO) {
        this.usuarioDAO = usuarioDAO;

        setTitle("Gestión de Usuarios");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Tabla de usuarios
        String[] columnas = {"ID", "Nombre", "Rol", "Usuario", "Telefono"};
        modeloTabla = new DefaultTableModel(columnas, 0);
        tablaUsuarios = new JTable(modeloTabla);
        JScrollPane scrollPane = new JScrollPane(tablaUsuarios);
        add(scrollPane, BorderLayout.CENTER);

        // Botones
        JPanel panelInferior = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton btnAgregar = new JButton("Agregar Usuario");
        btnAgregar.addActionListener(e -> agregarUsuario());

        JButton btnEditar = new JButton("Editar Usuario");
        btnEditar.addActionListener(e -> editarUsuario());

        JButton btnInactivar = new JButton("Inactivar Usuario");
        btnInactivar.addActionListener(e -> inactivarUsuario());



        panelInferior.add(btnAgregar);
        panelInferior.add(btnEditar);
        panelInferior.add(btnInactivar);

        add(panelInferior, BorderLayout.SOUTH);

        // Cargar usuarios iniciales
        cargarUsuarios();
    }

    private void cargarUsuarios() {
        modeloTabla.setRowCount(0);
        List<Usuario> usuarios = usuarioDAO.obtenerUsuariosActivos();
        for (Usuario usuario : usuarios) {
            modeloTabla.addRow(new Object[]{
                    usuario.getIdUsuario(),
                    usuario.getNombre(),
                    usuario.getUsuario(),
                    usuario.getRol(),
                    usuario.getTelefono() // Asegúrate de que este dato sea válido
            });
        }
    }

    private void agregarUsuario() {
        FormularioAgregarUsuario formulario = new FormularioAgregarUsuario(this, usuarioDAO, this::cargarUsuarios);
        formulario.setVisible(true);
    }


    private void editarUsuario() {
        int filaSeleccionada = tablaUsuarios.getSelectedRow();
        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(this, "Por favor selecciona un usuario para editar.");
            return;
        }

        // Verifica que las columnas existan en la tabla
        try {
            int idUsuario = (int) modeloTabla.getValueAt(filaSeleccionada, 0);
            String nombre = (String) modeloTabla.getValueAt(filaSeleccionada, 1);
            String usuario = (String) modeloTabla.getValueAt(filaSeleccionada, 2);
            String rol = (String) modeloTabla.getValueAt(filaSeleccionada, 3);
            String telefono = (String) modeloTabla.getValueAt(filaSeleccionada, 4);

            // Abrir el formulario para editar el usuario
            FormularioEditarUsuario formulario = new FormularioEditarUsuario(
                    this, usuarioDAO, idUsuario, nombre, usuario, rol, telefono, this::cargarUsuarios);
            formulario.setVisible(true);
        } catch (ArrayIndexOutOfBoundsException ex) {
            JOptionPane.showMessageDialog(this, "La fila seleccionada no contiene datos suficientes.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }




    private void inactivarUsuario() {
        int filaSeleccionada = tablaUsuarios.getSelectedRow();
        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(this, "Por favor selecciona un usuario para inactivar.");
            return;
        }

        int idUsuario = (int) modeloTabla.getValueAt(filaSeleccionada, 0);
        usuarioDAO.inactivarUsuario(idUsuario);
        cargarUsuarios();
        JOptionPane.showMessageDialog(this, "Usuario inactivado con éxito.");
    }

}
