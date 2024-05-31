package com.uff_carona.carona.domain.usuario;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

// DTO para retorno de informações de usuário em tela
public record ResponseUsuario(
        Integer id,

        String nome,

        String sobrenome,

        String cpf,

        Boolean ativo,

        String email,

        String senha,

        Long reputacao) {

    public ResponseUsuario(Usuario usuario){
        this(usuario.getId(), usuario.getNome(), usuario.getSobrenome(), usuario.getCpf(), usuario.getAtivo(), usuario.getEmail(), usuario.getSenha(), usuario.getReputacao());
    }

}
