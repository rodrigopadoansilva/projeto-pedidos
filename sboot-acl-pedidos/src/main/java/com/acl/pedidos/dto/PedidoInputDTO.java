package com.acl.pedidos.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import java.util.List;

public record PedidoInputDTO(
        @NotBlank(message = "O ID do cliente é obrigatório")
        String clienteId,

        @NotEmpty(message = "O carrinho não pode estar vazio")
        List<ItemCarrinhoDTO> itens
) {}