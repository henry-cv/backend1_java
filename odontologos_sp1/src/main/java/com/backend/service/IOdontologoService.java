package com.backend.service;

import com.backend.dto.entrada.OdontologoEntradaDto;
import com.backend.dto.salida.OdontologoSalidaDto;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public interface IOdontologoService {
  OdontologoSalidaDto registrarOdontologo(OdontologoEntradaDto odontologo);
  OdontologoSalidaDto buscarOdontologoPorId(Long id);
  OdontologoSalidaDto eliminarOdontologo(Long id);
  List<OdontologoSalidaDto> listarOdontologos();
}
