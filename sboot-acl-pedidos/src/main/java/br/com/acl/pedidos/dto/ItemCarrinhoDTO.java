package br.com.acl.pedidos.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ItemCarrinhoDTO(
        @NotBlank(message = "O código do produto é obrigatório")
        String produtoId,

        @NotNull(message = "A quantidade é obrigatória")
        @Min(value = 1, message = "A quantidade mínima deve ser 1")
        Integer quantidade
) {}