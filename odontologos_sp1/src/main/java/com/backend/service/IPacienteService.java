package com.backend.service;

import com.backend.dto.entrada.PacienteEntradaDto;
import com.backend.dto.salida.PacienteSalidaDto;
import java.util.List;

public interface IPacienteService {
  PacienteSalidaDto registrarPaciente(PacienteEntradaDto paciente);
  PacienteSalidaDto buscarPacientePorId(Long id);
  void eliminarPaciente(Long id);
  PacienteSalidaDto actualizarPaciente(PacienteEntradaDto pacienteEntradaDto, Long id);
  List<PacienteSalidaDto> listarPacientes();
}
