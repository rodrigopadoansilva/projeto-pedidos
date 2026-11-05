package br.com.atom.pedidos.dto;

import java.time.LocalDate;

import org.hibernate.validator.constraints.br.CPF;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ClienteRequest(
        @NotBlank(message = "O nome é obrigatório")
        String nome,

        @NotBlank(message = "O CPF é obrigatório")
        @CPF(message = "CPF em formato inválido")
        String cpf,

        @NotNull(message = "A data de nascimento é obrigatória")
        LocalDate dataNascimento

) {
}