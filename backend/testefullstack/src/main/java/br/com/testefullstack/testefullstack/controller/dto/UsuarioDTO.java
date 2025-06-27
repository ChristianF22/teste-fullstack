package br.com.testefullstack.testefullstack.controller.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

public record UsuarioDTO(

        Integer id,

        @NotBlank(message = "Login é obrigatório.")
        @Size(min = 3, max = 15, message = "Login deve ter entre 3 e 15 caracteres.")
        String login,

        @NotBlank(message = "Senha é obrigatória.")
        @Size(min = 6, max = 500, message = "Senha deve ter entre 6 e 500 caracteres.")
        String senha,

        @NotBlank(message = "Nome é obrigatório.")
        @Size(min = 2, max = 60, message = "Nome deve ter entre 2 e 60 caracteres.")
        String nome,

        @NotNull(message = "As roles são obrigatórias.")
        @Size(min = 1, message = "Pelo menos uma role deve ser informada.")
        List<@NotBlank(message = "Role não pode ser vazia.") String> roles

) {

}
