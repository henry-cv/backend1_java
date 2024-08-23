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
