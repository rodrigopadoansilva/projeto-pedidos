package br.com.acl.pedidos.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.acl.pedidos.dto.PedidoInputDTO;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/pedidos")
public class PedidoAclController {

    @PostMapping
    public ResponseEntity<String> receberPedido(
            @RequestHeader("X-Idempotency-Key") String idempotencyKey,
            @RequestBody @Valid PedidoInputDTO pedidoInput) {

        // Por enquanto, apenas validamos a chegada dos dados e da chave
        return ResponseEntity.status(HttpStatus.ACCEPTED)
                .body("Pedido recebido no ACL com sucesso! Chave de Idempotência: " + idempotencyKey);
    }
}
