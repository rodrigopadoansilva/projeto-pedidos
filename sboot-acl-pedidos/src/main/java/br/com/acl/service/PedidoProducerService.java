package br.com.acl.service;

import br.com.acl.pedidos.dto.PedidoInputDTO;

public interface PedidoProducerService {

	void enviarPedido(String idempotencyKey, PedidoInputDTO pedidoInput);

}
