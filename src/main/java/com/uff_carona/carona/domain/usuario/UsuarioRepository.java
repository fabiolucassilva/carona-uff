package com.uff_carona.carona.domain.usuario;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    //Busca de todos os usuarios com ativo True na base
    List<Usuario> findAllByAtivoTrue();

    //Busca do usuário que pussiu um determinado email
    Optional<Usuario> findByEmailAndAtivoTrue(String email);
}


