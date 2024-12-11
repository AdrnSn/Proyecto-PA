package Modelo;

import java.util.Date;

public class Venta {
    private int idVenta;
    private Date fecha;
    private double total;
    private String tipoCliente;
    private String metodoPago;
    private int id_usuario;

    public Venta(int idVenta, Date fecha, double total, String tipoCliente, String metodoPago, int id_usuario) {
        this.idVenta = idVenta;
        this.fecha = fecha;
        this.total = total;
        this.tipoCliente = tipoCliente;
        this.metodoPago = metodoPago;
        this.id_usuario = id_usuario;
    }

    // Getters y Setters
    public int getIdVenta() { return idVenta; }
    public void setIdVenta(int idVenta) { this.idVenta = idVenta; }

    public Date getFecha() { return fecha; }
    public void setFecha(Date fecha) { this.fecha = fecha; }

    public double getTotal() { return total; }
    public void setTotal(double total) { this.total = total; }

    public String getTipoCliente() { return tipoCliente; }
    public void setTipoCliente(String tipoCliente) { this.tipoCliente = tipoCliente; }

    public String getMetodoPago() { return metodoPago; }
    public void setMetodoPago(String metodoPago) { this.metodoPago = metodoPago; }

    public int getId_usuario() { return id_usuario; }
    public void setId_usuario(int id_usuario) { this.id_usuario = id_usuario; }
}
