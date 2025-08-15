package com.literalura.livros.model;

import jakarta.persistence.*;

@Entity
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String idioma;
    private Integer downloadCount;

    @ManyToOne(cascade = CascadeType.ALL)
    private Autor autor;

    public Livro() { }

    public Livro(String titulo, String idioma, Integer downloadCount, Autor autor) {
        this.titulo = titulo;
        this.idioma = idioma;
        this.downloadCount = downloadCount;
        this.autor = autor;
    }

    public Long getId() { return id; }
    public String getTitulo() { return titulo; }
    public String getIdioma() { return idioma; }
    public Integer getDownloadCount() { return downloadCount; }
    public Autor getAutor() { return autor; }

    @Override
    public String toString() {
        return titulo + " - " + idioma + " - downloads: " + downloadCount + " - Autor: " + autor;
    }
}
