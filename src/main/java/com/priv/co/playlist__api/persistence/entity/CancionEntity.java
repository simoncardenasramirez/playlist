package com.priv.co.playlist__api.persistence.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "canciones")
public class CancionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String artista;
    private String album;
    private String anno;   // si prefieres año numérico, cámbialo a Integer
    private String genero;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lista_id", nullable = false) // FK en la tabla canciones
    private ListaReproduccionEntity lista;

    public CancionEntity() {}

    public CancionEntity(Long id, String titulo, String artista, String album, String anno, String genero) {
        this.id = id;
        this.titulo = titulo;
        this.artista = artista;
        this.album = album;
        this.anno = anno;
        this.genero = genero;
    }

    // --- getters/setters ---
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getArtista() { return artista; }
    public void setArtista(String artista) { this.artista = artista; }

    public String getAlbum() { return album; }
    public void setAlbum(String album) { this.album = album; }

    public String getAnno() { return anno; }
    public void setAnno(String anno) { this.anno = anno; }

    public String getGenero() { return genero; }
    public void setGenero(String genero) { this.genero = genero; }

    public ListaReproduccionEntity getLista() { return lista; }
    public void setLista(ListaReproduccionEntity lista) { this.lista = lista; }
}
