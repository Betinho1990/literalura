package com.literalura.livros.client;

import com.literalura.livros.model.Autor;
import com.literalura.livros.model.Livro;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

@Component
public class GutendexClient {

    private static final String API_URL = "https://gutendex.com/books/?search=";

    public Livro buscarLivrosPorTituloExato(String titulo) {
        try {
            // Normaliza o título digitado
            String tituloNormalizado = java.text.Normalizer
                    .normalize(titulo, java.text.Normalizer.Form.NFD)
                    .replaceAll("\\p{M}", "")
                    .toLowerCase();

            RestTemplate restTemplate = new RestTemplate();
            String urlTitulo = URLEncoder.encode(titulo, StandardCharsets.UTF_8);
            String json = restTemplate.getForObject(API_URL + urlTitulo, String.class);

            ObjectMapper mapper = new ObjectMapper();
            JsonNode root = mapper.readTree(json);
            JsonNode results = root.path("results");

            if (results.isArray()) {
                for (JsonNode livroNode : results) {
                    String tituloLivro = livroNode.path("title").asText();

                    // Normaliza o título retornado
                    String tituloLivroNormalizado = java.text.Normalizer
                            .normalize(tituloLivro, java.text.Normalizer.Form.NFD)
                            .replaceAll("\\p{M}", "")
                            .toLowerCase();

                    if (tituloLivroNormalizado.equals(tituloNormalizado)) {
                        String idioma = livroNode.path("languages").get(0).asText();
                        int downloads = livroNode.path("download_count").asInt();

                        JsonNode autorNode = livroNode.path("authors").get(0);
                        String nomeAutor = autorNode.path("name").asText();
                        Integer nascimento = autorNode.path("birth_year").isInt()
                                ? autorNode.path("birth_year").asInt()
                                : null;
                        Integer falecimento = autorNode.path("death_year").isInt()
                                ? autorNode.path("death_year").asInt()
                                : null;

                        Autor autor = new Autor(nomeAutor, nascimento, falecimento);
                        return new Livro(tituloLivro, idioma, downloads, autor);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}