package com.uff_carona.carona.controllers;

import com.uff_carona.carona.domain.usuario.Usuario;
import com.uff_carona.carona.domain.usuario.UsuarioRepository;
import com.uff_carona.carona.domain.veiculo.UsuarioVeiculo;
import com.uff_carona.carona.domain.veiculo.UsuarioVeiculoRepository;
import com.uff_carona.carona.domain.veiculo.Veiculo;
import com.uff_carona.carona.domain.veiculo.VeiculoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/usuario-veiculos")
public class UsuarioVeiculoController {

    @Autowired
    private UsuarioVeiculoRepository usuarioVeiculoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private VeiculoRepository veiculoRepository;

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping
    public ResponseEntity<List<UsuarioVeiculo>> getAllUsuarioVeiculos() {
        List<UsuarioVeiculo> usuarioVeiculos = usuarioVeiculoRepository.findAll();
        return ResponseEntity.ok(usuarioVeiculos);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/{id}")
    public ResponseEntity<UsuarioVeiculo> getUsuarioVeiculoById(@PathVariable Integer id) {
        Optional<UsuarioVeiculo> usuarioVeiculoOptional = usuarioVeiculoRepository.findById(id);
        return usuarioVeiculoOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping
    public ResponseEntity<UsuarioVeiculo> createUsuarioVeiculo(@RequestBody UsuarioVeiculo usuarioVeiculo) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findById(usuarioVeiculo.getUsuario().getId());
        Optional<Veiculo> veiculoOptional = veiculoRepository.findById(usuarioVeiculo.getVeiculo().getId());

        if (usuarioOptional.isEmpty() || veiculoOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        usuarioVeiculo.setUsuario(usuarioOptional.get());
        usuarioVeiculo.setVeiculo(veiculoOptional.get());

        UsuarioVeiculo savedUsuarioVeiculo = usuarioVeiculoRepository.save(usuarioVeiculo);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedUsuarioVeiculo);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PutMapping("/{id}")
    public ResponseEntity<UsuarioVeiculo> updateUsuarioVeiculo(@PathVariable Integer id, @RequestBody UsuarioVeiculo usuarioVeiculo) {
        if (!usuarioVeiculoRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        Optional<Usuario> usuarioOptional = usuarioRepository.findById(usuarioVeiculo.getUsuario().getId());
        Optional<Veiculo> veiculoOptional = veiculoRepository.findById(usuarioVeiculo.getVeiculo().getId());

        if (usuarioOptional.isEmpty() || veiculoOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        usuarioVeiculo.setId(id);
        usuarioVeiculo.setUsuario(usuarioOptional.get());
        usuarioVeiculo.setVeiculo(veiculoOptional.get());

        UsuarioVeiculo updatedUsuarioVeiculo = usuarioVeiculoRepository.save(usuarioVeiculo);
        return ResponseEntity.ok(updatedUsuarioVeiculo);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUsuarioVeiculo(@PathVariable Integer id) {
        if (!usuarioVeiculoRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        usuarioVeiculoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
