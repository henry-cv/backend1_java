package com.backend.entity;

public class Usuario {
  private Long id;
  private String usuario;
  private String password;

  public Usuario(String usuario, String password) {
    this.usuario = usuario;
    this.password = password;
  }

  public Usuario() {
  }

  public String getUsuario() {
    return usuario;
  }

  public void setUsuario(String usuario) {
    this.usuario = usuario;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }
}
