package DAO;

import Modelo.Producto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProductoDAO {
    private Connection connection;

    public ProductoDAO(Connection connection) {
        this.connection = connection;
    }

    public List<Producto> obtenerProductosActivos() {
        List<Producto> productos = new ArrayList<>();
        String query = "SELECT * FROM productos WHERE estado = 'activo'";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Producto producto = new Producto(
                        rs.getInt("id_producto"),
                        rs.getString("nombre"),
                        rs.getString("descripcion"),
                        rs.getDouble("precio_venta"),
                        rs.getInt("cantidad_en_inventario"),
                        rs.getString("codigo_de_barras"),
                        rs.getString("unidad_medida"),
                        rs.getString("proveedor"),
                        rs.getDate("fecha_caducidad"),
                        rs.getDouble("precio_compra"),
                        rs.getInt("stock_minimo"),
                        rs.getString("estado"),
                        rs.getString("tipo"),
                        rs.getDouble("peso"),
                        rs.getDouble("margen_ganancia"),
                        rs.getString("categoria")
                );
                productos.add(producto);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return productos;
    }


    // Insertar un nuevo producto
    public void insertarProducto(Producto producto) {
        String query = "INSERT INTO productos (nombre, descripcion, precio_venta, cantidad_en_inventario, codigo_de_barras, "
                + "unidad_medida, proveedor, fecha_caducidad, precio_compra, stock_minimo, estado, tipo, peso, margen_ganancia, categoria) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, producto.getNombre());
            stmt.setString(2, producto.getDescripcion());
            stmt.setDouble(3, producto.getPrecioVenta());
            stmt.setInt(4, producto.getCantidadEnInventario());
            stmt.setString(5, producto.getCodigoDeBarras());
            stmt.setString(6, producto.getUnidadMedida());
            stmt.setString(7, producto.getProveedor());
            stmt.setDate(8, producto.getFechaCaducidad());
            stmt.setDouble(9, producto.getPrecioCompra());
            stmt.setInt(10, producto.getStockMinimo());
            stmt.setString(11, producto.getEstado());
            stmt.setString(12, producto.getTipo());
            stmt.setDouble(13, producto.getPeso());
            stmt.setDouble(14, producto.getMargenGanancia());
            stmt.setString(15, producto.getCategoria());
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    // Actualizar un producto existente
    public void actualizarProducto(Producto producto) {
        String query = "UPDATE productos SET nombre = ?, descripcion = ?, precio_venta = ?, cantidad_en_inventario = ?, "
                + "codigo_de_barras = ?, unidad_medida = ?, proveedor = ?, fecha_caducidad = ?, precio_compra = ?, "
                + "stock_minimo = ?, estado = ?, tipo = ?, peso = ?, margen_ganancia = ?, categoria = ? "
                + "WHERE id_producto = ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, producto.getNombre());
            stmt.setString(2, producto.getDescripcion());
            stmt.setDouble(3, producto.getPrecioVenta());
            stmt.setInt(4, producto.getCantidadEnInventario());
            stmt.setString(5, producto.getCodigoDeBarras());
            stmt.setString(6, producto.getUnidadMedida());
            stmt.setString(7, producto.getProveedor());
            stmt.setDate(8, producto.getFechaCaducidad());
            stmt.setDouble(9, producto.getPrecioCompra());
            stmt.setInt(10, producto.getStockMinimo());
            stmt.setString(11, producto.getEstado());
            stmt.setString(12, producto.getTipo());
            stmt.setDouble(13, producto.getPeso());
            stmt.setDouble(14, producto.getMargenGanancia());
            stmt.setString(15, producto.getCategoria());
            stmt.setInt(16, producto.getIdProducto());
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    // Cambiar el estado de un producto a inactivo (eliminación lógica)
    public void eliminarProducto(String codigoBarras) {
        String query = "UPDATE productos SET estado = 'inactivo' WHERE codigo_de_barras = ?";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, codigoBarras);
            stmt.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Producto buscarProductoPorCodigo(String codigoBarras) {
        Producto producto = null;
        String query = "SELECT * FROM productos WHERE codigo_de_barras = ? AND estado = 'activo'";

        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setString(1, codigoBarras);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                producto = new Producto(
                        rs.getInt("id_producto"),
                        rs.getString("nombre"),
                        rs.getString("descripcion"),
                        rs.getDouble("precio_venta"),
                        rs.getInt("cantidad_en_inventario"),
                        rs.getString("codigo_de_barras"),
                        rs.getString("unidad_medida"),
                        rs.getString("proveedor"),
                        rs.getDate("fecha_caducidad"),
                        rs.getDouble("precio_compra"),
                        rs.getInt("stock_minimo"),
                        rs.getString("estado"),
                        rs.getString("tipo"),
                        rs.getDouble("peso"),
                        rs.getDouble("margen_ganancia"),
                        rs.getString("categoria")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return producto;
    }
}
