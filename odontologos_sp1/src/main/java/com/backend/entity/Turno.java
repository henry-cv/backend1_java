package com.backend.entity;

import java.time.LocalDateTime;
import com.backend.entity.Paciente;

public class Turno {
  private Long id;
  private Paciente paciente;
  private Odontologo odontologo;
  private LocalDateTime fechaHora;

  public Turno(Long id, Paciente paciente, Odontologo odontologo, LocalDateTime fechaHora) {
    this.id = id;
    this.paciente = paciente;
    this.odontologo = odontologo;
    this.fechaHora = fechaHora;
  }

  public Turno(Paciente paciente, Odontologo odontologo, LocalDateTime fechaHora) {
    this.paciente = paciente;
    this.odontologo = odontologo;
    this.fechaHora = fechaHora;
  }

  public Turno() {
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Paciente getPaciente() {
    return paciente;
  }

  public void setPaciente(Paciente paciente) {
    this.paciente = paciente;
  }

  public Odontologo getOdontologo() {
    return odontologo;
  }

  public void setOdontologo(Odontologo odontologo) {
    this.odontologo = odontologo;
  }

  public LocalDateTime getFechaHora() {
    return fechaHora;
  }

  public void setFechaHora(LocalDateTime fechaHora) {
    this.fechaHora = fechaHora;
  }

  @Override
  public String toString() {
    return "Turno{" +
            "id=" + id +
            ", paciente=" + paciente +
            ", odontologo=" + odontologo +
            ", fechaHora=" + fechaHora +
            '}';
  }
}
