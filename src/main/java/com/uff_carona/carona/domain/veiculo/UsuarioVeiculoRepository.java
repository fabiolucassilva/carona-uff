package com.uff_carona.carona.domain.veiculo;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioVeiculoRepository extends JpaRepository<UsuarioVeiculo, Integer> {
    // Adicione aqui métodos de consulta personalizados, se necessário
}
