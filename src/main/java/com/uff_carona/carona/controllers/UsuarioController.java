package com.uff_carona.carona.controllers;

import com.uff_carona.carona.domain.usuario.RequestUsuario;
import com.uff_carona.carona.domain.usuario.ResponseUsuario;
import com.uff_carona.carona.domain.usuario.Usuario;
import com.uff_carona.carona.domain.usuario.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
    @Autowired
    private UsuarioRepository repository;

    /**
     * API Get para retorno de todos os usuários ativos da base
     */
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping
    public List<ResponseUsuario> getAllUsuario(){
        List<ResponseUsuario> UsuarioList = repository.findAllByAtivoTrue().stream().map(ResponseUsuario::new).toList();

        return UsuarioList;
    }

    /**
     * API Post para cadastrar usuários na base
     */
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping
    public ResponseEntity cadatrarUsuario(@RequestBody @Valid RequestUsuario data){
        Usuario novoUsuario = new Usuario(data);
        novoUsuario.setAtivo(true);
        novoUsuario.setReputacao(0L);
        System.out.println(data);//print no console
        repository.save(novoUsuario);

        return ResponseEntity.ok().build();
    }

    /**
     * API Put para edição de usuários na base
     */
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity updateUsuario(@PathVariable Integer id, @RequestBody @Valid RequestUsuario data){
        Optional<Usuario> optionalUsuario = repository.findById(id);
        if(optionalUsuario.isPresent()) {
            Usuario usuario = optionalUsuario.get();
            usuario.setNome(data.nome());
            usuario.setSobrenome(data.sobrenome());
            usuario.setCpf(data.cpf());
            usuario.setEmail(data.email());
            usuario.setSenha(data.senha());

            return ResponseEntity.ok(optionalUsuario);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * API Delete para a exclusão de usuários na base
     * OBS: A exclusão é apenas lógica pela coluna boolean "ativo"
     */
    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deleteUsuario(@PathVariable Integer id){
        Optional<Usuario> optionalUsuario = repository.findById(id);
        if(optionalUsuario.isPresent()) {
            Usuario usuario = optionalUsuario.get();
            usuario.setAtivo(false);

            return ResponseEntity.noContent().build();
        }else {
            throw new EntityNotFoundException();
        }
    }

}
