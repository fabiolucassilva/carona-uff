package com.uff_carona.carona.domain.veiculo;

import com.uff_carona.carona.domain.usuario.Usuario;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ResponseVeiculo(
        Integer id,

        String placa,

        boolean ativo,

        String marca,

        String modelo,

        Integer maxCapacidade,

        String donoNome) {  // Modificado para incluir apenas o nome do dono

    public ResponseVeiculo(Veiculo veiculo){
        this(veiculo.getId(), veiculo.getPlaca(), veiculo.getAtivo(), veiculo.getMarca(), veiculo.getModelo(), veiculo.getMaxCapacidade(), veiculo.getDonoId().getNome());
    }

}
