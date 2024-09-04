package com.backend.dto.entrada;

import com.backend.dto.salida.OdontologoSalidaDto;
import com.backend.dto.salida.PacienteSalidaDto;
import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class TurnoEntradaDto {
  @NotNull(message = "El Paciente debe existir, turnoEntradaDTO")
  PacienteSalidaDto pacienteSalidaDto;

  @NotNull(message = "El Odontologo debe existir, turnoEntradaDTO")
  OdontologoSalidaDto odontologoEntradaDto;

  @FutureOrPresent(message = "La fecha no puede ser anterior al día de hoy")
  @NotNull(message = "Debe especificarse la fecha de atención al paciente")
  @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss")
  LocalDateTime fechaHora;

  /*public TurnoEntradaDto(PacienteSalidaDto pacienteEntradaDto, OdontologoSalidaDto odontologoEntradaDto,
                         LocalDateTime fechaHora) {
    this.pacienteEntradaDto = pacienteEntradaDto;
    this.odontologoEntradaDto = odontologoEntradaDto;
    this.fechaHora = fechaHora;
  }*/

  public TurnoEntradaDto(PacienteSalidaDto pacienteSalidaDto, OdontologoSalidaDto odontologoEntradaDto) {
    this.pacienteSalidaDto = pacienteSalidaDto;
    this.odontologoEntradaDto = odontologoEntradaDto;
    this.fechaHora=LocalDateTime.now();
  }

  public TurnoEntradaDto(PacienteSalidaDto pacienteSalidaDto, OdontologoSalidaDto odontologoEntradaDto, LocalDateTime fechaHora) {
    this.pacienteSalidaDto = pacienteSalidaDto;
    this.odontologoEntradaDto = odontologoEntradaDto;
    this.fechaHora = fechaHora;
  }

  public TurnoEntradaDto() {
  }

  public PacienteSalidaDto getPacienteSalidaDto() {
    return pacienteSalidaDto;
  }

  public void setPacienteSalidaDto(PacienteSalidaDto pacienteSalidaDto) {
    this.pacienteSalidaDto = pacienteSalidaDto;
  }

  public OdontologoSalidaDto getOdontologoSalidaDto() {
    return odontologoEntradaDto;
  }

  public void setOdontologoSalidaDto(OdontologoSalidaDto odontologoEntradaDto) {
    this.odontologoEntradaDto = odontologoEntradaDto;
  }

  public LocalDateTime getFechaHora() {
    return fechaHora;
  }

  public void setFechaHora(LocalDateTime fechaHora) {
    this.fechaHora = fechaHora;
  }
}
