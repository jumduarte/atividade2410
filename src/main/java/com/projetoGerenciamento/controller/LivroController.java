package com.projetoGerenciamento.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projetoGerenciamento.entities.Livro;
import com.projetoGerenciamento.services.LivroService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

	@Tag(name = "Livros", description = "API REST DE GERENCIAMENTO DE LIVROS")
	@RestController
	@RequestMapping("/livros")
	public class LivroController {
	    
	    private final LivroService livroService;
	    
	    @Autowired
	    public LivroController(LivroService livroService) {
	        this.livroService = livroService;
	    }

	    @GetMapping("/{id}")
	    @Operation(summary = "Localiza usuario por ID")
	    public ResponseEntity<Livro> getProductById(@PathVariable Long id) {
	    	Livro livro = livroService.getLivroById(id);
	        if (livro != null) {
	            return ResponseEntity.ok(livro);
	        } else {
	            return ResponseEntity.notFound().build();
	        }
	    }

	    @GetMapping("/")
	    @Operation(summary = "Apresenta todos os Livros")
	    public ResponseEntity<List<Livro>> getAllLivros() {
	        List<Livro> livros = livroService.getAllLivros();
	        return ResponseEntity.ok(livros);
	    }
	    @PostMapping("/")
	    @Operation(summary = "Cadastra um Livro")
	    public ResponseEntity<Livro> criarLivro(@RequestBody @Valid Livro livro) {
	    	Livro criarLivro = livroService.salvarLivro(livro);
	        return ResponseEntity.status(HttpStatus.CREATED).body(criarLivro);
	    }
	   

	    @PutMapping("/{id}")
	    @Operation(summary = "Altera o Livro")
	    public ResponseEntity<Livro> updateLivro(@PathVariable Long id, @RequestBody @Valid Livro livro) {
	    	Livro updatedLivro = livroService.updateLivro(id, livro);
	        if (updatedLivro != null) {
	            return ResponseEntity.ok(updatedLivro);
	        } else {
	            return ResponseEntity.notFound().build();
	        }
	    }

	    @DeleteMapping("/{id}")
	    @Operation(summary = "Deleta o Livro")
	    public ResponseEntity<String> deleteLivro(@PathVariable Long id) {
	        boolean deleted = livroService.deleteLivro(id);
	        if (deleted) {
	        	 return ResponseEntity.ok().body("O produto foi excluído com sucesso.");
	        } else {
	            return ResponseEntity.notFound().build();
	        }
	    }
	}

