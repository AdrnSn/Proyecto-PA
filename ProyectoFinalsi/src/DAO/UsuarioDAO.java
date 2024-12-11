package DAO;

import Modelo.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAO {
    private Connection connection;

    public UsuarioDAO(Connection connection) {
        this.connection = connection;
    }

    public Usuario validarUsuario(String nombreUsuario, String contraseña) {
        Usuario usuario = null;
        String query = "SELECT id_usuario, nombre, usuario, rol, telefono FROM usuarios WHERE usuario = ? AND contraseña = ? AND estado = 'activo'";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, nombreUsuario);
            stmt.setString(2, contraseña);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                usuario = new Usuario(
                        rs.getInt("id_usuario"),
                        rs.getString("nombre"),
                        rs.getString("usuario"),
                        rs.getString("rol"),
                        rs.getString("telefono") // Si decides mantener el teléfono
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return usuario;
    }


    public void agregarUsuario(String nombre, String usuario, String contraseña, String rol, String telefono) {
        String query = "INSERT INTO usuarios (nombre, usuario, contraseña, rol, telefono, estado) VALUES (?, ?, ?, ?, ?, 'activo')";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, nombre);
            stmt.setString(2, usuario);
            stmt.setString(3, contraseña);
            stmt.setString(4, rol);
            stmt.setString(5, telefono);

            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    public void modificarUsuario(int idUsuario, String nombre, String usuario, String contraseña, String rol, String telefono) {
        String query = "UPDATE usuarios SET nombre = ?, usuario = ?, contraseña = ?, rol = ?, telefono = ? WHERE id_usuario = ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, nombre);
            stmt.setString(2, usuario);
            stmt.setString(3, contraseña);
            stmt.setString(4, rol);
            stmt.setString(5, telefono);
            stmt.setInt(6, idUsuario); // El índice debe coincidir con la posición de la columna WHERE

            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void inactivarUsuario(int idUsuario) {
        String query = "UPDATE usuarios SET estado = 'inactivo' WHERE id_usuario = ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, idUsuario);

            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Usuario> obtenerUsuariosActivos() {
        List<Usuario> usuarios = new ArrayList<>();
        String query = "SELECT id_usuario, nombre, usuario, rol, telefono FROM usuarios WHERE estado = 'activo'";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                usuarios.add(new Usuario(
                        rs.getInt("id_usuario"),
                        rs.getString("nombre"),
                        rs.getString("usuario"),
                        rs.getString("rol"),
                        rs.getString("telefono") // Si decides mantener el teléfono
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return usuarios;
    }






}

