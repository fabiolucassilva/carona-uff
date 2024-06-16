package com.uff_carona.carona.controllers;

import com.uff_carona.carona.domain.usuario.Usuario;
import com.uff_carona.carona.domain.usuario.UsuarioRepository;
import com.uff_carona.carona.domain.veiculo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
    public List<ResponseUsuarioVeiculo> getAllUsuarioVeiculos() {
        List<ResponseUsuarioVeiculo> usuarioVeiculosList = usuarioVeiculoRepository.findAll().stream().map(ResponseUsuarioVeiculo::new).toList();

        return usuarioVeiculosList;
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<List<ResponseUsuarioVeiculo>> getUsuarioVeiculoByUsuarioId(@PathVariable Integer idUsuario) {
        List<UsuarioVeiculo> usuarioVeiculos = usuarioVeiculoRepository.findByUsuarioId(idUsuario);

        if (!usuarioVeiculos.isEmpty()) {
            List<ResponseUsuarioVeiculo> responseUsuarioVeiculos = usuarioVeiculos.stream()
                    .map(ResponseUsuarioVeiculo::new)
                    .collect(Collectors.toList());
            return ResponseEntity.ok(responseUsuarioVeiculos);
        } else {
            return ResponseEntity.notFound().build();
        }
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
