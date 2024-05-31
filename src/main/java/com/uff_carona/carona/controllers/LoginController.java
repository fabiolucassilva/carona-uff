package com.uff_carona.carona.controllers;

import com.uff_carona.carona.domain.login.LoginRequest;
import com.uff_carona.carona.domain.usuario.Usuario;
import com.uff_carona.carona.domain.usuario.UsuarioRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private UsuarioRepository repository;

    /**
     * API Post para login de usuário
     * Valida no banco se a senha digitada bate para o e-mail também digitado
     * OBS: Não é feito nenhum tipo de criptografia nesta versão
     */
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping
    @Transactional
    public ResponseEntity<?> loginUsuario(@RequestBody @Valid LoginRequest loginRequest) {
        // Buscando usuário pelo e-mail
        Optional<Usuario> optionalUsuario = repository.findByEmailAndAtivoTrue(loginRequest.getEmail());
        Map<String, String> response = new HashMap<>();
        if (optionalUsuario.isPresent()) {
            Usuario usuario = optionalUsuario.get();
            // Validando a senha deste usuário
            if (usuario.getSenha().equals(loginRequest.getSenha())) {
                response.put("message", "Login realizado!");
                return ResponseEntity.ok().body(response);
            } else {
                response.put("message", "Senha e/ou E-mail Inválido!");
                return ResponseEntity.status(401).body(response);
            }
        } else {
            response.put("message", "Usuário não encontrado!");
            return ResponseEntity.status(404).body(response);
        }
    }
}
