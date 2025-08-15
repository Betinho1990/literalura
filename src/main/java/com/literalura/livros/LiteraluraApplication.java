package com.literalura.livros;

import com.literalura.livros.model.Autor;
import com.literalura.livros.model.Livro;
import com.literalura.livros.repository.AutorRepository;
import com.literalura.livros.repository.LivroRepository;
import com.literalura.livros.client.GutendexClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.domain.PageRequest;

import java.util.List;
import java.util.Scanner;

@SpringBootApplication
public class LiteraluraApplication implements CommandLineRunner {

	@Autowired
	private GutendexClient gutendexClient;

	@Autowired
	private LivroRepository livroRepository;

	@Autowired
	private AutorRepository autorRepository;

	public static void main(String[] args) {
		SpringApplication.run(LiteraluraApplication.class, args);
	}

	@Override
	public void run(String... args) {
		Scanner sc = new Scanner(System.in);
		int opcao = -1;

		while (opcao != 0) {
			System.out.println("""
                    \nMenu:
                    1 - Buscar e salvar livro por título exato
                    2 - Listar todos os livros
                    3 - Listar autores vivos em um ano
                    4 - Exibir quantidade de livros por idioma
                    5 - Top 10 livros mais baixados
                    6 - Total de autores
                    0 - Sair
                    """);

			try {
				opcao = Integer.parseInt(sc.nextLine());
			} catch (NumberFormatException e) {
				System.out.println("Digite um número válido.");
				continue;
			}

			switch (opcao) {
				case 1 -> buscarLivro(sc);
				case 2 -> listarLivros();
				case 3 -> listarAutoresVivos(sc);
				case 4 -> listarLivrosPorIdioma(sc);
				case 5 -> listarTop10();
				case 6 -> listarAutores();
				case 0 -> System.out.println("Saindo...");
				default -> System.out.println("Opção inválida!");
			}
		}
	}

	// Buscar e salvar livro por título exato
	private void buscarLivro(Scanner sc) {
		System.out.print("Digite o título exato: ");
		String titulo = sc.nextLine();

		Livro livro = gutendexClient.buscarLivrosPorTituloExato(titulo);

		if (livro == null) {
			System.out.println("Livro não encontrado na API.");
			return;
		}

// Verifica se já existe no banco
		if (!livroRepository.existsByTituloIgnoreCase(livro.getTitulo())) {
			livroRepository.save(livro);
			System.out.println("Livro salvo no banco: " + livro);
		} else {
			System.out.println("Livro já está no banco: " + livro);
		}
	}

	// Listar todos os livros
	private void listarLivros() {
		livroRepository.findAll().forEach(System.out::println);
	}

	// Listar autores vivos em um ano
	private void listarAutoresVivos(Scanner sc) {
		System.out.print("Digite o ano: ");
		try {
			int ano = Integer.parseInt(sc.nextLine());
			autorRepository.findByAnoNascimentoLessThanEqualAndAnoFalecimentoGreaterThanEqual(ano, ano)
					.forEach(System.out::println);
		} catch (NumberFormatException e) {
			System.out.println("Ano inválido.");
		}
	}

	// Listar livros por idioma
	private void listarLivrosPorIdioma(Scanner sc) {
		System.out.print("Digite o idioma (ex: 'en', 'pt'): ");
		String idioma = sc.nextLine();
		List<Livro> livros = livroRepository.findByIdioma(idioma);
		System.out.println("Total: " + livros.size());
		livros.forEach(System.out::println);
	}

	// Listar top 10 livros mais baixados
	private void listarTop10() {
		livroRepository.findAllByOrderByDownloadCountDesc(PageRequest.of(0, 10))
				.forEach(System.out::println);
	}

	// Total de autores no banco
	private void listarAutores() {
		List<Autor> autores = autorRepository.findAll();
		if (autores.isEmpty()) {
			System.out.println("Nenhum autor cadastrado no banco.");
		} else {
			System.out.println("Autores no banco:");
			autores.forEach(a -> System.out.println(
					"ID: " + a.getId() +
							", Nome: " + a.getNome() +
							", Ano Nascimento: " + a.getAnoNascimento() +
							", Ano Falecimento: " + a.getAnoFalecimento()
			));
		}
	}
}
