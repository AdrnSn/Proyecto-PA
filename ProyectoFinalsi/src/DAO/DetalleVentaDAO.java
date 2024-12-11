package DAO;

import Modelo.DetalleVenta;
import java.sql.Connection;
import java.sql.PreparedStatement;

public class DetalleVentaDAO {
    private Connection connection;

    public DetalleVentaDAO(Connection connection) {
        this.connection = connection;
    }

    public void insertarDetalleVenta(DetalleVenta detalle) {
        String query = "INSERT INTO detalle_ventas (id_venta, id_producto, cantidad, precio_unitario, subtotal) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, detalle.getIdVenta());
            stmt.setInt(2, detalle.getIdProducto());
            stmt.setInt(3, detalle.getCantidad());
            stmt.setDouble(4, detalle.getPrecioUnitario());
            stmt.setDouble(5, detalle.getSubtotal());
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
