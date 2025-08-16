package com.priv.co.playlist__api.persistence.entity;


import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "listas")
public class ListaReproduccionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String nombre;

    private String descripcion;

    // Debe coincidir con el nombre del atributo en CancionEntity: "lista"
    @OneToMany(mappedBy = "lista", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CancionEntity> canciones = new ArrayList<>();

    // --- helpers opcionales para mantener bidireccionalidad ---
    public void addCancion(CancionEntity c) {
        canciones.add(c);
        c.setLista(this);
    }

    public void removeCancion(CancionEntity c) {
        canciones.remove(c);
        c.setLista(null);
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public List<CancionEntity> getCanciones() { return canciones; }
    public void setCanciones(List<CancionEntity> canciones) { this.canciones = canciones; }
}

