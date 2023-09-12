package com.example.demo.service;

import com.example.demo.model.LivrosModel;
import com.example.demo.repository.LivrosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LivrosService {

    @Autowired
    LivrosRepository livrosRepository;

    public List<LivrosModel> exibirTodos() {
        return livrosRepository.findAll();
    }


    public Optional<LivrosModel> exibirPorId(Long id) {
        return livrosRepository.findById(id);
    }

    public LivrosModel cadastrar(LivrosModel livrosModel) {
        return livrosRepository.save(livrosModel);
    }

    public LivrosModel alterar(Long id, LivrosModel livrosModel) {
        LivrosModel livros = exibirPorId(id).get();

        if (livrosModel.getNome() != null) {
            livros.setNome(livrosModel.getNome());
        }
        if (livrosModel.getAutor() != null) {
            livros.setAutor(livrosModel.getAutor());
        }
        if (livrosModel.getDataDeLancamento() != null) {
            livros.setDataDeLancamento(livrosModel.getDataDeLancamento());
        }
        if (livrosModel.getCodigoDoLivro() != null) {
            livros.setCodigoDoLivro(livrosModel.getCodigoDoLivro());
        }
        return livrosRepository.save(livros);
    }

    public void deletar(Long id) {
        livrosRepository.deleteById(id);
    }

}
