package com.uff_carona.carona.domain.trajeto;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TrajetoRepository extends JpaRepository<Trajeto, Integer> {
    // Se necessário, você pode adicionar métodos personalizados de consulta aqui
}

