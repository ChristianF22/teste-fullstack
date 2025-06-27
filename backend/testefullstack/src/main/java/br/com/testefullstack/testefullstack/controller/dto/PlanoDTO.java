package br.com.testefullstack.testefullstack.controller.dto;

import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record PlanoDTO(

        Integer id,

        @NotBlank(message = "Nome é obrigatório.")
        @Size(min = 2, max = 100, message = "Nome deve ter entre 2 e 100 caracteres.")
        String nome,

        @NotNull(message = "Valor é obrigatório.")
        @DecimalMin(value = "0.00", inclusive = true, message = "Valor deve ser positivo.")
        @Digits(integer = 10, fraction = 2, message = "Valor com formato inválido.")
        BigDecimal valor,

        Integer idUsuario

) {

}