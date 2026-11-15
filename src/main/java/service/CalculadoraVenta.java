package service;

import dto.request.DetalleVentaRequestDTO;
import dto.response.DetalleVentaResponseDTO;
import dto.response.VentaResponseDTO;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

@Component
public class CalculadoraVenta {

    private static final double TASA_IGV = 0.18;
    private static final double UMBRAL_DESCUENTO = 500.00;
    private static final double TASA_DESCUENTO = 0.10;
    private static final double TASA_COMISION = 0.03;

    public VentaResponseDTO calcular(List<DetalleVentaRequestDTO> itemsReq, String metodoPago) {
        double subtotalGeneral = 0.0;
        List<DetalleVentaResponseDTO> detallesResp = new ArrayList<>();

        for (DetalleVentaRequestDTO itemReq : itemsReq) {
            double subtotalItem = redondear(itemReq.getCantidad() * itemReq.getPrecioUnitario());
            subtotalGeneral += subtotalItem;

            DetalleVentaResponseDTO detalleResp = new DetalleVentaResponseDTO();
            detalleResp.setProducto(itemReq.getProducto());
            detalleResp.setCantidad(itemReq.getCantidad());
            detalleResp.setPrecioUnitario(redondear(itemReq.getPrecioUnitario()));
            detalleResp.setSubtotalItem(subtotalItem);
            detallesResp.add(detalleResp);
        }

        subtotalGeneral = redondear(subtotalGeneral);

        // Descuento de 10% si supera S/ 500 antes del IGV
        double descuento = 0.0;
        if (subtotalGeneral > UMBRAL_DESCUENTO) {
            descuento = redondear(subtotalGeneral * TASA_DESCUENTO);
        }
        double subtotalConDescuento = redondear(subtotalGeneral - descuento);

        // IGV (18%) sobre subtotal con descuento
        double igv = redondear(subtotalConDescuento * TASA_IGV);
        double totalConIgv = redondear(subtotalConDescuento + igv);

        // Comisión por tarjeta (3%) si aplica
        double comisionTarjeta = 0.0;
        if ("TARJETA".equals(metodoPago)) {
            comisionTarjeta = redondear(totalConIgv * TASA_COMISION);
        }

        double totalFinal = redondear(totalConIgv + comisionTarjeta);

        VentaResponseDTO response = new VentaResponseDTO();
        response.setItems(detallesResp);
        response.setSubtotal(subtotalGeneral);
        response.setDescuento(descuento);
        response.setSubtotalConDescuento(subtotalConDescuento);
        response.setIgv(igv);
        response.setComisionTarjeta(comisionTarjeta);
        response.setTotalFinal(totalFinal);
        response.setMetodoPago(metodoPago);

        return response;
    }

    private double redondear(double valor) {
        return BigDecimal.valueOf(valor)
                .setScale(2, RoundingMode.HALF_UP)
                .doubleValue();
    }
}