# 📚 Literalura

**Literalura** é uma aplicação Java Spring Boot que te permite explorar livros do [Project Gutenberg](https://www.gutenberg.org/) via [API Gutendex](https://gutendex.com/).  
Ela salva livros e autores no PostgreSQL e permite consultas inteligentes diretamente pelo terminal.

---

## 🚀 Funcionalidades

- 🔍 **Buscar e salvar livros por título exato**  
- 📖 **Listar todos os livros**  
- 👤 **Autores vivos em um determinado ano**  
- 🌐 **Quantidade de livros por idioma**  
- 🏆 **Top 10 livros mais baixados**  
- ✍️ **Listar todos os autores**

## 🛠 Tecnologias

- Java 17
- Spring Boot + Spring Data JPA
- PostgreSQL
- RestTemplate (consumo da API)
- Maven (gerenciamento de dependências)

---

## ⚙️ Configuração do Banco

1. Crie o banco PostgreSQL e configure variáveis de ambiente:

```bash
# Windows
setx POSTGRES_USER "seu_usuario"
setx POSTGRES_PASSWORD "sua_senha"
setx POSTGRES_DB "nome_do_banco"

# Linux / Mac
export POSTGRES_USER=seu_usuario
export POSTGRES_PASSWORD=sua_senha
export POSTGRES_DB=nome_do_banco

```
2. Configure application.properties:

```bash

spring.datasource.url=jdbc:postgresql://localhost:5432/${POSTGRES_DB}
spring.datasource.username=${POSTGRES_USER}
spring.datasource.password=${POSTGRES_PASSWORD}
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

```

## 🚀 Rodando a Aplicação

```bash

git clone https://github.com/Betinho1990/literalura.git
cd literalura
mvn spring-boot:run

```
Use o menu no terminal para interagir com a aplicação.

## 📂 Estrutura do Projeto

```bash

literalura/
├── client/       # Cliente Gutendex
├── model/        # Entidades Livro e Autor
├── repository/   # Repositórios JPA
├── service/      # Serviços
└── LiteraluraApplication.java  # Classe principal com menu

```

## 💡 Diferenciais

---

- Normalização de títulos para evitar problemas com acentos e maiúsculas/minúsculas.
- Persistência automática no PostgreSQL.
- Simples de usar via terminal.
- Fácil de estender para filtros e novos recursos.

---

## 🔗 Referências

---

- API Gutendex
- Project Gutenberg
- Spring Boot
- Spring Data JPA

---

 ## 📄 Licença

 MIT License © Roberto Filho




