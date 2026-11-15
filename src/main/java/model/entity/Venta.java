package model.entity;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "venta")
@Data
public class Venta {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private double subtotal;
    private double descuento;

    @Column(name = "subtotal_con_descuento")
    private double subtotalConDescuento;

    private double igv;

    @Column(name = "comision_tarjeta")
    private double comisionTarjeta;

    @Column(name = "total_final")
    private double totalFinal;

    @Column(name = "metodo_pago")
    private String metodoPago;

    @Column(name = "fecha_venta")
    private LocalDateTime fechaVenta = LocalDateTime.now();

    @OneToMany(mappedBy = "venta", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<DetalleVenta> detalles;
}