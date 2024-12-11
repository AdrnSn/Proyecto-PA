package Vista;

import DAO.ProductoDAO;
import Modelo.Producto;

import javax.swing.*;
import java.awt.*;

public class FormularioEditarProducto extends JDialog {
    private JTextField txtNombre, txtDescripcion, txtPrecioVenta, txtCantidad, txtCodigoBarras, txtProveedor,
            txtFechaCaducidad, txtPrecioCompra, txtStockMinimo, txtMargenGanancia, txtPeso, txtTipo;
    private JComboBox<String> cbCategoria, cbUnidadMedida, cbEstado;
    private ProductoDAO productoDAO;
    private Producto producto;
    private Runnable callback;

    public FormularioEditarProducto(JFrame parent, ProductoDAO productoDAO, Producto producto, Runnable callback) {
        super(parent, "Modificar Producto", true);
        this.productoDAO = productoDAO;
        this.producto = producto;
        this.callback = callback;

        setSize(400, 600);
        setLocationRelativeTo(parent);
        GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.columnWidths = new int[]{0, 166, 166, 166, 166, 0};
        gridBagLayout.rowHeights = new int[]{28, 43, 39, 43, 39, 45, 38, 39, 35, 28, 39, 54, 0, 0};
        gridBagLayout.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
        gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
        getContentPane( ).setLayout(gridBagLayout);
                                                                                                                                        
                                                                                                                                                // Campos del formulario (rellenados con los datos del producto)
                                                                                                                                                GridBagConstraints gbc = new GridBagConstraints();
                                                                                                                                                gbc.fill = GridBagConstraints.BOTH;
                                                                                                                                                gbc.insets = new Insets(0, 0, 5, 5);
                                                                                                                                                gbc.gridx = 1;
                                                                                                                                                gbc.gridy = 1;
                                                                                                                                                JLabel label_5 = new JLabel("Nombre:");
                                                                                                                                                getContentPane().add(label_5, gbc);
                                                                                                                                        txtNombre = new JTextField(producto.getNombre());
                                                                                                                                        GridBagConstraints gbc_txtNombre = new GridBagConstraints();
                                                                                                                                        gbc_txtNombre.fill = GridBagConstraints.BOTH;
                                                                                                                                        gbc_txtNombre.insets = new Insets(0, 0, 5, 5);
                                                                                                                                        gbc_txtNombre.gridx = 2;
                                                                                                                                        gbc_txtNombre.gridy = 1;
                                                                                                                                        getContentPane().add(txtNombre, gbc_txtNombre);
                                                                                                                                
                                                                                                                                        GridBagConstraints gbc_1 = new GridBagConstraints();
                                                                                                                                        gbc_1.fill = GridBagConstraints.BOTH;
                                                                                                                                        gbc_1.insets = new Insets(0, 0, 5, 5);
                                                                                                                                        gbc_1.gridx = 3;
                                                                                                                                        gbc_1.gridy = 1;
                                                                                                                                        JLabel label_14 = new JLabel("Descripción:");
                                                                                                                                        getContentPane().add(label_14, gbc_1);
                                                                                                                                txtDescripcion = new JTextField(producto.getDescripcion());
                                                                                                                                GridBagConstraints gbc_txtDescripcion = new GridBagConstraints();
                                                                                                                                gbc_txtDescripcion.fill = GridBagConstraints.BOTH;
                                                                                                                                gbc_txtDescripcion.insets = new Insets(0, 0, 5, 0);
                                                                                                                                gbc_txtDescripcion.gridx = 4;
                                                                                                                                gbc_txtDescripcion.gridy = 1;
                                                                                                                                getContentPane().add(txtDescripcion, gbc_txtDescripcion);
                                                                                                                        
                                                                                                                                GridBagConstraints gbc_2 = new GridBagConstraints();
                                                                                                                                gbc_2.fill = GridBagConstraints.BOTH;
                                                                                                                                gbc_2.insets = new Insets(0, 0, 5, 5);
                                                                                                                                gbc_2.gridx = 1;
                                                                                                                                gbc_2.gridy = 2;
                                                                                                                                JLabel label = new JLabel("Precio Venta:");
                                                                                                                                getContentPane().add(label, gbc_2);
                                                                                                                                txtPrecioVenta = new JTextField(String.valueOf(producto.getPrecioVenta()));
                                                                                                                                GridBagConstraints gbc_txtPrecioVenta = new GridBagConstraints();
                                                                                                                                gbc_txtPrecioVenta.fill = GridBagConstraints.BOTH;
                                                                                                                                gbc_txtPrecioVenta.insets = new Insets(0, 0, 5, 5);
                                                                                                                                gbc_txtPrecioVenta.gridx = 2;
                                                                                                                                gbc_txtPrecioVenta.gridy = 2;
                                                                                                                                getContentPane().add(txtPrecioVenta, gbc_txtPrecioVenta);
                                                                                                                        
                                                                                                                                GridBagConstraints gbc_3 = new GridBagConstraints();
                                                                                                                                gbc_3.fill = GridBagConstraints.BOTH;
                                                                                                                                gbc_3.insets = new Insets(0, 0, 5, 5);
                                                                                                                                gbc_3.gridx = 3;
                                                                                                                                gbc_3.gridy = 2;
                                                                                                                                JLabel label_11 = new JLabel("Cantidad en Inventario:");
                                                                                                                                getContentPane().add(label_11, gbc_3);
                                                                                                                txtCantidad = new JTextField(String.valueOf(producto.getCantidadEnInventario()));
                                                                                                                GridBagConstraints gbc_txtCantidad = new GridBagConstraints();
                                                                                                                gbc_txtCantidad.fill = GridBagConstraints.BOTH;
                                                                                                                gbc_txtCantidad.insets = new Insets(0, 0, 5, 0);
                                                                                                                gbc_txtCantidad.gridx = 4;
                                                                                                                gbc_txtCantidad.gridy = 2;
                                                                                                                getContentPane().add(txtCantidad, gbc_txtCantidad);
                                                                                                        
                                                                                                                GridBagConstraints gbc_4 = new GridBagConstraints();
                                                                                                                gbc_4.fill = GridBagConstraints.BOTH;
                                                                                                                gbc_4.insets = new Insets(0, 0, 5, 5);
                                                                                                                gbc_4.gridx = 1;
                                                                                                                gbc_4.gridy = 3;
                                                                                                                JLabel label_3 = new JLabel("Código de Barras:");
                                                                                                                getContentPane().add(label_3, gbc_4);
                                                                                                        txtCodigoBarras = new JTextField(producto.getCodigoDeBarras());
                                                                                                        GridBagConstraints gbc_txtCodigoBarras = new GridBagConstraints();
                                                                                                        gbc_txtCodigoBarras.fill = GridBagConstraints.BOTH;
                                                                                                        gbc_txtCodigoBarras.insets = new Insets(0, 0, 5, 5);
                                                                                                        gbc_txtCodigoBarras.gridx = 2;
                                                                                                        gbc_txtCodigoBarras.gridy = 3;
                                                                                                        getContentPane().add(txtCodigoBarras, gbc_txtCodigoBarras);
                                                                                                
                                                                                                        GridBagConstraints gbc_5 = new GridBagConstraints();
                                                                                                        gbc_5.fill = GridBagConstraints.BOTH;
                                                                                                        gbc_5.insets = new Insets(0, 0, 5, 5);
                                                                                                        gbc_5.gridx = 3;
                                                                                                        gbc_5.gridy = 3;
                                                                                                        JLabel label_6 = new JLabel("Categoría:");
                                                                                                        getContentPane().add(label_6, gbc_5);
                                                                                                cbCategoria = new JComboBox<>(new String[]{"Enlatados",
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
                                                                                                        "Desodorantes"});
                                                                                                cbCategoria.setSelectedItem(producto.getCategoria()); // Selecciona la categoría actual
                                                                                                GridBagConstraints gbc_cbCategoria = new GridBagConstraints();
                                                                                                gbc_cbCategoria.fill = GridBagConstraints.BOTH;
                                                                                                gbc_cbCategoria.insets = new Insets(0, 0, 5, 0);
                                                                                                gbc_cbCategoria.gridx = 4;
                                                                                                gbc_cbCategoria.gridy = 3;
                                                                                                getContentPane().add(cbCategoria, gbc_cbCategoria);
                                                                                        
                                                                                                GridBagConstraints gbc_6 = new GridBagConstraints();
                                                                                                gbc_6.fill = GridBagConstraints.BOTH;
                                                                                                gbc_6.insets = new Insets(0, 0, 5, 5);
                                                                                                gbc_6.gridx = 1;
                                                                                                gbc_6.gridy = 4;
                                                                                                JLabel label_7 = new JLabel("Unidad de Medida:");
                                                                                                getContentPane().add(label_7, gbc_6);
                                                                                        cbUnidadMedida = new JComboBox<>(new String[]{"Kilogramos (kg)",
                                                                                                "Gramos (g)",
                                                                                                "Libras (lb)",
                                                                                                "Onzas (oz)",
                                                                                                "Litros (L)",
                                                                                                "Mililitros (ml)",
                                                                                                "Galones (gal)",
                                                                                                "Piezas (pz)"});
                                                                                        cbUnidadMedida.setSelectedItem(producto.getUnidadMedida()); // Selecciona la unidad actual
                                                                                        GridBagConstraints gbc_cbUnidadMedida = new GridBagConstraints();
                                                                                        gbc_cbUnidadMedida.fill = GridBagConstraints.BOTH;
                                                                                        gbc_cbUnidadMedida.insets = new Insets(0, 0, 5, 5);
                                                                                        gbc_cbUnidadMedida.gridx = 2;
                                                                                        gbc_cbUnidadMedida.gridy = 4;
                                                                                        getContentPane().add(cbUnidadMedida, gbc_cbUnidadMedida);
                                                                                
                                                                                        GridBagConstraints gbc_7 = new GridBagConstraints();
                                                                                        gbc_7.fill = GridBagConstraints.BOTH;
                                                                                        gbc_7.insets = new Insets(0, 0, 5, 5);
                                                                                        gbc_7.gridx = 3;
                                                                                        gbc_7.gridy = 4;
                                                                                        JLabel label_8 = new JLabel("Proveedor:");
                                                                                        getContentPane().add(label_8, gbc_7);
                                                                                txtProveedor = new JTextField(producto.getProveedor());
                                                                                GridBagConstraints gbc_txtProveedor = new GridBagConstraints();
                                                                                gbc_txtProveedor.fill = GridBagConstraints.BOTH;
                                                                                gbc_txtProveedor.insets = new Insets(0, 0, 5, 0);
                                                                                gbc_txtProveedor.gridx = 4;
                                                                                gbc_txtProveedor.gridy = 4;
                                                                                getContentPane().add(txtProveedor, gbc_txtProveedor);
                                                                        
                                                                                GridBagConstraints gbc_8 = new GridBagConstraints();
                                                                                gbc_8.fill = GridBagConstraints.BOTH;
                                                                                gbc_8.insets = new Insets(0, 0, 5, 5);
                                                                                gbc_8.gridx = 1;
                                                                                gbc_8.gridy = 5;
                                                                                JLabel label_10 = new JLabel("Fecha de Caducidad (YYYY-MM-DD):");
                                                                                getContentPane().add(label_10, gbc_8);
                                                                        txtFechaCaducidad = new JTextField(producto.getFechaCaducidad().toString());
                                                                        GridBagConstraints gbc_txtFechaCaducidad = new GridBagConstraints();
                                                                        gbc_txtFechaCaducidad.fill = GridBagConstraints.BOTH;
                                                                        gbc_txtFechaCaducidad.insets = new Insets(0, 0, 5, 5);
                                                                        gbc_txtFechaCaducidad.gridx = 2;
                                                                        gbc_txtFechaCaducidad.gridy = 5;
                                                                        getContentPane().add(txtFechaCaducidad, gbc_txtFechaCaducidad);
                                                                
                                                                        GridBagConstraints gbc_9 = new GridBagConstraints();
                                                                        gbc_9.fill = GridBagConstraints.BOTH;
                                                                        gbc_9.insets = new Insets(0, 0, 5, 5);
                                                                        gbc_9.gridx = 3;
                                                                        gbc_9.gridy = 5;
                                                                        JLabel label_1 = new JLabel("Precio Compra:");
                                                                        getContentPane().add(label_1, gbc_9);
                                                                txtPrecioCompra = new JTextField(String.valueOf(producto.getPrecioCompra()));
                                                                GridBagConstraints gbc_txtPrecioCompra = new GridBagConstraints();
                                                                gbc_txtPrecioCompra.fill = GridBagConstraints.BOTH;
                                                                gbc_txtPrecioCompra.insets = new Insets(0, 0, 5, 0);
                                                                gbc_txtPrecioCompra.gridx = 4;
                                                                gbc_txtPrecioCompra.gridy = 5;
                                                                getContentPane().add(txtPrecioCompra, gbc_txtPrecioCompra);
                                                        
                                                                GridBagConstraints gbc_10 = new GridBagConstraints();
                                                                gbc_10.fill = GridBagConstraints.BOTH;
                                                                gbc_10.insets = new Insets(0, 0, 5, 5);
                                                                gbc_10.gridx = 1;
                                                                gbc_10.gridy = 6;
                                                                JLabel label_4 = new JLabel("Stock Mínimo:");
                                                                getContentPane().add(label_4, gbc_10);
                                                        txtStockMinimo = new JTextField(String.valueOf(producto.getStockMinimo()));
                                                        GridBagConstraints gbc_txtStockMinimo = new GridBagConstraints();
                                                        gbc_txtStockMinimo.fill = GridBagConstraints.BOTH;
                                                        gbc_txtStockMinimo.insets = new Insets(0, 0, 5, 5);
                                                        gbc_txtStockMinimo.gridx = 2;
                                                        gbc_txtStockMinimo.gridy = 6;
                                                        getContentPane().add(txtStockMinimo, gbc_txtStockMinimo);
                                                
                                                        GridBagConstraints gbc_11 = new GridBagConstraints();
                                                        gbc_11.fill = GridBagConstraints.BOTH;
                                                        gbc_11.insets = new Insets(0, 0, 5, 5);
                                                        gbc_11.gridx = 3;
                                                        gbc_11.gridy = 6;
                                                        JLabel label_12 = new JLabel("Estado:");
                                                        getContentPane().add(label_12, gbc_11);
                                                cbEstado = new JComboBox<>(new String[]{"activo", "inactivo" });
                                                cbEstado.setSelectedItem(producto.getEstado()); // Selecciona el Estado
                                                GridBagConstraints gbc_cbEstado = new GridBagConstraints();
                                                gbc_cbEstado.fill = GridBagConstraints.BOTH;
                                                gbc_cbEstado.insets = new Insets(0, 0, 5, 0);
                                                gbc_cbEstado.gridx = 4;
                                                gbc_cbEstado.gridy = 6;
                                                getContentPane().add(cbEstado, gbc_cbEstado);
                                        
                                                GridBagConstraints gbc_12 = new GridBagConstraints();
                                                gbc_12.fill = GridBagConstraints.BOTH;
                                                gbc_12.insets = new Insets(0, 0, 5, 5);
                                                gbc_12.gridx = 1;
                                                gbc_12.gridy = 7;
                                                JLabel label_13 = new JLabel("Margen de Ganancia:");
                                                getContentPane().add(label_13, gbc_12);
                                        txtMargenGanancia = new JTextField(String.valueOf(producto.getMargenGanancia()));
                                        GridBagConstraints gbc_txtMargenGanancia = new GridBagConstraints();
                                        gbc_txtMargenGanancia.fill = GridBagConstraints.BOTH;
                                        gbc_txtMargenGanancia.insets = new Insets(0, 0, 5, 5);
                                        gbc_txtMargenGanancia.gridx = 2;
                                        gbc_txtMargenGanancia.gridy = 7;
                                        getContentPane().add(txtMargenGanancia, gbc_txtMargenGanancia);
                                
                                        GridBagConstraints gbc_13 = new GridBagConstraints();
                                        gbc_13.fill = GridBagConstraints.BOTH;
                                        gbc_13.insets = new Insets(0, 0, 5, 5);
                                        gbc_13.gridx = 3;
                                        gbc_13.gridy = 7;
                                        JLabel label_9 = new JLabel("Peso:");
                                        getContentPane().add(label_9, gbc_13);
                                txtPeso = new JTextField(String.valueOf(producto.getPeso()));
                                GridBagConstraints gbc_txtPeso = new GridBagConstraints();
                                gbc_txtPeso.fill = GridBagConstraints.BOTH;
                                gbc_txtPeso.insets = new Insets(0, 0, 5, 0);
                                gbc_txtPeso.gridx = 4;
                                gbc_txtPeso.gridy = 7;
                                getContentPane().add(txtPeso, gbc_txtPeso);
                        
                                GridBagConstraints gbc_14 = new GridBagConstraints();
                                gbc_14.fill = GridBagConstraints.BOTH;
                                gbc_14.insets = new Insets(0, 0, 5, 5);
                                gbc_14.gridx = 1;
                                gbc_14.gridy = 8;
                                JLabel label_2 = new JLabel("Tipo:");
                                getContentPane().add(label_2, gbc_14);
                        txtTipo = new JTextField(producto.getTipo());
                        GridBagConstraints gbc_txtTipo = new GridBagConstraints();
                        gbc_txtTipo.fill = GridBagConstraints.BOTH;
                        gbc_txtTipo.insets = new Insets(0, 0, 5, 5);
                        gbc_txtTipo.gridx = 2;
                        gbc_txtTipo.gridy = 8;
                        getContentPane().add(txtTipo, gbc_txtTipo);
                        
                                JButton btnCancelar = new JButton("Cancelar");
                                btnCancelar.addActionListener(e -> dispose());
                                
                                        JButton btnGuardar = new JButton("Guardar Cambios");
                                        btnGuardar.addActionListener(e -> guardarCambios());
                                        GridBagConstraints gbc_btnGuardar = new GridBagConstraints();
                                        gbc_btnGuardar.fill = GridBagConstraints.BOTH;
                                        gbc_btnGuardar.insets = new Insets(0, 0, 5, 5);
                                        gbc_btnGuardar.gridx = 3;
                                        gbc_btnGuardar.gridy = 11;
                                        getContentPane().add(btnGuardar, gbc_btnGuardar);
                                GridBagConstraints gbc_btnCancelar = new GridBagConstraints();
                                gbc_btnCancelar.insets = new Insets(0, 0, 5, 0);
                                gbc_btnCancelar.fill = GridBagConstraints.BOTH;
                                gbc_btnCancelar.gridx = 4;
                                gbc_btnCancelar.gridy = 11;
                                getContentPane().add(btnCancelar, gbc_btnCancelar);
    }

    private void guardarCambios() {
        try {
            producto.setNombre(txtNombre.getText().trim());
            producto.setDescripcion(txtDescripcion.getText().trim());
            producto.setPrecioVenta(Double.parseDouble(txtPrecioVenta.getText().trim()));
            producto.setCantidadEnInventario(Integer.parseInt(txtCantidad.getText().trim()));
            producto.setCodigoDeBarras(txtCodigoBarras.getText().trim());
            producto.setUnidadMedida(cbUnidadMedida.getSelectedItem().toString());
            producto.setProveedor(txtProveedor.getText().trim());
            producto.setFechaCaducidad(java.sql.Date.valueOf(txtFechaCaducidad.getText().trim()));
            producto.setPrecioCompra(Double.parseDouble(txtPrecioCompra.getText().trim()));
            producto.setStockMinimo(Integer.parseInt(txtStockMinimo.getText().trim()));
            producto.setEstado(cbEstado.getSelectedItem().toString());
            producto.setTipo(txtTipo.getText().trim());
            producto.setPeso(Double.parseDouble(txtPeso.getText().trim()));
            producto.setMargenGanancia(Double.parseDouble(txtMargenGanancia.getText().trim()));
            producto.setCategoria(cbCategoria.getSelectedItem().toString());





            productoDAO.actualizarProducto(producto);
            JOptionPane.showMessageDialog(this, "Producto modificado con éxito.");
            dispose();

            if (callback != null) {
                callback.run();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al modificar el producto: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
