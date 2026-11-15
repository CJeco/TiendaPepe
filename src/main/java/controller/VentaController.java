package controller;

import dto.request.VentaRequestDTO;
import dto.response.VentaResponseDTO;
import service.VentaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/ventas")
public class VentaController {
    @Autowired
    private VentaService ventaService;

    @PostMapping("/calcular")
    public ResponseEntity<VentaResponseDTO> calcularVenta(@RequestBody VentaRequestDTO request) {
        VentaResponseDTO response = ventaService.procesarVenta(request);
        return ResponseEntity.ok(response);
    }
}