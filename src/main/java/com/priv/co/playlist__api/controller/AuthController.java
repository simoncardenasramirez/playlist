package com.priv.co.playlist__api.controller;

import com.priv.co.playlist__api.persistence.entity.UsuarioEntity;
import com.priv.co.playlist__api.service.JwtService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import java.util.Map;
import com.priv.co.playlist__api.service.UsuarioService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UsuarioService usuarios;
    private final AuthenticationManager authManager;
    private final JwtService jwt;

    public AuthController(UsuarioService usuarios, AuthenticationManager authManager, JwtService jwt) {
        this.usuarios = usuarios; this.authManager = authManager; this.jwt = jwt;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody UsuarioEntity body) {
        try {
            var saved = usuarios.registrar(body);
            // devolvemos el usuario con password encriptado
            return ResponseEntity.status(HttpStatus.CREATED).body(saved);
        } catch (Exception ex) {
            // Propaga los mensajes del servicio (400 por validación o email repetido)
            return ResponseEntity.status(
                            (ex instanceof org.springframework.web.server.ResponseStatusException rse)
                                    ? rse.getStatusCode() : HttpStatus.BAD_REQUEST)
                    .body(Map.of("error", ex.getMessage()));
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody UsuarioEntity body) {
        try {
            var auth = authManager.authenticate(
                    new UsernamePasswordAuthenticationToken(body.getEmail(), body.getPassword())
            );
            String token = jwt.generate(auth.getName());
            return ResponseEntity.ok(Map.of("token", token));
        } catch (BadCredentialsException ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("error", "Credenciales inválidas"));
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("error", "No fue posible autenticar"));
        }
    }
}
