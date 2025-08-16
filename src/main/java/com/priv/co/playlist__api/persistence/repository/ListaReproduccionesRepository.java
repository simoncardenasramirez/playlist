package com.priv.co.playlist__api.persistence.repository;

import com.priv.co.playlist__api.persistence.entity.ListaReproduccionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;


public interface ListaReproduccionesRepository extends JpaRepository<ListaReproduccionEntity, Long> {

    Optional<ListaReproduccionEntity> findByNombre(String nombre);

    boolean existsByNombre(String nombre);

    void deleteByNombre(String nombre);

    @Query("select distinct l from ListaReproduccionEntity l left join fetch l.canciones")
    List<ListaReproduccionEntity> findAllWithSongs();

    @Query("select l from ListaReproduccionEntity l left join fetch l.canciones where l.nombre = :nombre")
    Optional<ListaReproduccionEntity> findByNombreWithSongs(@Param("nombre") String nombre);
}
