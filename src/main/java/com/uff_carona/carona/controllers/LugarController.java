package com.uff_carona.carona.controllers;
import com.uff_carona.carona.domain.trajeto.Lugar;
import com.uff_carona.carona.domain.trajeto.LugarRepository;
import com.uff_carona.carona.domain.veiculo.Veiculo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/lugares")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class LugarController {

    private final LugarRepository lugarRepository;

    @Autowired
    public LugarController(LugarRepository lugarRepository) {
        this.lugarRepository = lugarRepository;
    }

    @GetMapping
    public ResponseEntity<List<Lugar>> getAllLugares() {
        List<Lugar> lugares = lugarRepository.findAll();
        return ResponseEntity.ok(lugares);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Lugar> getLugarById(@PathVariable Integer id) {
        Optional<Lugar> lugarOptional = lugarRepository.findById(id);
        return lugarOptional.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Lugar> createLugar(@Valid @RequestBody Lugar lugar) {
        Lugar savedLugar = lugarRepository.save(lugar);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedLugar);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Lugar> updateLugar(@PathVariable Integer id, @Valid @RequestBody Lugar lugarData) {
        if (!lugarRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }

        Optional<Lugar> lugarOptional = lugarRepository.findById(id);
        if (lugarOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Lugar lugar = lugarOptional.get();

        // Atualiza os dados do lugar com base nos dados recebidos
        lugar.setNomeLugar(lugarData.getNomeLugar());
        lugar.setEstado(lugarData.getEstado());
        lugar.setLatitude(lugarData.getLatitude());
        lugar.setLongitude(lugarData.getLongitude());
        lugar.setMunicipio(lugarData.getMunicipio());

        // Salva o lugar atualizado
        Lugar updatedLugar = lugarRepository.save(lugar);
        return ResponseEntity.ok(updatedLugar);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLugar(@PathVariable Integer id) {
        if (!lugarRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        lugarRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

}
