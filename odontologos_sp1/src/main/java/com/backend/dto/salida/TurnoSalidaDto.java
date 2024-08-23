package com.backend.dto.salida;

import com.backend.entity.Odontologo;
import com.backend.entity.Paciente;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class TurnoSalidaDto {

  Long id;
  Paciente paciente;
  Odontologo odontologo;
  LocalDateTime fechaHora;

  public TurnoSalidaDto(Long id, Paciente paciente, Odontologo odontologo, LocalDateTime fechaHora) {
    this.id = id;
    this.paciente = paciente;
    this.odontologo = odontologo;
    this.fechaHora = fechaHora;
  }
  public TurnoSalidaDto(){}

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
}
