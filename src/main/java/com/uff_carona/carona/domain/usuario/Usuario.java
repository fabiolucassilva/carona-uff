package com.uff_carona.carona.domain.usuario;

import jakarta.persistence.*;
import lombok.*;

@Table(name="usuario")
@Entity(name="usuario")
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String nome;

    private String sobrenome;

    @Column(unique = true)
    private String cpf;

    @Column(name = "ativo")
    private Boolean ativo;

    private String email;

    private String senha;

    private Long reputacao;

    // Construtor
    public Usuario(RequestUsuario requestUsuario){
        this.nome = requestUsuario.nome();
        this.sobrenome = requestUsuario.sobrenome();
        this.cpf = requestUsuario.cpf();
        this.email = requestUsuario.email();
        this.senha = requestUsuario.senha();
    }

    // Getters and Setters
    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getCpf() {
        return cpf;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getSenha() {
        return senha;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public Long getReputacao() {
        return reputacao;
    }

    public void setReputacao(Long reputacao) {
        this.reputacao = reputacao;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
