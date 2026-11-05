package com.orch.pedidos.controller;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.orch.pedidos.dto.PedidoRequest;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {
	
	@PostMapping
	public ResponseEntity<String>pedido(@RequestBody @Validated PedidoRequest pedido){
		
		String protocolo = UUID.randomUUID().toString();
		
		return ResponseEntity
                .status(HttpStatus.ACCEPTED)
                .body("Pedido aceito. Protocolo de acompanhamento: " + protocolo);
		
	}

}
