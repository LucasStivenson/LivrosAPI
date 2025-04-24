# Exercício - Sistema de Controle de Empréstimos de Livros

Este repositório contém a implementação de um sistema de controle de empréstimos de livros, desenvolvido como parte da disciplina **Desenvolvimento de Sistemas Computacionais** - **Turma 4A (2025/1)** do **IFSUL - Campus Sapucaia do Sul**.

## Descrição do Projeto

A aplicação consiste em uma **API REST** que permite:

- Cadastrar livros disponíveis na biblioteca
- Registrar empréstimos e devoluções de livros
- Listar livros disponíveis e todos os empréstimos realizados

## Regras de Negócio

- Um livro só pode ser emprestado se estiver disponível.
- Ao ser emprestado, o livro se torna indisponível.
- Ao ser devolvido, o livro volta a estar disponível.
- Cada empréstimo registra o usuário, a data de empréstimo e, posteriormente, a data de devolução.

## Tecnologias Utilizadas

- **Java 17+**
- **Spring Boot**
- **Spring Web**
- **Maven**

## Estrutura de Classes

- **Livro**  
  - `id`  
  - `titulo`  
  - `autor`  
  - `disponivel` (booleano)

- **Emprestimo**  
  - `id`  
  - `usuario`  
  - `dataEmprestimo`  
  - `dataDevolucao` (opcional)  
  - `livros` (Lista de livros emprestados, neste caso 1 por empréstimo)

## Endpoints Disponíveis

- **POST** `/livros/inserir`  
  Cadastra um novo livro na biblioteca.

- **GET** `/livros/listar`  
  Retorna a lista de todos os livros cadastrados.

- **GET** `/livros/livrosdispo`  
  Retorna todos os livros disponíveis para empréstimo.

- **POST** `/livros/emprestimos?livroId={id}&usuario={nome}`  
  Registra o empréstimo de um livro para um usuário.

- **PUT** `/livros/emprestimos/devolver/{id}`  
  Registra a devolução de um livro pelo ID.

- **GET** `/livros/emprestimos`  
  Lista todos os empréstimos realizados, com datas e usuários.
