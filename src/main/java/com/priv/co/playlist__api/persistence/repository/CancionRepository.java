package com.priv.co.playlist__api.persistence.repository;

import com.priv.co.playlist__api.persistence.entity.CancionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CancionRepository extends JpaRepository<CancionEntity, Long> {
}
