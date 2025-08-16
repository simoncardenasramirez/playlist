package com.priv.co.playlist__api.service;


import com.priv.co.playlist__api.persistence.entity.CancionEntity;
import com.priv.co.playlist__api.persistence.entity.ListaReproduccionEntity;
import com.priv.co.playlist__api.persistence.repository.CancionRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class CancionService {

    private final CancionRepository cancionRepo;

    public CancionService(CancionRepository cancionRepo) {
        this.cancionRepo = cancionRepo;
    }

    public void validarCampos(CancionEntity c) {
        if (c == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "canción inválida");
        }
        if (c.getTitulo() == null || c.getTitulo().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "titulo es obligatorio");
        }
        if (c.getArtista() == null || c.getArtista().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "artista es obligatorio");
        }
    }

    public CancionEntity agregarA(ListaReproduccionEntity lista, CancionEntity c) {
        validarCampos(c);
        c.setLista(lista);
        return cancionRepo.save(c);
    }


    public void eliminarDeLista(String listName, Long songId) {
        var cancion = cancionRepo.findByIdAndListaReproduccion_Nombre(songId, listName)
                .orElseThrow(() ->
                        new ResponseStatusException(HttpStatus.NOT_FOUND, "canción no encontrada en esa lista"));
        cancionRepo.delete(cancion);
    }
}

