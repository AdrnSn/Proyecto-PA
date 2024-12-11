package Vista;

import DAO.ProductoDAO;
import Modelo.Producto;
import Conexion.ConexionDB;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.Date;
import java.util.List;

public class PantallaInventario extends JFrame {
    private ProductoDAO productoDAO;
    private JTable tablaInventario;
    private DefaultTableModel modeloTabla;
    private JTextField txtBuscar;

    public PantallaInventario(ProductoDAO productoDAO) {
        this.productoDAO = productoDAO;

        setTitle("Inventario");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Barra de búsqueda
        JPanel panelSuperior = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel lblBuscar = new JLabel("Buscar:");
        txtBuscar = new JTextField(20);
        JButton btnBuscar = new JButton("Buscar");
        btnBuscar.addActionListener(e -> buscarProductos());

        panelSuperior.add(lblBuscar);
        panelSuperior.add(txtBuscar);
        panelSuperior.add(btnBuscar);
        add(panelSuperior, BorderLayout.NORTH);

        // Tabla
        String[] columnas = {"Id",
                "Nombre",
                "Descripcion",
                "Precio_venta",
                "Cantidad",
                "Codigo de Barras",
                "Unidad_med",
                "Provedor",
                "Fecha Caducidad",
                "Precio Compra",
                "Stock",
                "Estado",
                "Tipo",
                "Peso",
                "Margen",
                "Categoria"};
        modeloTabla = new DefaultTableModel(columnas, 0);
        tablaInventario = new JTable(modeloTabla);
        JScrollPane scrollPane = new JScrollPane(tablaInventario);
        add(scrollPane, BorderLayout.CENTER);

        // Botón para actualizar la tabla
        // Botones para acciones
        JPanel panelInferior = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton btnActualizar = new JButton("Actualizar Inventario");
        btnActualizar.addActionListener(e -> cargarProductos());

        JButton btnAgregar = new JButton("Agregar Producto");
        btnAgregar.addActionListener(e -> agregarProducto());

        // Añadimos el botón "Editar Producto" al panel inferior
        JButton btnEditar = new JButton("Editar Producto");
        btnEditar.addActionListener(e -> editarProducto());

        JButton btnEliminar = new JButton("Eliminar Producto");
        btnEliminar.addActionListener(e -> eliminarProducto());

        panelInferior.add(btnEliminar);
        panelInferior.add(btnEditar);
        panelInferior.add(btnActualizar);
        panelInferior.add(btnAgregar);

        add(panelInferior, BorderLayout.SOUTH);

        // Cargar productos iniciales
        cargarProductos();
    }

    private void cargarProductos() {
        modeloTabla.setRowCount(0);
        List<Producto> productos = productoDAO.obtenerProductosActivos(); // Obtener productos activos
        for (Producto producto : productos) {
            modeloTabla.addRow(new Object[]{
                    producto.getIdProducto(),
                    producto.getNombre(),
                    producto.getDescripcion(),
                    producto.getPrecioVenta(),
                    producto.getCantidadEnInventario(),
                    producto.getCodigoDeBarras(),
                    producto.getUnidadMedida(),//unidad,provedor,fecha,precio,stock,estado,tipo,peso,margen,categoria
                    producto.getProveedor(),
                    producto.getFechaCaducidad(),
                    producto.getPrecioCompra(),
                    producto.getStockMinimo(),
                    producto.getEstado(),
                    producto.getTipo(),
                    producto.getPeso(),
                    producto.getMargenGanancia(),
                    producto.getCategoria()
            });
        }
    }

    private void buscarProductos() {
        String filtro = txtBuscar.getText().trim();
        modeloTabla.setRowCount(0);
        Producto producto = productoDAO.buscarProductoPorCodigo(filtro);
        if (producto != null) {
            // Agregar el producto a la tabla
            modeloTabla.addRow(new Object[]{
                    producto.getIdProducto(),
                    producto.getNombre(),
                    producto.getDescripcion(),
                    producto.getPrecioVenta(),
                    producto.getCantidadEnInventario(),
                    producto.getCodigoDeBarras(),
                    producto.getUnidadMedida(),//unidad,provedor,fecha,precio,stock,estado,tipo,peso,margen,categoria
                    producto.getProveedor(),
                    producto.getFechaCaducidad(),
                    producto.getPrecioCompra(),
                    producto.getStockMinimo(),
                    producto.getTipo(),
                    producto.getPeso(),
                    producto.getMargenGanancia(),
                    producto.getCategoria()
            });
        } else {
            JOptionPane.showMessageDialog(this, "No se encontró ningún producto.", "Búsqueda", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    private void agregarProducto() {
        FormularioAgregarProducto formulario = new FormularioAgregarProducto(this, productoDAO, this::cargarProductos);
        formulario.setVisible(true);
    }


    private void editarProducto() {
        int filaSeleccionada = tablaInventario.getSelectedRow();
        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(this, "Por favor selecciona un producto para editar.");
            return;
        }

        try {
            // Obtener valores desde la tabla
            int idProducto = (Integer) modeloTabla.getValueAt(filaSeleccionada, 0);
            String nombre = (String) modeloTabla.getValueAt(filaSeleccionada, 1);
            String descripcion = (String) modeloTabla.getValueAt(filaSeleccionada, 2);
            double precioVenta = (Double) modeloTabla.getValueAt(filaSeleccionada, 3);
            int cantidad = (Integer) modeloTabla.getValueAt(filaSeleccionada, 4);
            String codigoBarras = (String) modeloTabla.getValueAt(filaSeleccionada, 5);
            String unidadMedida = (String) modeloTabla.getValueAt(filaSeleccionada, 6);
            String proveedor = (String) modeloTabla.getValueAt(filaSeleccionada, 7);

            // Manejo de la conversión de fecha
            Date fechaCaducidad = modeloTabla.getValueAt(filaSeleccionada, 8) instanceof Date
                    ? (Date) modeloTabla.getValueAt(filaSeleccionada, 8)
                    : null;

            double precioCompra = modeloTabla.getValueAt(filaSeleccionada, 9) != null
                    ? (Double) modeloTabla.getValueAt(filaSeleccionada, 9)
                    : 0.0;
            int stockMinimo = modeloTabla.getValueAt(filaSeleccionada, 10) != null
                    ? (Integer) modeloTabla.getValueAt(filaSeleccionada, 10)
                    : 0;
            String estado = (String) modeloTabla.getValueAt(filaSeleccionada, 11);
            String tipo = (String) modeloTabla.getValueAt(filaSeleccionada, 12);
            double peso = modeloTabla.getValueAt(filaSeleccionada, 13) != null
                    ? (Double) modeloTabla.getValueAt(filaSeleccionada, 13)
                    : 0.0;
            double margenGanancia = modeloTabla.getValueAt(filaSeleccionada, 14) != null
                    ? (Double) modeloTabla.getValueAt(filaSeleccionada, 14)
                    : 0.0;
            String categoria = (String) modeloTabla.getValueAt(filaSeleccionada, 15);

            // Crear el objeto Producto
            Producto producto = new Producto(
                    idProducto,
                    nombre,
                    descripcion,
                    precioVenta,
                    cantidad,
                    codigoBarras,
                    unidadMedida,
                    proveedor,
                    fechaCaducidad,
                    precioCompra,
                    stockMinimo,
                    estado,
                    tipo,
                    peso,
                    margenGanancia,
                    categoria
            );

            // Abrir el formulario de edición
            FormularioEditarProducto formulario = new FormularioEditarProducto(
                    this,
                    productoDAO,
                    producto,
                    this::cargarProductos
            );
            formulario.setVisible(true);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error al cargar el producto: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
    }



    private void eliminarProducto() {
        int filaSeleccionada = tablaInventario.getSelectedRow();
        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(this, "Por favor selecciona un producto para eliminar.");
            return;
        }

        // Obtener el código de barras del producto seleccionado
        String codigoBarras = (String) modeloTabla.getValueAt(filaSeleccionada, 5);

        // Confirmar la eliminación
        int confirmacion = JOptionPane.showConfirmDialog(
                this,
                "¿Estás seguro de que deseas eliminar este producto?",
                "Confirmar Eliminación",
                JOptionPane.YES_NO_OPTION
        );

        if (confirmacion == JOptionPane.YES_OPTION) {
            // Llamar al DAO para eliminar el producto
            productoDAO.eliminarProducto(codigoBarras);

            // Refrescar la tabla
            cargarProductos();
            JOptionPane.showMessageDialog(this, "Producto eliminado con éxito.");
        }
    }




}

