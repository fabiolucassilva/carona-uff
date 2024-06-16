package com.uff_carona.carona.domain.veiculo;

import com.uff_carona.carona.domain.usuario.Usuario;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ResponseUsuarioVeiculo(

        Usuario usuario,

        Veiculo veiculo

) {
    public ResponseUsuarioVeiculo(UsuarioVeiculo usuarioVeiculo) {
        this(usuarioVeiculo.getUsuario(), usuarioVeiculo.getVeiculo());
    }
}
