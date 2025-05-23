package br.edu.ifsul.livro.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.edu.ifsul.livro.model.Emprestimo;
import br.edu.ifsul.livro.model.Livro;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/livros")
public class EmprestimoController {
    private List<Livro> livros = new ArrayList<>();
    private List<Emprestimo> emprestimos = new ArrayList<>();

    @PostMapping("/inserir")
    public ResponseEntity<?> inserirLivro(@RequestBody Livro livro) {
        livros.add(livro);
        return ResponseEntity.status(HttpStatus.CREATED).body("Livro criado com sucesso!");
    }

    @GetMapping("/listar")
    public ResponseEntity<List<Livro>> listarLivros() {
        return ResponseEntity.ok(livros);
    }

    @PostMapping("/emprestimos")
    public ResponseEntity<?> registrarEmprestimo(@RequestParam Long livroId, @RequestParam String usuario) {

        Livro livroEmprestado = null;
        for (Livro livro : livros) {
            if (livro.getId().equals(livroId)) {
                livroEmprestado = livro;
                break;
            }
        }

        if (livroEmprestado == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Livro não encontrado.");
        }

        if (!livroEmprestado.isDisponivel()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Livro já emprestado.");
        }

        livroEmprestado.setDisponivel(false);

        Emprestimo novoEmprestimo = new Emprestimo();
        novoEmprestimo.setId(livroId);
        novoEmprestimo.setUsuario(usuario);
        novoEmprestimo.setDataEmprestimo(LocalDate.now());

        List<Livro> listaLivros = new ArrayList<>();
        listaLivros.add(livroEmprestado);
        emprestimos.add(novoEmprestimo);
        novoEmprestimo.setLivros(listaLivros);

        return ResponseEntity.status(HttpStatus.CREATED).body("Empréstimo registrado com sucesso!");
    }

    @PutMapping("/emprestimos/devolver/{id}")
    public ResponseEntity<?> registrarDevolucao(@PathVariable Long id) {
        Livro livroEmprestado = null;
        for (Livro livro : livros) {
            if (livro.getId().equals(id)) {
                livroEmprestado = livro;
                break;
            }
        }

        if (livroEmprestado == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Livro não encontrado.");
        }

        if (livroEmprestado.isDisponivel()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Este livro não foi emprestado.");
        }

        for (Emprestimo emprestimo : emprestimos) {
            if (emprestimo.getLivros().contains(livroEmprestado)) {
                emprestimo.setDataDevolucao(LocalDate.now());
                break;
            }
        }

        livroEmprestado.setDisponivel(true);

        return ResponseEntity.ok("Livro devolvido com sucesso!");
    }

    @GetMapping("/emprestimos")
    public ResponseEntity<List<Emprestimo>> listarEmprestimos() {
        return ResponseEntity.ok(emprestimos);
    }

    @GetMapping("/livrosdispo")
    public ResponseEntity<?> listarDisponiveis() {
        List<Livro> livrosDisponiveis = new ArrayList<>();
        for (Livro livro : livros) {
            if (livro.isDisponivel()) {
                livrosDisponiveis.add(livro);
            }
        }

        if (livrosDisponiveis.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Nenhum livro disponível no momento.");
        }

        return ResponseEntity.ok(livrosDisponiveis);
    }
}
