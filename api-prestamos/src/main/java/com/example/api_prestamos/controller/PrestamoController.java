package com.example.api_prestamos.controller;

import com.example.api_prestamos.model.Prestamo;
import com.example.api_prestamos.service.PrestamoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@Tag(name = "Préstamos", description = "Gestión de las solicitudes y estados de los préstamos.")
@RequiredArgsConstructor
public class PrestamoController {
    private final PrestamoService prestamoService;

    @Operation(
            summary = "Crear un nuevo préstamo para un cliente",
            description = "Asocia y registra un nuevo préstamo a un cliente específico usando su ID.",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Préstamo creado exitosamente"),
                    @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos"),
                    @ApiResponse(responseCode = "404", description = "Cliente no encontrado")
            }
    )
    @PostMapping("/clientes/{clienteId}/prestamos")
    public ResponseEntity<Prestamo> crearPrestamo(
            @PathVariable Long clienteId,
            @RequestBody Prestamo prestamo){
        Prestamo nuevoPrestamo = prestamoService.crearPrestamo(clienteId, prestamo);
        return  new ResponseEntity<>(nuevoPrestamo, HttpStatus.CREATED);
    }
    @Operation(
            summary = "Obtener préstamos por ID de cliente",
            description = "Devuelve una lista de todos los préstamos asociados a un cliente específico.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Lista de préstamos devuelta (puede estar vacía)"),
                    @ApiResponse(responseCode = "404", description = "Cliente no encontrado")
            }
    )
    @GetMapping("/clientes/{clienteId}/prestamos")
    public ResponseEntity<List<Prestamo>> obtenerPrestamosPorCliente(@PathVariable Long clienteId){
        List<Prestamo> prestamos = prestamoService.obtenerPrestamosPorClientes(clienteId);
        return new ResponseEntity<>(prestamos, HttpStatus.OK);
    }
}
