package br.com.orch.pedidos.dto;

import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

public record PedidoRequest(
		
		@NotBlank(message = "O ID do cliente é obrigatório") 
		String clienteId,

		@NotBlank(message = "A forma de pagamento é obrigatória") 
		String formaPagamento, // Ex: "PIX", "CARTAO_CREDITO"

		@NotEmpty(message = "O pedido deve conter pelo menos um item") 
		@Valid // Garante que as validações do  ItemPedidoRequest sejam executadas
		List<ItemPedidoRequest> itens) {
}
