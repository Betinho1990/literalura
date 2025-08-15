package com.literalura.livros.service;

import com.literalura.livros.client.GutendexClient;
import com.literalura.livros.model.Livro;
import com.literalura.livros.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LivroService {

    @Autowired
    private LivroRepository livroRepository;

    @Autowired
    private GutendexClient gutendexClient;

    public List<Livro> listarLivros() {
        return livroRepository.findAll();
    }

    public void importarLivro(String titulo) {
        Livro livro = gutendexClient.buscarLivrosPorTituloExato(titulo);
        if (livro != null && !livroRepository.existsByTituloIgnoreCase(livro.getTitulo())) {
            livroRepository.save(livro);
        }
    }
}
