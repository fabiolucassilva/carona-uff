package com.uff_carona.carona.domain.veiculo;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UsuarioVeiculoRepository extends JpaRepository<UsuarioVeiculo, Integer> {
    List<UsuarioVeiculo> findByUsuarioId(Integer idUsuario);
    // Adicione aqui métodos de consulta personalizados, se necessário
}
