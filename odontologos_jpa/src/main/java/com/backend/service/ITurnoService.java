package com.backend.service;

import com.backend.dto.entrada.TurnoEntradaDto;
import com.backend.dto.salida.TurnoSalidaDto;

import java.util.List;

public interface ITurnoService {
  TurnoSalidaDto registrarTurno(TurnoEntradaDto turno);
  TurnoSalidaDto buscarTurnoPorId(Long id);
  List<TurnoSalidaDto> listarTurnos();
  void eliminarTurno(Long id);
  TurnoSalidaDto actualizarTurno(TurnoEntradaDto turnoEntradaDto, Long id);
}