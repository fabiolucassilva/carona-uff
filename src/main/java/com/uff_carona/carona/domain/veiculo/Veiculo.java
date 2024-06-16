package com.uff_carona.carona.domain.veiculo;

import com.uff_carona.carona.domain.usuario.Usuario;
import jakarta.persistence.*;
import lombok.*;

@Table(name="veiculo")
@Entity(name="veiculo")
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Veiculo {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(unique = true)
    private String placa;

    @Column(name = "ativo")
    private Boolean ativo;

    private String marca;

    private String modelo;

    @Column(name = "capacidade_max")
    private Integer maxCapacidade;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "dono_usuario_id")
    private Usuario donoId;

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public Usuario getDonoId() {
        return donoId;
    }

    public void setDonoId(Usuario donoId) {
        this.donoId = donoId;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public Integer getMaxCapacidade() {
        return maxCapacidade;
    }

    public void setMaxCapacidade(Integer maxCapacidade) {
        this.maxCapacidade = maxCapacidade;
    }
}