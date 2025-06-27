package br.com.testefullstack.testefullstack.controller.dto;

import jakarta.validation.constraints.*;

import java.time.LocalDateTime;

public record BeneficiarioDTO(

        Integer id,

        @NotBlank(message = "Nome é obrigatório.")
        @Size(min = 2, max = 100, message = "Nome deve ter entre 2 e 100 caracteres.")
        String nome,

        @NotBlank(message = "CPF é obrigatório.")
        @Size(min = 11, max = 14, message = "CPF deve ter entre 11 e 14 caracteres.")
        String cpf,

        @Email(message = "E-mail inválido.")
        @Size(max = 100, message = "E-mail deve ter no máximo 100 caracteres.")
        String email,

        @NotNull(message = "Idade é obrigatória.")
        @Min(value = 0, message = "Idade não pode ser negativa.")
        Integer idade,

        @NotNull(message = "ID do plano é obrigatório.")
        Integer idPlano,

        Integer idUsuario

) {
}
