package com.literalura.livros.controller;

import com.literalura.livros.model.Livro;
import com.literalura.livros.repository.LivroRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/livros")
public class LivroController {

    private final LivroRepository livroRepository;

    public LivroController(LivroRepository livroRepository) {
        this.livroRepository = livroRepository;
    }

    @GetMapping
    public List<Livro> listarTodos() {
        return livroRepository.findAll();
    }

    @GetMapping("/{idioma}")
    public List<Livro> listarPorIdioma(@PathVariable String idioma) {
        return livroRepository.findByIdioma(idioma);
    }

    @PostMapping
    public Livro salvar(@RequestBody Livro livro) {
        return livroRepository.save(livro);
    }
}