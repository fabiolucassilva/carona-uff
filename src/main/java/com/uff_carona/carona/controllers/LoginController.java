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

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping
    @Transactional
    public ResponseEntity<?> loginUsuario(@RequestBody @Valid LoginRequest loginRequest) {
        Optional<Usuario> optionalUsuario = repository.findByEmailAndAtivoTrue(loginRequest.getEmail());
        Map<String, String> response = new HashMap<>();
        if (optionalUsuario.isPresent()) {
            Usuario usuario = optionalUsuario.get();
            if (usuario.getSenha().equals(loginRequest.getSenha())) {
                String token = "token_carona_" + usuario.getId();
                response.put("message", "Login realizado!");
                response.put("token", token);
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

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/usuario")
    public ResponseEntity<?> getUsuario(@RequestHeader("Authorization") String token) {
        String userId = token.replace("token_carona_", "");
        Optional<Usuario> optionalUsuario = repository.findById(Integer.parseInt(userId));
        if (optionalUsuario.isPresent()) {
            return ResponseEntity.ok(optionalUsuario.get());
        } else {
            return ResponseEntity.status(404).body("Usuário não encontrado!");
        }
    }
}
