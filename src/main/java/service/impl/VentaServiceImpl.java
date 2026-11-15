package service.impl;

import dto.request.VentaRequestDTO;
import dto.response.DetalleVentaResponseDTO;
import dto.response.VentaResponseDTO;
import model.entity.DetalleVenta;
import model.entity.Venta;
import repository.VentaRepository;
import service.CalculadoraVenta;
import service.VentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class VentaServiceImpl implements VentaService {
    @Autowired
    private CalculadoraVenta calculadoraVenta;

    @Autowired
    private VentaRepository ventaRepository;

    @Override
    @Transactional
    public VentaResponseDTO procesarVenta(VentaRequestDTO request) {
        request.validar();

        VentaResponseDTO response = calculadoraVenta.calcular(request.getItems(), request.getMetodoPago());

        Venta ventaEntity = new Venta();
        ventaEntity.setSubtotal(response.getSubtotal());
        ventaEntity.setDescuento(response.getDescuento());
        ventaEntity.setSubtotalConDescuento(response.getSubtotalConDescuento());
        ventaEntity.setIgv(response.getIgv());
        ventaEntity.setComisionTarjeta(response.getComisionTarjeta());
        ventaEntity.setTotalFinal(response.getTotalFinal());
        ventaEntity.setMetodoPago(response.getMetodoPago());

        List<DetalleVenta> listaDetalles = new ArrayList<>();
        for (DetalleVentaResponseDTO itemResp : response.getItems()) {
            DetalleVenta detalle = new DetalleVenta();
            detalle.setVenta(ventaEntity);
            detalle.setProducto(itemResp.getProducto());
            detalle.setCantidad(itemResp.getCantidad());
            detalle.setPrecioUnitario(itemResp.getPrecioUnitario());
            detalle.setSubtotalItem(itemResp.getSubtotalItem());
            listaDetalles.add(detalle);
        }
        ventaEntity.setDetalles(listaDetalles);

        ventaRepository.save(ventaEntity);

        return response;
    }
}