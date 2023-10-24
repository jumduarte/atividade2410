package com.projetoGerenciamento.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projetoGerenciamento.entities.Livro;
import com.projetoGerenciamento.repository.LivroRepository;


@Service
public class LivroService {
    private final LivroRepository livroRepository;
    
    @Autowired
    public LivroService(LivroRepository livroRepository) {
        this.livroRepository = livroRepository;
    }

    public List<Livro> getAllLivros() {
        return livroRepository.findAll();
    }

    public Livro getLivroById(Long id) {
        Optional<Livro> livro = livroRepository.findById(id);
        return livro.orElse(null);
    }

    public Livro salvarLivro(Livro livro) {
        return livroRepository.save(livro);
    }

    public Livro updateLivro(Long id, Livro updatedLivro) {
        Optional<Livro> existingLivro = livroRepository.findById(id);
        if (existingLivro.isPresent()) {
            updatedLivro.setId(id);
            return livroRepository.save(updatedLivro);
        }
        return null;
    }
    public boolean deleteLivro(Long id) {
        Optional<Livro> existingLivro = livroRepository.findById(id);
        if (existingLivro.isPresent()) {
        	livroRepository.deleteById(id);
            return true;
        }
        return false;
    }
}