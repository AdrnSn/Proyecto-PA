package Vista;

import DAO.ProductoDAO;
import DAO.VentaDAO;
import DAO.DetalleVentaDAO;
import Modelo.CarritoItem;
import Modelo.DetalleVenta;
import Modelo.Producto;
import Modelo.Venta;
import javafx.print.PrinterJob;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.scene.control.TextArea;
import javafx.scene.layout.StackPane;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class PantallaVentas extends JFrame {
    private ProductoDAO productoDAO;
    private VentaDAO ventaDAO;
    private DetalleVentaDAO detalleVentaDAO;

    private JTable tablaCarrito;
    private DefaultTableModel modeloCarrito;
    private JTextField txtCodigoBarras;
    private JComboBox<String> cbTipoCliente;
    private JComboBox<String> cbMetodoPago;
    private JLabel lblTotal;
    private List<CarritoItem> carrito;

    public PantallaVentas(ProductoDAO productoDAO, VentaDAO ventaDAO, DetalleVentaDAO detalleVentaDAO) {
        this.productoDAO = productoDAO;
        this.ventaDAO = ventaDAO;
        this.detalleVentaDAO = detalleVentaDAO;
        this.carrito = new ArrayList<>();

        setTitle("Ventas");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Construir la interfaz gráfica
        construirUI();

        // Configurar listener para tipo de cliente
        configurarListenerTipoCliente();
    }

    private void construirUI() {
        // Panel superior: Búsqueda y tipo de cliente
        JPanel panelSuperior = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel lblCodigoBarras = new JLabel("Código de Barras:");
        txtCodigoBarras = new JTextField(20);
        JButton btnAgregar = new JButton("Agregar al Carrito");
        btnAgregar.addActionListener(e -> agregarProductoAlCarrito());
        panelSuperior.add(lblCodigoBarras);
        panelSuperior.add(txtCodigoBarras);
        panelSuperior.add(btnAgregar);

        JLabel lblTipoCliente = new JLabel("Tipo de Cliente:");
        cbTipoCliente = new JComboBox<>(new String[]{"Adulto/niño","Personas Mayores", "Soldados", "Personas con Discapacidad"});
        panelSuperior.add(lblTipoCliente);
        panelSuperior.add(cbTipoCliente);

        JLabel lblMetodoPago = new JLabel("Método de Pago:");
        cbMetodoPago = new JComboBox<>(new String[]{"Efectivo", "Tarjeta", "Transferencia"});
        panelSuperior.add(lblMetodoPago);
        panelSuperior.add(cbMetodoPago);

        add(panelSuperior, BorderLayout.NORTH);

        // Tabla del carrito
        String[] columnas = {"Producto", "Cantidad", "Precio Unitario", "Subtotal"};
        modeloCarrito = new DefaultTableModel(columnas, 0);
        tablaCarrito = new JTable(modeloCarrito);
        JScrollPane scrollPane = new JScrollPane(tablaCarrito);
        add(scrollPane, BorderLayout.CENTER);

        // Panel inferior: Total y finalizar venta
        JPanel panelInferior = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        lblTotal = new JLabel("Total: $0.00");
        JButton btnFinalizar = new JButton("Finalizar Venta");
        btnFinalizar.addActionListener(e -> finalizarVenta());
        panelInferior.add(lblTotal);
        panelInferior.add(btnFinalizar);
        add(panelInferior, BorderLayout.SOUTH);
    }

    private void configurarListenerTipoCliente() {
        cbTipoCliente.addActionListener(e -> actualizarTotal());
    }

    private void agregarProductoAlCarrito() {
        String codigoBarras = txtCodigoBarras.getText().trim();
        Producto producto = productoDAO.buscarProductoPorCodigo(codigoBarras);

        if (producto != null) {
            String cantidadStr = JOptionPane.showInputDialog(this, "Ingrese la cantidad:");
            try {
                int cantidad = Integer.parseInt(cantidadStr);
                if (cantidad > 0 && cantidad <= producto.getCantidadEnInventario()) {
                    CarritoItem item = new CarritoItem(producto, cantidad);
                    carrito.add(item);
                    modeloCarrito.addRow(new Object[]{
                            producto.getNombre(),
                            cantidad,
                            producto.getPrecioVenta(),
                            item.getSubtotal()
                    });
                    actualizarTotal();
                } else {
                    JOptionPane.showMessageDialog(this, "Cantidad inválida o insuficiente en inventario.");
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Por favor ingrese un número válido.");
            }
        } else {
            JOptionPane.showMessageDialog(this, "Producto no encontrado.");
        }
    }

    private void actualizarTotal() {
        double total = 0.0;
        for (CarritoItem item : carrito) {
            total += item.getSubtotal();
        }

        // Aplicar descuento según el tipo de cliente
        String tipoCliente = (String) cbTipoCliente.getSelectedItem();
        double descuento = switch (tipoCliente) {
            case "Adulto/niño" -> 0.0;
            case "Personas Mayores" -> 0.10;
            case "Soldados" -> 0.15;
            case "Personas con Discapacidad" -> 0.10;
            default -> 0.0;
        };

        total -= total * descuento;
        lblTotal.setText("Total: $" + String.format("%.2f", total));
    }

    private double calcularDescuento() {
        String tipoCliente = (String) cbTipoCliente.getSelectedItem();

        switch (tipoCliente) {
            case "Personas mayores":
                return 10.0; // Descuento en porcentaje
            case "Soldados":
                return 15.0;
            case "Personas con discapacidad":
                return 10.0;
            default:
                return 0.0; // Sin descuento para otros casos
        }
    }

    private void finalizarVenta() {
        if (carrito.isEmpty()) {
            JOptionPane.showMessageDialog(this, "El carrito está vacío. Agregue productos antes de finalizar la venta.");
            return;
        }

        // Guardar la venta en la base de datos
        double total = Double.parseDouble(lblTotal.getText().replace("Total: $", ""));
        double descuento = calcularDescuento();
        String tipoCliente = (String) cbTipoCliente.getSelectedItem();
        String metodoPago = (String) cbMetodoPago.getSelectedItem();

        Venta venta = new Venta(0, null, total, tipoCliente, metodoPago, 1); // 1 es el ID del empleado (puede ser dinámico)
        int idVenta = ventaDAO.insertarVenta(venta);

        if (idVenta != -1) {
            for (CarritoItem item : carrito) {
                detalleVentaDAO.insertarDetalleVenta(new DetalleVenta(
                        0,
                        idVenta,
                        item.getProducto().getIdProducto(),
                        item.getCantidad(),
                        item.getProducto().getPrecioVenta(),
                        item.getSubtotal()
                ));
                TicketPDF ticketPDF = new TicketPDF();
                ticketPDF.generarTicket(carrito, Double.parseDouble(tipoCliente), total);
                carrito.clear();
                modeloCarrito.setRowCount(0);
                actualizarTotal();
            }

            JOptionPane.showMessageDialog(this, "Venta finalizada con éxito. Generando ticket...");
            carrito.clear();
            modeloCarrito.setRowCount(0);
            lblTotal.setText("Total: $0.00");
        } else {
            JOptionPane.showMessageDialog(this, "Error al finalizar la venta. Intente nuevamente.");
        }

    }
    public class TicketPDF {
        public void generarTicket(List<CarritoItem> carrito, double descuento, double total) {
            // Crear el contenido del ticket
            StringBuilder ticketContent = new StringBuilder();
            ticketContent.append("Tienda de Abarrotes\n");
            ticketContent.append("=====================\n");
            ticketContent.append("Productos:\n");

            for (CarritoItem item : carrito) {
                ticketContent.append(item.getProducto().getNombre())
                        .append(" x")
                        .append(item.getCantidad())
                        .append("  $")
                        .append(String.format("%.2f", item.getSubtotal()))
                        .append("\n");
            }

            ticketContent.append("---------------------\n");
            ticketContent.append("Descuento: ").append(descuento).append("%\n");
            ticketContent.append("Total: $").append(String.format("%.2f", total)).append("\n");
            ticketContent.append("=====================\n");
            ticketContent.append("Gracias por su compra!");

            // Crear el TextArea
            TextArea textArea = new TextArea(ticketContent.toString());
            textArea.setEditable(false);
            textArea.setPrefSize(400, 300);

            // Crear un StackPane y agregar el TextArea
            StackPane root = new StackPane();
            root.getChildren().add(textArea);

            // Crear un Stage temporal
            Stage stage = new Stage();
            Scene scene = new Scene(root, 400, 300);
            stage.setScene(scene);
            stage.setTitle("Vista previa del ticket");

            // Configurar el diálogo de impresión
            PrinterJob printerJob = PrinterJob.createPrinterJob();
            if (printerJob != null && printerJob.showPrintDialog(stage)) {
                stage.show(); // Mostrar la ventana temporal
                boolean success = printerJob.printPage(textArea);
                if (success) {
                    printerJob.endJob();
                    System.out.println("Ticket generado con éxito.");
                } else {
                    System.out.println("Error al generar el ticket.");
                }
                stage.close(); // Cerrar la ventana después de imprimir
            } else {
                System.out.println("Impresión cancelada.");
            }
        }
    }
}
