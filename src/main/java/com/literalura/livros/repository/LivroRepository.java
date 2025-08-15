package com.literalura.livros.repository;

import com.literalura.livros.model.Livro;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LivroRepository extends JpaRepository<Livro, Long> {

    // Consulta livros por idioma
    List<Livro> findByIdioma(String idioma);

    // Consulta livros por título ignorando maiúsculas/minúsculas
    boolean existsByTituloIgnoreCase(String titulo);

    // Top N livros mais baixados
    List<Livro> findAllByOrderByDownloadCountDesc(Pageable pageable);


}
