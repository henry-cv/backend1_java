package com.backend.service.impl;

import com.backend.dto.entrada.PacienteEntradaDto;
import com.backend.dto.entrada.TurnoEntradaDto;
import com.backend.dto.salida.DomicilioSalidaDto;
import com.backend.dto.salida.OdontologoSalidaDto;
import com.backend.dto.salida.PacienteSalidaDto;
import com.backend.dto.salida.TurnoSalidaDto;
import com.backend.entity.Domicilio;
import com.backend.entity.Odontologo;
import com.backend.entity.Paciente;
import com.backend.entity.Turno;
import com.backend.exceptions.BadRequestException;
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

  private final TurnoRepository turnoRepository;
  private final ModelMapper modelMapper;
  private final OdontologoService odontologoService;
  private final PacienteService pacienteService;

  public TurnoService(TurnoRepository turnoRepository, ModelMapper modelMapper, OdontologoService odontologoService,
                      PacienteService pacienteService) {
    this.turnoRepository = turnoRepository;
    this.modelMapper = modelMapper;
    this.odontologoService = odontologoService;
    this.pacienteService = pacienteService;
    configureMapping();
  }

  @Override
  public TurnoSalidaDto registrarTurno(TurnoEntradaDto turnoEntradaDto)throws BadRequestException {
    Long idPaciente = turnoEntradaDto.getPacienteId();
    Long idOdontologo = turnoEntradaDto.getOdontologoId();

    // Verificar si existen
    PacienteSalidaDto pacienteEncontrado = pacienteService.buscarPacientePorId(idPaciente);
    OdontologoSalidaDto odontologoEncontrado = odontologoService.buscarOdontologoPorId(idOdontologo);

    if(pacienteEncontrado == null) {
      throw new BadRequestException("Paciente con ID " + idPaciente + " no existe.");
    }
    if(odontologoEncontrado == null) {
      throw new BadRequestException("Odontólogo con ID " + idOdontologo + " no existe.");
    }

    LOGGER.info("TurnoEntradaDto: {}", JsonPrinter.toString(turnoEntradaDto));

    //Recibe un TurnoEntradato y convierte a una Entidad Turno
    Turno entidadTurno = modelMapper.map(turnoEntradaDto, Turno.class);
    LOGGER.info("EntidadTurno antes de setear paciente y odontologo: {}", JsonPrinter.toString(entidadTurno));

    // Setea los datos de paciente y odontologo
    entidadTurno.setPaciente(modelMapper.map(pacienteEncontrado, Paciente.class));
    entidadTurno.setOdontologo(modelMapper.map(odontologoEncontrado, Odontologo.class));
    entidadTurno.setFechaHora(turnoEntradaDto.getFechaHora());
    LOGGER.info("EntidadTurno después de setear paciente y odontologo: {}", JsonPrinter.toString(entidadTurno));

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
    if(turnoBuscado != null) {
      turnoEncontrado = modelMapper.map(turnoBuscado, TurnoSalidaDto.class);
      LOGGER.info("Turno encontrado: {}", JsonPrinter.toString(turnoEncontrado));
    } else {
      LOGGER.error("No se ha encontrado el turno con id {}", id);
    }
    return turnoEncontrado;
  }

  @Override
  public List<TurnoSalidaDto> listarTurnos() {
    List<TurnoSalidaDto> turnosSalidaDtos = turnoRepository.findAll().stream().map(turno -> modelMapper.map(turno,
            TurnoSalidaDto.class)).toList();
    LOGGER.info("Listado de todos los turnos: {}", JsonPrinter.toString(turnosSalidaDtos));

    return turnosSalidaDtos;
  }

  @Override
  public void eliminarTurno(Long id) {
    if(buscarTurnoPorId(id) != null) {
      //llamada a la capa repositorio para eliminar
      turnoRepository.deleteById(id);
      LOGGER.warn("Se ha eliminado el turno con id {}", id);
    } else {
      //excepcion resource not found
      LOGGER.error("No se pudo eliminar porque no existe turno con ese id: " + id);
    }
  }

  @Override
  public TurnoSalidaDto actualizarTurno(TurnoEntradaDto turnoEntradaDto, Long id) {
    Turno turnoAActualizar = turnoRepository.findById(id).orElse(null);
    Turno turnoRecibido = modelMapper.map(turnoEntradaDto, Turno.class);
    TurnoSalidaDto turnoSalidaDto = null;

    if(turnoAActualizar != null) {

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

    } else
      LOGGER.error("No fue posible actualizar el turno porque no se encuentra en nuestra base de datos");
    //lanzar exception

    return turnoSalidaDto;
  }

  private void configureMapping() {
    modelMapper.emptyTypeMap(TurnoEntradaDto.class, Turno.class)
            .addMappings(mapper -> mapper.map(TurnoEntradaDto::getFechaHora, Turno::setFechaHora));

    modelMapper.typeMap(Turno.class, TurnoSalidaDto.class)
            .addMappings(mapper -> mapper.map(Turno::getPaciente, TurnoSalidaDto::setPacienteSalidaDto))
            .addMappings(mapper -> mapper.map(Turno::getOdontologo, TurnoSalidaDto::setOdontologoSalidaDto));

    modelMapper.typeMap(OdontologoSalidaDto.class, Odontologo.class);
    modelMapper.typeMap(PacienteSalidaDto.class, Paciente.class)
            .addMappings(mapper -> mapper.map(PacienteSalidaDto::getDomicilioSalidaDto, Paciente::setDomicilio));
    //agregados por mi desde aqui abajo

    //modelMapper.typeMap(DomicilioSalidaDto.class, Domicilio.class);
  }
}
