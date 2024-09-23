package com.backend.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.SequenceGenerator;
import org.springframework.data.annotation.Id;
import javax.persistence.Table;

@Entity
@Table(name = "usuarios")
public class Usuario {
  @Id
  @SequenceGenerator(name = "usuario_sequence", sequenceName = "usuario_sequence")
  @GeneratedValue(strategy = GeneratedType.SEQUENCE, generator = "usuario_sequence")
  //@GeneratedValue(strategy=GeneratedType.IDENTITY
  //.sequence para crar una secuencia personalizada
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
