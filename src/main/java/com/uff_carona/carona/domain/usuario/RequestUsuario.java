package com.uff_carona.carona.domain.usuario;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

// DTO para cadastro e edição de usuário
public record RequestUsuario(

        @NotBlank
        String nome,

        String sobrenome,

        @NotNull
        String cpf,

        Boolean ativo,

        @NotNull
        String email,

        @NotNull
        String senha) {

}
