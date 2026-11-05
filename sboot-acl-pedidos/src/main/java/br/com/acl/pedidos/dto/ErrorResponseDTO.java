package br.com.acl.pedidos.dto;

import java.time.LocalDateTime;
import java.util.List;

public record ErrorResponseDTO(
        int status,
        String erro,
        String mensagem,
        List<String> detalhes,
        LocalDateTime timestamp
) {
    public static ErrorResponseDTO comDetalhes(int status, String erro, String mensagem, List<String> detalhes) {
        return new ErrorResponseDTO(status, erro, mensagem, detalhes, LocalDateTime.now());
    }

    public static ErrorResponseDTO simples(int status, String erro, String mensagem) {
        return new ErrorResponseDTO(status, erro, mensagem, List.of(), LocalDateTime.now());
    }
}