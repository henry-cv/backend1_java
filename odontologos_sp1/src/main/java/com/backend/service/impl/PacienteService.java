package com.backend.service.impl;

import com.backend.dto.entrada.PacienteEntradaDto;
import com.backend.dto.salida.OdontologoSalidaDto;
import com.backend.dto.salida.PacienteSalidaDto;
import com.backend.entity.Odontologo;
import com.backend.entity.Paciente;
import com.backend.repository.IDao;
import com.backend.service.IPacienteService;
import com.backend.utils.JsonPrinter;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PacienteService implements IPacienteService {


  private final Logger LOGGER = LoggerFactory.getLogger(PacienteService.class);
  @Autowired
  private final IDao<Paciente> pacienteIDao;
  private final ModelMapper modelMapper;

  public PacienteService(IDao<Paciente> pacienteIDao, ModelMapper modelMapper) {
    this.pacienteIDao = pacienteIDao;
    this.modelMapper = modelMapper;
    configureMapping();
  }


  @Override
  public PacienteSalidaDto registrarPaciente(PacienteEntradaDto paciente) {

    LOGGER.info("PacienteEntradaDto: {}", JsonPrinter.toString(paciente));
    Paciente entidadPaciente = modelMapper.map(paciente, Paciente.class);
    LOGGER.info("EntidadPaciente: {}", JsonPrinter.toString(entidadPaciente));
    Paciente pacienteRegistrado = pacienteIDao.registrar(entidadPaciente);
    LOGGER.info("PacienteRegistrado: {}", JsonPrinter.toString(pacienteRegistrado));
    PacienteSalidaDto pacienteSalidaDto = modelMapper.map(pacienteRegistrado, PacienteSalidaDto.class);
    LOGGER.info("PacienteSalidaDto: {}", JsonPrinter.toString(pacienteSalidaDto));
    return pacienteSalidaDto;
  }

  @Override//NO HAY QUE LANZAR LA EXCEPCION ACA
  public PacienteSalidaDto buscarPacientePorId(Long id) {
    Paciente pacienteBuscado = pacienteIDao.buscar(id);
    //Primero el IDao busca un paciente por id, y lo guarda en pacienteBuscado
    if(pacienteBuscado == null)
      throw new NullPointerException("No existe paciente con ese id");
    return modelMapper.map(pacienteBuscado, PacienteSalidaDto.class);
    //El mapper convierte ese pacienteBuscado a un PacienteSalidaDto
  }

  @Override
  public List<PacienteSalidaDto> listarPacientes() {
    List<PacienteSalidaDto> pacienteSalidaDtos =
            pacienteIDao.listar()
                    .stream()
                    .map(paciente -> modelMapper.map(paciente, PacienteSalidaDto.class))
                    .toList();
    LOGGER.info("Listado de todos los pacientes: {}", JsonPrinter.toString(pacienteSalidaDtos));

    return pacienteSalidaDtos;
  }


  public void eliminarPaciente(Long id) {
    Paciente pacienteEncontrado = pacienteIDao.buscar(id);
    if(pacienteEncontrado != null) {
      //llamada a la capa repositorio para eliminar
      LOGGER.warn("Se ha eliminado el paciente con id {}", id);
      pacienteIDao.eliminar(id);
    }
    //modelMapper.map(pacienteEncontrado, PacienteSalidaDto.class);
  }

  @Override
  public PacienteSalidaDto actualizarPaciente(PacienteEntradaDto pacienteEntradaDto, Long id) {
    return null;
  }


  private void configureMapping() {
    modelMapper.typeMap(PacienteEntradaDto.class, Paciente.class).addMappings(mapper -> mapper.map(PacienteEntradaDto::getDomicilioEntradaDto, Paciente::setDomicilio));
    modelMapper.typeMap(Paciente.class, PacienteSalidaDto.class).addMappings(mapper -> mapper.map(Paciente::getDomicilio, PacienteSalidaDto::setDomicilioSalidaDto));
  }
}



