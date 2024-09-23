package com.backend.dto.entrada;

import com.backend.entity.Odontologo;
import com.backend.entity.Paciente;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class TurnoEntradaDto {
  @NotNull
  Paciente paciente;

  @NotNull
  Odontologo odontologo;

  @FutureOrPresent(message = "La fecha no puede ser anterior al día de hoy")
  @NotNull(message = "Debe especificarse la fecha de ingreso del paciente")
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
  LocalDateTime fechaHora;

  public TurnoEntradaDto(Paciente paciente, Odontologo odontologo, LocalDateTime fechaHora) {
    this.paciente = paciente;
    this.odontologo = odontologo;
    this.fechaHora = fechaHora;
  }
  public TurnoEntradaDto(){};

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
