package com.uff_carona.carona.domain.login;

// DTO para realizar Login
public class LoginRequest {
    private String email;
    private String senha;

    // Getters and Setters
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
}
