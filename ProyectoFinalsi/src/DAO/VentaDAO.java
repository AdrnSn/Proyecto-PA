package DAO;

import Modelo.Venta;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class VentaDAO {
    private Connection connection;

    public VentaDAO(Connection connection) {
        this.connection = connection;
    }
    public int guardarVenta(int idUsuario, double total, double descuento, double totalConDescuento) {
        String query = "INSERT INTO ventas (id_usuario, total, descuento, total_con_descuento, fecha) VALUES (?, ?, ?, ?, NOW()) RETURNING id_venta";
        int idVenta = -1;

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, idUsuario); // ID del usuario que realiza la venta
            stmt.setDouble(2, total);
            stmt.setDouble(3, descuento);
            stmt.setDouble(4, totalConDescuento);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                idVenta = rs.getInt("id_venta"); // Obtener el ID generado por la base de datos
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return idVenta;
    }

    public int insertarVenta(Venta venta) {
        String query = "INSERT INTO ventas (total, tipo_cliente, metodo_pago, id_usuario) VALUES (?, ?, ?, ?) RETURNING id_venta";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setDouble(1, venta.getTotal());
            stmt.setString(2, venta.getTipoCliente());
            stmt.setString(3, venta.getMetodoPago());
            stmt.setInt(4, venta.getId_usuario());

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("id_venta");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return -1; // Error al insertar
    }

    public List<Venta> obtenerVentas() {
        List<Venta> ventas = new ArrayList<>();
        String query = "SELECT * FROM ventas";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                ventas.add(new Venta(
                        rs.getInt("id_venta"),
                        rs.getTimestamp("fecha"),
                        rs.getDouble("total"),
                        rs.getString("tipo_cliente"),
                        rs.getString("metodo_pago"),
                        rs.getInt("id_empleado")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ventas;
    }
}
