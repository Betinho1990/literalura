package com.literalura.livros.repository;

import com.literalura.livros.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AutorRepository extends JpaRepository<Autor, Long> {

    // Consulta todos os nomes de autores
    @Query("SELECT a.nome FROM Autor a")
    List<String> findAllNomesAutores();

    // Consulta autores vivos em determinado ano
    List<Autor> findByAnoNascimentoLessThanEqualAndAnoFalecimentoGreaterThanEqual(int anoNascimento, int anoFalecimento);


}