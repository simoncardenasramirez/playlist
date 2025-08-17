package com.priv.co.playlist__api.persistence.repository;

import com.priv.co.playlist__api.persistence.entity.ListaReproduccionEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;


public interface ListaReproduccionesRepository
        extends JpaRepository<ListaReproduccionEntity, Long> {

    @EntityGraph(attributePaths = "canciones") //
    @Query("select l from ListaReproduccionEntity l")
    List<ListaReproduccionEntity> findAllWithSongs();

    @EntityGraph(attributePaths = "canciones")
    Optional<ListaReproduccionEntity> findByNombre(String nombre);

    boolean existsByNombre(String nombre);
    void deleteByNombre(String nombre);
}

