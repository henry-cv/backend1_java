package com.backend.dto.entrada;

import com.backend.entity.Odontologo;
import com.backend.entity.Paciente;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class TurnoEntradaDto {
  @NotNull
  PacienteEntradaDto pacienteEntradaDto;

  @NotNull
  OdontologoEntradaDto odontologoEntradaDto;

  @FutureOrPresent(message = "La fecha no puede ser anterior al día de hoy")
  @NotNull(message = "Debe especificarse la fecha de ingreso del paciente")
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
  LocalDateTime fechaHora;

  public TurnoEntradaDto(PacienteEntradaDto pacienteEntradaDto, OdontologoEntradaDto odontologoEntradaDto,
                         LocalDateTime fechaHora) {
    this.pacienteEntradaDto = pacienteEntradaDto;
    this.odontologoEntradaDto = odontologoEntradaDto;
    this.fechaHora = fechaHora;
  }

  public PacienteEntradaDto getPacienteEntradaDto() {
    return pacienteEntradaDto;
  }

  public void setPacienteEntradaDto(PacienteEntradaDto pacienteEntradaDto) {
    this.pacienteEntradaDto = pacienteEntradaDto;
  }

  public OdontologoEntradaDto getOdontologoEntradaDto() {
    return odontologoEntradaDto;
  }

  public void setOdontologoEntradaDto(OdontologoEntradaDto odontologoEntradaDto) {
    this.odontologoEntradaDto = odontologoEntradaDto;
  }

  public LocalDateTime getFechaHora() {
    return fechaHora;
  }

  public void setFechaHora(LocalDateTime fechaHora) {
    this.fechaHora = fechaHora;
  }
}
