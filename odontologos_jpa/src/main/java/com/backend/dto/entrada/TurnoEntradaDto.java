package com.backend.dto.entrada;

import com.backend.dto.salida.OdontologoSalidaDto;
import com.backend.dto.salida.PacienteSalidaDto;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.time.LocalDateTime;

public class TurnoEntradaDto {
  @Positive(message = "El Id de Paciente no puede ser nulo o menor a cero")
  private Long pacienteId;

  @Positive(message = "El Id de Odontólogo no puede ser nulo o menor a cero")
  private Long odontologoId;

  @FutureOrPresent(message = "La fecha no puede ser anterior al día de hoy")
  @NotNull(message = "Debe especificarse la fecha de atención al paciente")
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
  private LocalDateTime fechaHora;

  public TurnoEntradaDto(Long pacienteId, Long odontologoId, LocalDateTime fechaHora) {
    this.pacienteId = pacienteId;
    this.odontologoId = odontologoId;
    this.fechaHora = fechaHora;
  }

  public TurnoEntradaDto(Long pacienteId, Long odontologoId) {
    this.pacienteId = pacienteId;
    this.odontologoId = odontologoId;
    this.fechaHora=LocalDateTime.now().plusDays(1);
  }

  public TurnoEntradaDto() {
  }

  public Long getPacienteId() {
    return pacienteId;
  }

  public void setPacienteId(Long pacienteId) {
    this.pacienteId = pacienteId;
  }

  public Long getOdontologoId() {
    return odontologoId;
  }

  public void setOdontologoId(Long odontologoId) {
    this.odontologoId = odontologoId;
  }

  public LocalDateTime getFechaHora() {
    return fechaHora;
  }

  public void setFechaHora(LocalDateTime fechaHora) {
    this.fechaHora = fechaHora;
  }
}
