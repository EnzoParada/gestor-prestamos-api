package com.example.api_prestamos.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "Identificador único del cliente, generado automáticamente.", example = "101", accessMode = Schema.AccessMode.READ_ONLY)
    private long id;

    @Schema(description = "Primer nombre del cliente.", example = "Carla", required = true)
    private String nombre;

    @Schema(description = "Apellido del cliente.", example = "Sanchez", required = true)
    private String apellido;

    @Column(unique = true, nullable = false)
    @Schema(description = "Número de Documento Nacional de Identidad (DNI) único.", example = "35400123", required = true)
    private String dni;

    @Schema(description = "Estado de baja lógica del cliente. 'false' significa activo.", example = "false", defaultValue = "false")
    private boolean baja = false;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @Schema(description = "Lista de préstamos asociados a este cliente. Solo lectura.", accessMode = Schema.AccessMode.READ_ONLY)
    private List<Prestamo> prestamos;
}
