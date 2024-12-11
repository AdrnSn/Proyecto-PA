package Vista;

import DAO.ProductoDAO;
import Modelo.Producto;

import javax.swing.*;
import java.awt.*;

public class FormularioAgregarProducto extends JDialog {
    private JTextField txtNombre, txtDescripcion, txtPrecioVenta, txtCantidad, txtCodigoBarras, txtProveedor,
            txtFechaCaducidad, txtPrecioCompra, txtStockMinimo, txtMargenGanancia, txtPeso, txtTipo;
    private JComboBox<String> cbCategoria, cbUnidadMedida;
    private ProductoDAO productoDAO;
    private Runnable callback;

    public FormularioAgregarProducto(JFrame parent, ProductoDAO productoDAO, Runnable callback) {
        super(parent, "Agregar Producto", true);
        this.productoDAO = productoDAO;
        this.callback = callback;

        setSize(400, 600);
        setLocationRelativeTo(parent);
        setLayout(new GridLayout(15, 2, 10, 10));

        // Campos del formulario
        add(new JLabel("Nombre:"));
        txtNombre = new JTextField();
        add(txtNombre);

        add(new JLabel("Descripción:"));
        txtDescripcion = new JTextField();
        add(txtDescripcion);

        add(new JLabel("Precio Venta:"));
        txtPrecioVenta = new JTextField();
        add(txtPrecioVenta);

        add(new JLabel("Cantidad en Inventario:"));
        txtCantidad = new JTextField();
        add(txtCantidad);

        add(new JLabel("Código de Barras:"));
        txtCodigoBarras = new JTextField();
        add(txtCodigoBarras);

        add(new JLabel("Categoría:"));
        cbCategoria = new JComboBox<>(new String[]{
                "Enlatados",
                "Cereales y granos",
                "Snacks",
                "Lácteos",
                "Carnes frías",
                "Panadería",
                "Refrescos",
                "Cloro y desinfectantes",
                "Champú y acondicionador",
                "Jabón de baño",
                "Pasta dental",
                "Desodorantes"
        });
        add(cbCategoria);

        add(new JLabel("Unidad de Medida:"));
        cbUnidadMedida = new JComboBox<>(new String[]{
                "Kilogramos (kg)",
                "Gramos (g)",
                "Libras (lb)",
                "Onzas (oz)",
                "Litros (L)",
                "Mililitros (ml)",
                "Galones (gal)",
                "Piezas (pz)",
        });
                add(cbUnidadMedida);

        add(new JLabel("Proveedor:"));
        txtProveedor = new JTextField();
        add(txtProveedor);

        add(new JLabel("Fecha de Caducidad (YYYY-MM-DD):"));
        txtFechaCaducidad = new JTextField();
        add(txtFechaCaducidad);

        add(new JLabel("Precio Compra:"));
        txtPrecioCompra = new JTextField();
        add(txtPrecioCompra);

        add(new JLabel("Stock Mínimo:"));
        txtStockMinimo = new JTextField();
        add(txtStockMinimo);

        add(new JLabel("Margen de Ganancia:"));
        txtMargenGanancia = new JTextField();
        add(txtMargenGanancia);

        add(new JLabel("Peso:"));
        txtPeso = new JTextField();
        add(txtPeso);

        add(new JLabel("Tipo:"));
        txtTipo = new JTextField();
        add(txtTipo);

        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.addActionListener(e -> guardarProducto());
        add(btnGuardar);

        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(e -> dispose());
        add(btnCancelar);
    }

    private void guardarProducto() {
        try {
            String nombre = txtNombre.getText().trim();
            String descripcion = txtDescripcion.getText().trim();
            double precioVenta = Double.parseDouble(txtPrecioVenta.getText().trim());
            int cantidad = Integer.parseInt(txtCantidad.getText().trim());
            String codigoBarras = txtCodigoBarras.getText().trim();
            String categoria = cbCategoria.getSelectedItem().toString();
            String unidadMedida = cbUnidadMedida.getSelectedItem().toString();
            String proveedor = txtProveedor.getText().trim();
            java.sql.Date fechaCaducidad = java.sql.Date.valueOf(txtFechaCaducidad.getText().trim());
            double precioCompra = Double.parseDouble(txtPrecioCompra.getText().trim());
            int stockMinimo = Integer.parseInt(txtStockMinimo.getText().trim());
            double margenGanancia = Double.parseDouble(txtMargenGanancia.getText().trim());
            double peso = Double.parseDouble(txtPeso.getText().trim());
            String tipo = txtTipo.getText().trim();

            Producto producto = new Producto(
                    0, // idProducto
                    nombre, // nombre
                    descripcion, // descripcion
                    precioVenta, // precioVenta
                    cantidad, // cantidadEnInventario
                    codigoBarras, // codigoDeBarras
                    unidadMedida, // unidadMedida (String)
                    proveedor, // proveedor
                    fechaCaducidad, // fechaCaducidad
                    precioCompra, // precioCompra
                    stockMinimo, // stockMinimo
                    "activo", // estado
                    tipo, // tipo
                    peso, // peso
                    margenGanancia,
                    categoria // categoria
            );

            productoDAO.insertarProducto(producto);
            if (callback != null) {
                callback.run(); // Recargar la tabla en PantallaInventario
            }
            JOptionPane.showMessageDialog(this, "Producto agregado con éxito.");
            dispose();


        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al agregar el producto: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
