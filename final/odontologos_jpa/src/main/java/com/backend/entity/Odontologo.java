package com.backend.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "odontologos")
public class Odontologo {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String matricula;
  private String nombre;
  private String apellido;

  @OneToMany(mappedBy = "odontologo", cascade = CascadeType.REMOVE)
  @JsonManagedReference
  private List<Turno> turnos;

  public List<Turno> getTurnos() {
    return turnos;
  }

  public void setTurnos(List<Turno> turnos) {
    this.turnos = turnos;
  }

  public Odontologo(String matricula, String nombre, String apellido) {
    this.matricula = matricula;
    this.nombre = nombre;
    this.apellido = apellido;
  }

  public Odontologo(Long id, String matricula, String nombre, String apellido) {
    this.id = id;
    this.matricula = matricula;
    this.nombre = nombre;
    this.apellido = apellido;
  }

  public Odontologo() {
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getMatricula() {
    return matricula;
  }

  public void setMatricula(String matricula) {
    this.matricula = matricula;
  }

  public String getNombre() {
    return nombre;
  }

  public void setNombre(String nombre) {
    this.nombre = nombre;
  }

  public String getApellido() {
    return apellido;
  }

  public void setApellido(String apellido) {
    this.apellido = apellido;
  }

  @Override
  public String toString() {
    return "Odontologo{" + "id=" + id + ", matricula='" + matricula + '\'' + ", nombre='" + nombre + '\'' + ", " +
            "apellido='" + apellido + '\'' + '}';
  }
}
