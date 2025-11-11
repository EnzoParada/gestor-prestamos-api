package com.example.api_prestamos.controller;

import com.example.api_prestamos.model.Cliente;
import com.example.api_prestamos.service.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clientes")
@Tag(name = "Clientes", description = "Gestion de clientes")
@RequiredArgsConstructor
public class ClienteController {
    private final ClienteService clienteService;

    @Operation(
            summary = "Crear un nuevo cliente.",
            description = "Crea un nuevo cliente asignandole un id.",
            responses = {
                @ApiResponse(responseCode = "201", description = "Cliente creado exitosamente."),
                @ApiResponse(responseCode = "400", description = "Datos de cliente inválidos.")
    }
    )
    @PostMapping
    public ResponseEntity<Cliente> crearCliete(@RequestBody Cliente cliente){
        return new ResponseEntity<>(clienteService.crearCliente(cliente), HttpStatus.CREATED);
    }

    @Operation(
            summary = "Obtener todos los clientes.",
            description = "Devuelve una lista de todos los clientes registrados en el sistema.",
            responses = {
                  @ApiResponse (responseCode = "200", description = "Lista de clientes devuelta (puede estar vacía)")
            }
            )
    @GetMapping
    public ResponseEntity<List<Cliente>> obtenerTodosLosCLientes(){
        return  new ResponseEntity<>(clienteService.obtenerTodosLosClientes(), HttpStatus.OK);
    }

    @Operation(
            summary = "Obtener clientes por id",
            description ="Busca y devuelve un cliente específico usando su identificador único.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Cliente encontrado"),
                    @ApiResponse(responseCode = "404", description = "Cliente no encontrado con ese ID")
            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<Cliente> obtenerClientePorId(@PathVariable Long id){
        return  clienteService.obtenerClientePorId(id)
                .map(cliente -> new ResponseEntity<>(cliente, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @Operation(
            summary = "Actualizar un cliente existente",
            description = "Actualiza los detalles de un cliente existente usando su ID.",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Cliente actualizado exitosamente"),
                    @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos"),
                    @ApiResponse(responseCode = "404", description = "Cliente no encontrado con ese ID")
            }
    )
    @PutMapping("/{id}")
    public ResponseEntity<Cliente> actualizarCliente(
            @PathVariable Long id,
            @RequestBody Cliente clienteDetalles){

        return clienteService.actualizarCliente(id, clienteDetalles)
                .map(clienteActualizado -> new ResponseEntity<>(clienteActualizado, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @Operation(
            summary = "Eliminar un cliente",
            description = "Elimina permanentemente un cliente del sistema usando su ID.",
            responses = {
                    @ApiResponse(responseCode = "204", description = "Cliente eliminado exitosamente (No Content)"),
                    @ApiResponse(responseCode = "404", description = "Cliente no encontrado con ese ID")
            }
    )
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> bajaCliente(@PathVariable Long id){
        clienteService.eliminarCliente(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
