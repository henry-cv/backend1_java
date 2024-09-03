package com.backend.service;

import com.backend.dto.entrada.TurnoEntradaDto;
import com.backend.dto.salida.TurnoSalidaDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ITurnoService {
  TurnoSalidaDto registrarTurno(TurnoEntradaDto turno);
  TurnoSalidaDto buscarTurnoPorId(Long id);
  List<TurnoSalidaDto> listarTurnos();
  void eliminarTurno(Long id);
  TurnoSalidaDto actualizarTurno(TurnoEntradaDto turnoEntradaDto, Long id);
}