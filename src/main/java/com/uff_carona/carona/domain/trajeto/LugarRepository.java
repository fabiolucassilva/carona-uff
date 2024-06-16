package com.uff_carona.carona.domain.trajeto;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LugarRepository extends JpaRepository<Lugar, Integer> {
    // Se necessário, você pode adicionar métodos personalizados de consulta aqui
}

