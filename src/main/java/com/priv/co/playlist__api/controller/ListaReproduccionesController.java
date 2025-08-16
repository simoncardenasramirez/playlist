// com/priv/co/playlist__api/controller/ListaReproduccionesController.java
package com.priv.co.playlist__api.controller;

import com.priv.co.playlist__api.persistence.entity.ListaReproduccionEntity;
import com.priv.co.playlist__api.service.ListaReproduccionesService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/lists")
public class ListaReproduccionesController {

    private final ListaReproduccionesService service;

    public ListaReproduccionesController(ListaReproduccionesService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<?> crear(@RequestBody ListaReproduccionEntity body,
                                   UriComponentsBuilder uriBuilder) {
        try {
            var creada = service.crear(body);
            URI location = uriBuilder
                    .path("/lists/{name}")
                    .buildAndExpand(creada.getNombre())
                    .encode()            // <-- importante si hay espacios o tildes
                    .toUri();
            return ResponseEntity.created(location).body(creada);
        } catch (org.springframework.web.server.ResponseStatusException ex) {
            return ResponseEntity.status(ex.getStatusCode())
                    .body(Map.of("error", ex.getReason()));
        } catch (Exception ex) {
            return ResponseEntity.internalServerError()
                    .body(Map.of("error", "Error interno", "detail", ex.getMessage()));
        }
    }

    @GetMapping
    public ResponseEntity<List<ListaReproduccionEntity>> listar() {
        return ResponseEntity.ok(service.listar());
    }

    @GetMapping("/{listName}")
    public ResponseEntity<?> obtener(@PathVariable String listName) {
        try {
            return ResponseEntity.ok(service.obtenerPorNombre(listName));
        } catch (org.springframework.web.server.ResponseStatusException ex) {
            return ResponseEntity.status(ex.getStatusCode())
                    .body(Map.of("error", ex.getReason()));
        }
    }

    @DeleteMapping("/{listName}")
    public ResponseEntity<?> eliminar(@PathVariable String listName) {
        try {
            service.eliminarPorNombre(listName);
            return ResponseEntity.noContent().build();
        } catch (org.springframework.web.server.ResponseStatusException ex) {
            return ResponseEntity.status(ex.getStatusCode())
                    .body(Map.of("error", ex.getReason()));
        }
    }
}
