package com.priv.co.playlist__api.persistence.repository;

import com.priv.co.playlist__api.persistence.entity.CancionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CancionRepository extends JpaRepository<CancionEntity, Long> {

    Optional<CancionEntity> findByIdAndLista_Nombre(Long id, String nombreLista);

    List<CancionEntity> findByLista_Nombre(String nombreLista);
}


