package dto.response;

import java.util.List;

public class VentaResponseDTO {
    private List<DetalleVentaResponseDTO> items;
    private double subtotal;
    private double descuento;
    private double subtotalConDescuento;
    private double igv;
    private double comisionTarjeta;
    private double totalFinal;
    private String metodoPago;

    public List<DetalleVentaResponseDTO> getItems() { return items; }
    public void setItems(List<DetalleVentaResponseDTO> items) { this.items = items; }
    public double getSubtotal() { return subtotal; }
    public void setSubtotal(double subtotal) { this.subtotal = subtotal; }
    public double getDescuento() { return descuento; }
    public void setDescuento(double descuento) { this.descuento = descuento; }
    public double getSubtotalConDescuento() { return subtotalConDescuento; }
    public void setSubtotalConDescuento(double subtotalConDescuento) { this.subtotalConDescuento = subtotalConDescuento; }
    public double getIgv() { return igv; }
    public void setIgv(double igv) { this.igv = igv; }
    public double getComisionTarjeta() { return comisionTarjeta; }
    public void setComisionTarjeta(double comisionTarjeta) { this.comisionTarjeta = comisionTarjeta; }
    public double getTotalFinal() { return totalFinal; }
    public void setTotalFinal(double totalFinal) { this.totalFinal = totalFinal; }
    public String getMetodoPago() { return metodoPago; }
    public void setMetodoPago(String metodoPago) { this.metodoPago = metodoPago; }
}