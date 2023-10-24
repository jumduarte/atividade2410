package com.projetoGerenciamento.repository;

	import org.springframework.data.jpa.repository.JpaRepository;

	import com.projetoGerenciamento.entities.Livro;

	public interface LivroRepository extends JpaRepository<Livro, Long> {

	}
