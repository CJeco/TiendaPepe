
package com.tiendapepe.Tiendapepe.dto.request;

import com.tiendapepe.Tiendapepe.exception.ValidacionException;
import java.util.List;

public class VentaRequestDTO {
    private List<DetalleVentaRequestDTO> items;
    private String metodoPago;

    public void validar() {
        if (items == null || items.isEmpty()) {
            throw new ValidacionException("La lista de items no puede ser nula ni vacía.");
        }
        for (DetalleVentaRequestDTO item : items) {
            item.validar();
        }
        if (metodoPago == null || metodoPago.trim().isEmpty()) {
            throw new ValidacionException("El método de pago no puede ser nulo ni vacío.");
        }
        String metodoUpper = metodoPago.trim().toUpperCase();
        if (!metodoUpper.equals("EFECTIVO") && !metodoUpper.equals("TARJETA")) {
            throw new ValidacionException("El método de pago solo puede ser TARJETA o EFECTIVO.");
        }
        this.metodoPago = metodoUpper;
    }

    // Getters y Setters
    public List<DetalleVentaRequestDTO> getItems() { return items; }
    public void setItems(List<DetalleVentaRequestDTO> items) { this.items = items; }
    public String getMetodoPago() { return metodoPago; }
    public void setMetodoPago(String metodoPago) { this.metodoPago = metodoPago; }
}

