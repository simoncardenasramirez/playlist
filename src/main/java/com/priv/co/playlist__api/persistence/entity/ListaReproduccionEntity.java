package com.priv.co.playlist__api.persistence.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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

    @JsonIgnore
    @JsonManagedReference
    @OneToMany(mappedBy = "listaReproduccion", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CancionEntity> canciones = new ArrayList<>();



    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public List<CancionEntity> getCanciones() { return canciones; }
    public void setCanciones(List<CancionEntity> canciones) { this.canciones = canciones; }
}

