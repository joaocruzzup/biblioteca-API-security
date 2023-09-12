package com.example.demo.controller;

import com.example.demo.model.LivrosModel;
import com.example.demo.service.LivrosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class LivrosController {

    @Autowired
    LivrosService livrosService;

    @PreAuthorize("anyRole('USER', 'EMPLOYEE', 'ADMIN')")
    @GetMapping(path = "/livros")
    public List<LivrosModel> exibeTodosLivros() {
        return livrosService.exibirTodos();
    }

    @PreAuthorize("anyRole('USER', 'EMPLOYEE', 'ADMIN')")
    @GetMapping(path = "/livros/{id}")
    public Optional<LivrosModel> exibirLivroPorId(@PathVariable Long id) { //optional vai pegar uma lista e procurar dentro da lista uma informação
        return livrosService.exibirPorId(id);
    }

    @PreAuthorize("anyRole('EMPLOYEE', 'ADMIN')")
    @PostMapping(path = "/livros")
    @ResponseStatus(HttpStatus.CREATED)
    public LivrosModel cadastrarNovoLivro(@RequestBody LivrosModel livrosModel) {
        return livrosService.cadastrar(livrosModel);
    }

    @PreAuthorize("anyRole('ADMIN')")
    @PutMapping(path = "/livros/{id}")
    public LivrosModel alterarLivro(@PathVariable Long id, @RequestBody LivrosModel livrosModel) {
        return livrosService.alterar(id, livrosModel);
    }

    @PreAuthorize("anyRole('ADMIN')")
    @DeleteMapping(path = "/livros/{id}")
    public void deletarLivro(@PathVariable Long id) {
        livrosService.deletar(id);
    }

}
