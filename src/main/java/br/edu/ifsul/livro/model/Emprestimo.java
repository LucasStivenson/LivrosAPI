package br.edu.ifsul.livro.model;

import java.time.LocalDate;
import java.util.List;

public class Emprestimo {
    private Long id;
    private List<Livro> livros;
    private String usuario;
    private LocalDate dataEmprestimo;
    private LocalDate dataDevolucao;
   
    public Emprestimo() {
    }

    public Emprestimo(Long id, List<Livro> livros, String usuario, LocalDate dataEmprestimo, LocalDate dataDevolucao) {
        this.id = id;
        this.livros = livros;
        this.usuario = usuario;
        this.dataEmprestimo = dataEmprestimo;
        this.dataDevolucao = dataDevolucao;
    }
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public List<Livro> getLivros() {
        return livros;
    }
    public void setLivros(List<Livro> livros) {
        this.livros = livros;
    }
    public String getUsuario() {
        return usuario;
    }
    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
    public LocalDate getDataEmprestimo() {
        return dataEmprestimo;
    }
    public void setDataEmprestimo(LocalDate dataEmprestimo) {
        this.dataEmprestimo = dataEmprestimo;
    }
    public LocalDate getDataDevolucao() {
        return dataDevolucao;
    }
    public void setDataDevolucao(LocalDate dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }
}
