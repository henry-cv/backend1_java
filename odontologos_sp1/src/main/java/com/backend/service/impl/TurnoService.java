package com.backend.service.impl;

import com.backend.dto.entrada.PacienteEntradaDto;
import com.backend.dto.entrada.TurnoEntradaDto;
import com.backend.dto.salida.PacienteSalidaDto;
import com.backend.dto.salida.TurnoSalidaDto;


import com.backend.entity.Paciente;
import com.backend.entity.Turno;
import com.backend.repository.IDao;
import com.backend.service.ITurnoService;
import com.backend.utils.JsonPrinter;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TurnoService implements ITurnoService {

  private final Logger LOGGER = LoggerFactory.getLogger(TurnoService.class);
  private final IDao<Turno> turnoIDao;
  private final ModelMapper modelMapper;

  public TurnoService(IDao<Turno> turnoIDao, ModelMapper modelMapper) {
    this.turnoIDao = turnoIDao;
    this.modelMapper = modelMapper;
  }

  @Override
  public TurnoSalidaDto registrarTurno(TurnoEntradaDto turno) {
    LOGGER.info("TurnoEntradaDto: {}", JsonPrinter.toString(turno));

    //Recibe un TurnoEntradato y convierte a una Entidad Turno
    Turno entidadTurno = modelMapper.map(turno, Turno.class);
    LOGGER.info("EntidadTurno: {}", JsonPrinter.toString(entidadTurno));

    //registra un turno, mediante el Idao, y lo guarda en turnoRegistrado, también lo persiste en la BD
    Turno turnoRegistrado = turnoIDao.registrar(entidadTurno);
    LOGGER.info("TurnoRegistrado: {}", JsonPrinter.toString(turnoRegistrado));

    //Mapea un turnoRegistrado  de tipo Entity a TurnoSalidaDto y lo guarda en turnoSalidaDto
    TurnoSalidaDto turnoSalidaDto = modelMapper.map(turnoRegistrado, TurnoSalidaDto.class);
    LOGGER.info("TurnoSalidaDto: {}", JsonPrinter.toString(turnoSalidaDto));
    return turnoSalidaDto;

  }

  @Override
  public TurnoSalidaDto buscarTurnoPorId(Long id) {
    Turno turnoBuscado = turnoIDao.buscar(id);
    return modelMapper.map(turnoBuscado, TurnoSalidaDto.class);
  }

  @Override
  public List<TurnoSalidaDto> listarTurnos() {
    List<TurnoSalidaDto> turnosSalidaDtos = turnoIDao.listar()
            .stream()
            .map(turno -> modelMapper.map(turno, TurnoSalidaDto.class))
            .toList();
    LOGGER.info("Listado de todos los turnos: {}", JsonPrinter.toString(turnosSalidaDtos));

    return turnosSalidaDtos;
  }

  @Override
  public void eliminarTurno(Long id) {

  }

  @Override
  public TurnoSalidaDto actualizarTurno(TurnoEntradaDto turnoEntradaDto, Long id) {
    return null;
  }
  private void configureMapping(){
    modelMapper.typeMap(PacienteEntradaDto.class, Paciente.class)
            .addMappings(mapper -> mapper.map(PacienteEntradaDto::getDomicilioEntradaDto, Paciente::setDomicilio));
    modelMapper.typeMap(Paciente.class, PacienteSalidaDto.class)
            .addMappings(mapper -> mapper.map(Paciente::getDomicilio, PacienteSalidaDto::setDomicilioSalidaDto));
  }
}
