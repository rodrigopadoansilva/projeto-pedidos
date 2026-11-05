package com.atom.pedidos.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.atom.pedidos.dto.ClienteRequest;


@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

	
	@PostMapping
	public ResponseEntity<String> cadastroClientes(@RequestBody List<ClienteRequest> clientes) {
		//TODO: process POST request
		
		return ResponseEntity.status(HttpStatus.CREATED).body("Lista Recebida ".concat(String.valueOf(10)).concat(" Cadastrado com Sucesso").concat(String.valueOf(2)));
	}
	
	
}
