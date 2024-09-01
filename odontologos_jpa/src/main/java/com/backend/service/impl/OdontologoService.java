package com.backend.service.impl;

import com.backend.dto.entrada.OdontologoEntradaDto;
import com.backend.dto.salida.OdontologoSalidaDto;
import com.backend.entity.Odontologo;
import com.backend.repository.OdontologoRepository;
import com.backend.service.IOdontologoService;
import com.backend.utils.JsonPrinter;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OdontologoService implements IOdontologoService {
  private static final Logger LOGGER = LoggerFactory.getLogger(OdontologoService.class.getName());
  @Autowired
  private final OdontologoRepository odontologoRepository;
  private final ModelMapper modelMapper;

  public OdontologoService(OdontologoRepository odontologoRepository, ModelMapper modelMapper) {
    this.odontologoRepository = odontologoRepository;
    this.modelMapper = modelMapper;
  }

  @Override
  /*public Odontologo registrarOdontologo(Odontologo odontologo) {
    return odontologoDao.registrar(odontologo);
  }*/ public OdontologoSalidaDto registrarOdontologo(OdontologoEntradaDto odontologoEntradaDto) {
    LOGGER.info("OdontologoEntradaDto: {}", JsonPrinter.toString(odontologoEntradaDto));
    //Imprime a String el odóntologo de Entrada DTO, y es registrado al LOGGER

    Odontologo entidadOdontologo = modelMapper.map(odontologoEntradaDto, Odontologo.class);
    // el mapper convierte el odontólogo Entrada a un Odontólogo Entidad

    LOGGER.info("EntidadOdontologo: {}", JsonPrinter.toString(entidadOdontologo));
    //Imprime a String el odóntologo Entidad, y es registrado al LOGGER

    Odontologo odontologoRegistrado = odontologoRepository.save(entidadOdontologo);
    // Se agrega o registra un nuevo Odontólogo mediante el IDao y se guarda el objeto en odontologoRegistrado

    LOGGER.info("OdontologoRegistrado: {}", JsonPrinter.toString(odontologoRegistrado));
    //Imprime a String el odóntologo Registrado, y es registrado al LOGGER

    OdontologoSalidaDto odontologoSalidaDto = modelMapper.map(odontologoRegistrado, OdontologoSalidaDto.class);
    //El mapper toma el odontologoRegistado y lo convierte a un odontologoSalida DTO

    LOGGER.info("OdontologoSalidaDto: {}", JsonPrinter.toString(odontologoSalidaDto));
    //Imprime a String el odóntologo Salida DTO, y es registrado al LOGGER

    return odontologoSalidaDto;
  }

  @Override
  public OdontologoSalidaDto buscarOdontologoPorId(Long id) {
    Odontologo odontologoBuscado = odontologoRepository.findById(id).orElse(null);
    //Primero el IDao busca un odontologo por id, y lo guarda en odontologoBuscado
    if(odontologoBuscado == null)
      throw new NullPointerException("No existe odontólogo con ese id");
    return modelMapper.map(odontologoBuscado, OdontologoSalidaDto.class);
    //El mapper convierte ese odontologoBuscado a un OdontologoSalidaDto
  }

  @Override
  public void eliminarOdontologo(Long id) {
    Odontologo odontologoEncontrado = odontologoRepository.findById(id).orElse(null);
    // Se envia a buscar el odontologo a eliminar y se guarda en odontologoEncontrado
    if(odontologoEncontrado != null) {
      LOGGER.warn("Se ha eliminado el odontólogo con id {}", id);
      odontologoRepository.deleteById(id);
      //Si lo encuentra se elimina el odontologo con ese id
      LOGGER.warn("Se ha eliminado el odontologo con id {}", id);
    } else {
      //excepcion resource not found
      LOGGER.warn("no Se ha eliminado porque no se encontró el odontologo con id {}", id);
    }

  }

  @Override

  public OdontologoSalidaDto actualizarOdontologo(OdontologoEntradaDto odontologoEntradaDto, Long id) {
    Odontologo odontologoPorActualizar = odontologoRepository.findById(id).orElse(null);
    LOGGER.info("Odontologo por actualizar por Id: {}", JsonPrinter.toString(odontologoPorActualizar));
    //PENDIENTE NO HAY EN REPOSITORY H2 UN MÉTODO ACTUALIZAR

    LOGGER.info("OdontologoEntradaDto: {}", JsonPrinter.toString(odontologoEntradaDto));
    //Imprime a String el odóntologo de Entrada DTO, y es registrado al LOGGER

    Odontologo entidadOdontologo = modelMapper.map(odontologoEntradaDto, Odontologo.class);
    // el mapper convierte el odontólogo Entrada a un Odontólogo Entidad

    LOGGER.info("EntidadOdontologo: {}", JsonPrinter.toString(entidadOdontologo));
    //Imprime a String el odóntologo Entidad, y es registrado al LOGGER

    OdontologoSalidaDto odontologoActualizado = null;
    if(odontologoPorActualizar != null){
      entidadOdontologo.setId(odontologoPorActualizar.getId());
      odontologoPorActualizar = entidadOdontologo;
      odontologoRepository.save(odontologoPorActualizar);
      odontologoActualizado = modelMapper.map(odontologoPorActualizar, OdontologoSalidaDto.class);
      LOGGER.warn("Odontologo actualizado: {}", JsonPrinter.toString(odontologoActualizado));
    }else{
      LOGGER.error("No fue posible actualizar el odontologo porque no se encuentra en nuestra base de datos");
    }
    return odontologoActualizado;
  }

  @Override
  public List<OdontologoSalidaDto> listarOdontologos() {
    List<OdontologoSalidaDto> odontologosSalidaDtos =
            odontologoRepository.findAll().stream().map(odontologo -> modelMapper.map(odontologo,
                    OdontologoSalidaDto.class)).toList();
    LOGGER.info("Listado de todos los odontologos: {}", JsonPrinter.toString(odontologosSalidaDtos));
    return odontologosSalidaDtos;
  }
}
