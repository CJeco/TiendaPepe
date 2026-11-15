package service;

import dto.request.VentaRequestDTO;
import dto.response.VentaResponseDTO;

public interface VentaService {
    VentaResponseDTO procesarVenta(VentaRequestDTO request);
}