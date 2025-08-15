package com.literalura.livros.dto;

public record AutorDTO(
        String nome,
        Integer ano_nascimento,
        Integer ano_falecimento
) {}
