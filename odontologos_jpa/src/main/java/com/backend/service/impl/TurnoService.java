package com.backend.service.impl;

import com.backend.dto.entrada.PacienteEntradaDto;
import com.backend.dto.entrada.TurnoEntradaDto;
import com.backend.dto.salida.OdontologoSalidaDto;
import com.backend.dto.salida.PacienteSalidaDto;
import com.backend.dto.salida.TurnoSalidaDto;


import com.backend.entity.Odontologo;
import com.backend.entity.Paciente;
import com.backend.entity.Turno;
import com.backend.repository.IDao;
import com.backend.repository.TurnoRepository;
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
  //@Autowired

  private final TurnoRepository turnoRepository;
  private final ModelMapper modelMapper;

  public TurnoService(TurnoRepository turnoRepository, ModelMapper modelMapper) {
    this.turnoRepository = turnoRepository;
    this.modelMapper = modelMapper;
    configureMapping();
  }

  @Override
  public TurnoSalidaDto registrarTurno(TurnoEntradaDto turno) {
    LOGGER.info("TurnoEntradaDto: {}", JsonPrinter.toString(turno));

    //Recibe un TurnoEntradato y convierte a una Entidad Turno
    Turno entidadTurno = modelMapper.map(turno, Turno.class);
    LOGGER.info("EntidadTurno: {}", JsonPrinter.toString(entidadTurno));

    //registra un turno, mediante el Idao, y lo guarda en turnoRegistrado, también lo persiste en la BD
    Turno turnoRegistrado = turnoRepository.save(entidadTurno);
    LOGGER.info("TurnoRegistrado: {}", JsonPrinter.toString(turnoRegistrado));

    //Mapea un turnoRegistrado  de tipo Entity a TurnoSalidaDto y lo guarda en turnoSalidaDto
    TurnoSalidaDto turnoSalidaDto = modelMapper.map(turnoRegistrado, TurnoSalidaDto.class);
    LOGGER.info("TurnoSalidaDto: {}", JsonPrinter.toString(turnoSalidaDto));
    return turnoSalidaDto;

  }

  @Override
  public TurnoSalidaDto buscarTurnoPorId(Long id) {
    Turno turnoBuscado = turnoRepository.findById(id).orElse(null);
    LOGGER.info("Turno buscado: {}", JsonPrinter.toString(turnoBuscado));
    TurnoSalidaDto turnoEncontrado = null;
    if(turnoBuscado != null){
      turnoEncontrado = modelMapper.map(turnoBuscado, TurnoSalidaDto.class);
      LOGGER.info("Turno encontrado: {}", JsonPrinter.toString(turnoEncontrado));
    } else LOGGER.error("No se ha encontrado el turno con id {}", id);

    return turnoEncontrado;
    //return modelMapper.map(turnoBuscado, TurnoSalidaDto.class);
  }

  @Override
  public List<TurnoSalidaDto> listarTurnos() {
    List<TurnoSalidaDto> turnosSalidaDtos = turnoRepository.findAll()
            .stream()
            .map(turno -> modelMapper.map(turno, TurnoSalidaDto.class))
            .toList();
    LOGGER.info("Listado de todos los turnos: {}", JsonPrinter.toString(turnosSalidaDtos));

    return turnosSalidaDtos;
  }

  @Override
  public void eliminarTurno(Long id) {
    if(buscarTurnoPorId(id) != null){
      //llamada a la capa repositorio para eliminar
      turnoRepository.deleteById(id);
      LOGGER.warn("Se ha eliminado el turno con id {}", id);
    } else {
      //excepcion resource not found
      LOGGER.error("No se pudo eliminar porque no existe turno con ese id: "+id);
    }
  }

  @Override
  public TurnoSalidaDto actualizarTurno(TurnoEntradaDto turnoEntradaDto, Long id){
    Turno turnoAActualizar = turnoRepository.findById(id).orElse(null);
    Turno turnoRecibido = modelMapper.map(turnoEntradaDto, Turno.class);
    TurnoSalidaDto turnoSalidaDto = null;

    if (turnoAActualizar != null){

      turnoRecibido.setId(turnoAActualizar.getId());
      turnoRecibido.getPaciente().setId(turnoAActualizar.getPaciente().getId());
      turnoRecibido.getOdontologo().setId(turnoAActualizar.getOdontologo().getId());
      turnoAActualizar = turnoRecibido;

      //turnoAActualizar.setNombre(turnoRecibido.getNombre());
      //turnoAActualizar.setApellido(turnoRecibido.getApellido());
      //turnoAActualizar.setDni(turnoRecibido.getDni());
      //turnoAActualizar.setFechaIngreso(turnoRecibido.getFechaIngreso());
      //turnoAActualizar.getDomicilio().setNumero(turnoRecibido.getDomicilio().getNumero());
      //turnoAActualizar.getDomicilio().setLocalidad(turnoRecibido.getDomicilio().getLocalidad());
      //turnoAActualizar.getDomicilio().setProvincia(turnoRecibido.getDomicilio().getProvincia());

      turnoRepository.save(turnoAActualizar);
      turnoSalidaDto = modelMapper.map(turnoAActualizar, TurnoSalidaDto.class);
      LOGGER.warn("Turno actualizado: {}", JsonPrinter.toString(turnoSalidaDto));

    } else LOGGER.error("No fue posible actualizar el turno porque no se encuentra en nuestra base de datos");
    //lanzar exception

    return turnoSalidaDto;
  }
  /*private void configureMapping(){
    modelMapper.typeMap(TurnoEntradaDto.class, Turno.class)
            //.addMappings(mapper -> mapper.map(TurnoEntradaDto::getDomicilioEntradaDto, Turno::setDomicilio));
            .addMappings(mapper -> mapper.map(TurnoEntradaDto::getPacienteEntradaDto, Turno::setPaciente,
                    TurnoEntradaDto::getOdontologoEntradaDto, Turno::setOdontologo));
    modelMapper.typeMap(Turno.class, TurnoSalidaDto.class)
            .addMappings(mapper -> mapper.map(Turno::getDomicilio, TurnoSalidaDto::setDomicilioSalidaDto));
  }*/
  private void configureMapping() {
    // Mapeo de PacienteEntradaDto a Paciente
    modelMapper.typeMap(PacienteEntradaDto.class, Paciente.class)
            .addMappings(mapper -> mapper.map(PacienteEntradaDto::getDomicilioEntradaDto, Paciente::setDomicilio));

    // Mapeo de Paciente a PacienteSalidaDto
    modelMapper.typeMap(Paciente.class, PacienteSalidaDto.class)
            .addMappings(mapper -> mapper.map(Paciente::getDomicilio, PacienteSalidaDto::setDomicilioSalidaDto));


    // Mapeo de OdontologoSalidaDto a Odontologo
    modelMapper.typeMap(OdontologoSalidaDto.class, Odontologo.class)
            .addMappings(mapper -> mapper.map(OdontologoSalidaDto::getId, Odontologo::setId));
    //Mapeo de TurnoEntradaDto a Turno
    modelMapper.typeMap(TurnoEntradaDto.class, Turno.class)
            .addMappings(mapper -> {
              mapper.map(TurnoEntradaDto::getPacienteEntradaDto, Turno::setPaciente);
              mapper.map(TurnoEntradaDto::getOdontologoEntradaDto, Turno::setOdontologo);
            });
    //Mapeo de Turno a TurnoSalidaDto
    modelMapper.typeMap(Turno.class, TurnoSalidaDto.class)
            .addMappings(mapper -> {
              mapper.map(Turno::getPaciente, TurnoSalidaDto::setPacienteSalidaDto);
              mapper.map(Turno::getOdontologo, TurnoSalidaDto::setOdontologoSalidaDto);
            });
  }
}
