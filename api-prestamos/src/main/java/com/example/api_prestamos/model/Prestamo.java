package com.example.api_prestamos.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Data
@NoArgsConstructor
@Schema
public class Prestamo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Identificador único del prestamo, generado automáticamente.", example = "101", accessMode = Schema.AccessMode.READ_ONLY)
    private long id;

    @Column(nullable = false)
    @Schema(description = "Monto total del dinero solicitado para el préstamo.", example = "15000.50", required = true)
    private BigDecimal monto;
    @Schema(description = "Tasa de interés anual aplicada al préstamo (en porcentaje).", example = "0.08")
    private double tasaInteres;
    @Schema(description = "Plazo de devolución del préstamo en meses.", example = "24", required = true)
    private int plazoMeses;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cliente_id", nullable = false)
    @JsonIgnore
    @Schema(description = "Referencia al cliente asociado con este préstamo. Se maneja por el ID en la URL de POST.", accessMode = Schema.AccessMode.READ_ONLY)
    private Cliente cliente;
}
