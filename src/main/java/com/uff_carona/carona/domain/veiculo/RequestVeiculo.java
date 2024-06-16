package com.uff_carona.carona.domain.veiculo;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RequestVeiculo(

        @NotBlank
        String placa,

        boolean ativo,

        String marca,

        String modelo,

        @NotNull
        Integer maxCapacidade,

        @NotNull
        Integer donoId) {
}
