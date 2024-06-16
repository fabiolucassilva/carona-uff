package com.uff_carona.carona.controllers;

import com.uff_carona.carona.domain.usuario.ResponseUsuario;
import com.uff_carona.carona.domain.usuario.Usuario;
import com.uff_carona.carona.domain.veiculo.RequestVeiculo;
import com.uff_carona.carona.domain.veiculo.ResponseVeiculo;
import com.uff_carona.carona.domain.veiculo.Veiculo;
import com.uff_carona.carona.domain.veiculo.VeiculoRepository;
import com.uff_carona.carona.domain.usuario.UsuarioRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/veiculos")
public class VeiculoController {

    @Autowired
    private VeiculoRepository veiculoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping
    public List<ResponseVeiculo> getAllVeiculos() {
        List<ResponseVeiculo> VeiculoList = veiculoRepository.findAllByAtivoTrue().stream().map(ResponseVeiculo::new).toList();

        return VeiculoList;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseVeiculo> getVeiculoById(@PathVariable Integer id) {
        Optional<Veiculo> veiculoOptional = veiculoRepository.findById(id);
        if (veiculoOptional.isPresent()) {
            Veiculo veiculo = veiculoOptional.get();
            ResponseVeiculo veiculoDTO = new ResponseVeiculo(veiculo); // Converta para DTO
            return ResponseEntity.ok(veiculoDTO);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping
    public ResponseEntity<Veiculo> createVeiculo(@RequestBody @Valid RequestVeiculo data) {
        Usuario dono = usuarioRepository.findById(data.donoId())
                .orElseThrow(() -> new RuntimeException("Dono não encontrado"));

        Veiculo novoVeiculo = new Veiculo();
        novoVeiculo.setPlaca(data.placa());
        novoVeiculo.setAtivo(true);
        novoVeiculo.setMarca(data.marca());
        novoVeiculo.setModelo(data.modelo());
        novoVeiculo.setMaxCapacidade(data.maxCapacidade());
        novoVeiculo.setDonoId(dono);

        Veiculo savedVeiculo = veiculoRepository.save(novoVeiculo);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedVeiculo);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PutMapping("/{id}")
    public ResponseEntity<ResponseVeiculo> updateVeiculo(@PathVariable Integer id, @RequestBody RequestVeiculo veiculoDTO) {
        if (!veiculoRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        Optional<Veiculo> veiculoOptional = veiculoRepository.findById(id);
        if (!veiculoOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        Veiculo veiculo = veiculoOptional.get();

        veiculo.setPlaca(veiculoDTO.placa());
        veiculo.setMarca(veiculoDTO.marca());
        veiculo.setModelo(veiculoDTO.modelo());
        veiculo.setMaxCapacidade(veiculoDTO.maxCapacidade());

        Optional<Usuario> usuarioOptional = usuarioRepository.findById(veiculoDTO.donoId());
        if (!usuarioOptional.isPresent()) {
            return ResponseEntity.badRequest().build();
        }
        veiculo.setDonoId(usuarioOptional.get());

        Veiculo updatedVeiculo = veiculoRepository.save(veiculo);
        ResponseVeiculo responseVeiculo = new ResponseVeiculo(updatedVeiculo);

        return ResponseEntity.ok(responseVeiculo);
    }


    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVeiculo(@PathVariable Integer id) {
        if (!veiculoRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        Optional<Veiculo> veiculoOptional = veiculoRepository.findById(id);
        if (!veiculoOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        Veiculo veiculo = veiculoOptional.get();

        veiculo.setAtivo(false);

        Veiculo updatedVeiculo = veiculoRepository.save(veiculo);
        ResponseVeiculo responseVeiculo = new ResponseVeiculo(updatedVeiculo);

        return ResponseEntity.noContent().build();
    }
}
