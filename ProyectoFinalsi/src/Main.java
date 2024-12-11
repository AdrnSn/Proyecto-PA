import Conexion.ConexionDB;
import DAO.UsuarioDAO;
import Vista.InicioSesion;

import javax.swing.*;
import java.sql.Connection;


public class Main {
    public static void main(String[] args) {
        // Establecer conexión a la base de datos
        Connection connection = ConexionDB.getConnection();

        // Crear instancia de UsuarioDAO
        UsuarioDAO usuarioDAO = new UsuarioDAO(connection);

        // Iniciar la pantalla de inicio de sesión
        SwingUtilities.invokeLater(() -> {
            new InicioSesion(usuarioDAO).setVisible(true);
        });
    }
}