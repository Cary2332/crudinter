package com.example.demo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

import com.example.demo.dto.DTO;
import com.example.demo.model.Model;
import com.example.demo.repository.Repository;

@RestController
@RequestMapping("perfumaria")
public class Controller {

	@Autowired
	private Repository repo; // Corrigindo o nome do repositório

	@GetMapping
	public ResponseEntity<List<Model>> obterUsuarios() {
		List<Model> usuarios = repo.findAll();
		return ResponseEntity.ok(usuarios);
	}
	
	@GetMapping("/perfumes")
	public ResponseEntity<?> listarTodos() {
		List<Model> perfumes = repo.findAll();
		if (perfumes.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		}
		return ResponseEntity.ok(perfumes);
	}

	@PostMapping("/perfume")
	public ResponseEntity<?> cadastrarPerfume(@RequestBody DTO dto) {
		Model perfumeExistente = new Model(dto);
		repo.save(perfumeExistente);
		return ResponseEntity.status(HttpStatus.CREATED).body("Perfume adicionado ao estoque!");
	}

//	@PutMapping("/perfume/{id}")
//	public ResponseEntity<?> updatePerfume(@PathVariable Long ID, @RequestBody Model dto) {
//		Optional<Model> perfumeExistente = repo.findById(ID);
//
//		if (perfumeExistente.isPresent()) {
//			Model perfumeAtualizado = perfumeExistente.get();
//			perfumeAtualizado.setNome(dto.getNome());
//			perfumeAtualizado.setEstoque(dto.getEstoque());
//			perfumeAtualizado.setMarca(dto.getMarca());
//			perfumeAtualizado.setPreco(dto.getPreco());
//			repo.save(perfumeAtualizado);
//			return ResponseEntity.ok(perfumeAtualizado);
//		}
//		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Perfume não encontrado!");
//	}

	
	@PutMapping("/{id}")
	public ResponseEntity<?> atualizarUsuario(@PathVariable Long id, @RequestBody DTO dto) {
	    Model us = repo.findById(id).get();
	    us.updateDTO(dto);// Atualiza o modelo com os dados do DTO
	    repo.save(us);
	    return ResponseEntity.ok("Atualizado com sucesso");
	    		}
	
	
//	@DeleteMapping("/perfume/{id}")
//	public ResponseEntity<?> deletarPerfume(@PathVariable Long ID) {
//		Optional<Model> perfumeExistente = repo.findById(ID);
//
//		if (perfumeExistente.isPresent()) {
//			repo.deleteById(ID);
//			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
//
//		}
//		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Perfume nãuo encontrado!");
//	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deletarUsuario(@PathVariable Long id) {
		repo.deleteById(id);
		return ResponseEntity.ok("Deletado com sucesso");
	}

}
