package dto.request;

import exception.ValidacionException;

public class DetalleVentaRequestDTO {
    private String producto;
    private int cantidad;
    private double precioUnitario;

    public void validar() {
        if (producto == null || producto.trim().isEmpty()) {
            throw new ValidacionException("El nombre del producto no puede ser nulo ni vacío.");
        }
        if (cantidad <= 0) {
            throw new ValidacionException("La cantidad debe ser mayor a 0.");
        }
        if (precioUnitario <= 0) {
            throw new ValidacionException("El precio unitario debe ser mayor a 0.");
        }
    }

    public String getProducto() { return producto; }
    public void setProducto(String producto) { this.producto = producto; }
    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }
    public double getPrecioUnitario() { return precioUnitario; }
    public void setPrecioUnitario(double precioUnitario) { this.precioUnitario = precioUnitario; }
}