package com.uff_carona.carona.domain.trajeto;

import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface LugarRepository extends JpaRepository<Lugar, Integer> {
    // Se necessário, você pode adicionar métodos personalizados de consulta aqui
}

