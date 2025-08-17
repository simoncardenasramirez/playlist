package com.priv.co.playlist__api.service;

import com.priv.co.playlist__api.persistence.entity.CancionEntity;
import com.priv.co.playlist__api.persistence.entity.ListaReproduccionEntity;
import com.priv.co.playlist__api.persistence.repository.ListaReproduccionesRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Comparator;
import java.util.List;

@Service
public class ListaReproduccionesService {

    private final ListaReproduccionesRepository listasRepo;
    private final CancionService cancionService;

    public ListaReproduccionesService(
            ListaReproduccionesRepository listasRepo,
            CancionService cancionService) {
        this.listasRepo = listasRepo;
        this.cancionService = cancionService;
    }

    public ListaReproduccionEntity crear(ListaReproduccionEntity input) {
        if (input == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "payload requerido");
        }
        if (input.getNombre() == null || input.getNombre().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "nombre es obligatorio");
        }
        if (listasRepo.existsByNombre(input.getNombre())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ya existe una lista con ese nombre");
        }

        if (input.getCanciones() != null) {
            for (CancionEntity c : input.getCanciones()) {
                cancionService.validarCampos(c);
                c.setLista(input);
            }
        }

        return listasRepo.save(input);
    }

    public List<ListaReproduccionEntity> listarConCanciones() {
        return listasRepo.findAllWithSongs()
                .stream()
                .sorted(Comparator.comparing(ListaReproduccionEntity::getNombre))
                .toList();
    }


    public ListaReproduccionEntity obtenerPorNombre(String nombre) {
        if (nombre == null || nombre.isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "nombre inválido");
        }
        return listasRepo.findByNombre(nombre)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "lista no encontrada"));
    }


    public void eliminarPorNombre(String nombre) {
        var lista = listasRepo.findByNombre(nombre)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "lista no encontrada"));
        listasRepo.delete(lista);
    }


    public CancionEntity agregarCancion(String listName, CancionEntity c) {
        var lista = obtenerPorNombre(listName);
        return cancionService.agregarA(lista, c);
    }

    public void eliminarCancion(String listName, Long songId) {
        cancionService.eliminarDeLista(listName, songId);
    }
}
