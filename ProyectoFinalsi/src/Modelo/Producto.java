package Modelo;

import java.sql.Date;

public class Producto {
    private int idProducto;
    private String nombre;
    private String descripcion; // Opcional
    private double precioVenta;
    private int cantidadEnInventario;
    private String codigoDeBarras;
    private String unidadMedida; // Opcional
    private String proveedor; // Opcional
    private Date fechaCaducidad; // Opcional
    private Double precioCompra; // Opcional
    private Integer stockMinimo; // Opcional
    private String estado; // Opcional
    private String tipo; // Opcional
    private Double peso; // Opcional
    private Double margenGanancia; // Opcional
    private String categoria; // Opcional

    public Producto(int idProducto, String nombre, String descripcion, double precioVenta, int cantidadEnInventario,
                    String codigoDeBarras, String unidadMedida, String proveedor, Date fechaCaducidad,
                    Double precioCompra, Integer stockMinimo, String estado, String tipo, Double peso,
                    Double margenGanancia, String categoria) {
        this.idProducto = idProducto;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precioVenta = precioVenta;
        this.cantidadEnInventario = cantidadEnInventario;
        this.codigoDeBarras = codigoDeBarras;
        this.unidadMedida = unidadMedida;
        this.proveedor = proveedor;
        this.fechaCaducidad = fechaCaducidad;
        this.precioCompra = precioCompra;
        this.stockMinimo = stockMinimo;
        this.estado = estado;
        this.tipo = tipo;
        this.peso = peso;
        this.margenGanancia = margenGanancia;
        this.categoria = categoria;
    }

    // Getters y Setters para cada atributo
    public int getIdProducto() { return idProducto; }
    public void setIdProducto(int idProducto) { this.idProducto = idProducto; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public double getPrecioVenta() { return precioVenta; }
    public void setPrecioVenta(double precioVenta) { this.precioVenta = precioVenta; }

    public int getCantidadEnInventario() { return cantidadEnInventario; }
    public void setCantidadEnInventario(int cantidadEnInventario) { this.cantidadEnInventario = cantidadEnInventario; }

    public String getCodigoDeBarras() { return codigoDeBarras; }
    public void setCodigoDeBarras(String codigoDeBarras) { this.codigoDeBarras = codigoDeBarras; }

    public String getUnidadMedida() { return unidadMedida; }
    public void setUnidadMedida(String unidadMedida) { this.unidadMedida = unidadMedida; }

    public String getProveedor() { return proveedor; }
    public void setProveedor(String proveedor) { this.proveedor = proveedor; }

    public Date getFechaCaducidad() { return fechaCaducidad; }
    public void setFechaCaducidad(Date fechaCaducidad) { this.fechaCaducidad = fechaCaducidad; }

    public Double getPrecioCompra() { return precioCompra; }
    public void setPrecioCompra(Double precioCompra) { this.precioCompra = precioCompra; }

    public Integer getStockMinimo() { return stockMinimo; }
    public void setStockMinimo(Integer stockMinimo) { this.stockMinimo = stockMinimo; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public Double getPeso() { return peso; }
    public void setPeso(Double peso) { this.peso = peso; }

    public Double getMargenGanancia() { return margenGanancia; }
    public void setMargenGanancia(Double margenGanancia) { this.margenGanancia = margenGanancia; }

    public String getCategoria() { return categoria; }
    public void setCategoria(String categoria) { this.categoria = categoria; }
}
