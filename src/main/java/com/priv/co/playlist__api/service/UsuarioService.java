package com.priv.co.playlist__api.service;

import com.priv.co.playlist__api.persistence.entity.UsuarioEntity;
import com.priv.co.playlist__api.persistence.repository.UsuarioRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UsuarioService {

    private final UsuarioRepository repo;
    private final PasswordEncoder encoder;

    public UsuarioService(UsuarioRepository repo, PasswordEncoder encoder) {
        this.repo = repo; this.encoder = encoder;
    }

    public UsuarioEntity registrar(UsuarioEntity in) {
        if (in == null || in.getEmail()==null || in.getEmail().isBlank()
                || in.getPassword()==null || in.getPassword().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "email y password requeridos");
        }
        if (repo.existsByEmail(in.getEmail())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "email ya registrado");
        }
        in.setPassword(encoder.encode(in.getPassword()));
        return repo.save(in);
    }

    public Optional<UsuarioEntity> findByEmail(String email) {
        return repo.findByEmail(email);
    }
}

