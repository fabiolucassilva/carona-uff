package com.uff_carona.carona.domain.veiculo;

import com.uff_carona.carona.domain.usuario.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface VeiculoRepository extends JpaRepository<Veiculo, Integer> {
    // Busca de todos os veículos ativos na base
    List<Veiculo> findAllByAtivoTrue();

    // Busca de veículos de um determinado dono
    //List<Veiculo> findAllByDono(Usuario dono);

    // Busca de veículos por placa
    Veiculo findByPlaca(String placa);
}

