package com.backend.service.impl;

import com.backend.dto.entrada.OdontologoEntradaDto;
import com.backend.dto.salida.OdontologoSalidaDto;
import com.backend.entity.Odontologo;
import com.backend.exceptions.ResourceNotFoundException;
import com.backend.repository.OdontologoRepository;
import com.backend.service.IOdontologoService;
import com.backend.utils.JsonPrinter;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OdontologoService implements IOdontologoService {
  private static final Logger LOGGER =
          LoggerFactory.getLogger(OdontologoService.class.getName());
  @Autowired
  private final OdontologoRepository odontologoRepository;
  private final ModelMapper modelMapper;

  public OdontologoService(OdontologoRepository odontologoRepository,
                           ModelMapper modelMapper) {
    this.odontologoRepository = odontologoRepository;
    this.modelMapper = modelMapper;
  }

  @Override
  @Transactional
  public OdontologoSalidaDto registrarOdontologo(
          OdontologoEntradaDto odontologoEntradaDto) {
    LOGGER.info("OdontologoEntradaDto: {}",
            JsonPrinter.toString(odontologoEntradaDto));

    Odontologo entidadOdontologo = modelMapper.map(odontologoEntradaDto,
            Odontologo.class);

    LOGGER.info("EntidadOdontologo: {}",
            JsonPrinter.toString(entidadOdontologo));

    Odontologo odontologoRegistrado =
            odontologoRepository.save(entidadOdontologo);

    LOGGER.info("OdontologoRegistrado: {}",
            JsonPrinter.toString(odontologoRegistrado));

    OdontologoSalidaDto odontologoSalidaDto =
            modelMapper.map(odontologoRegistrado, OdontologoSalidaDto.class);

    LOGGER.info("OdontologoSalidaDto: {}",
            JsonPrinter.toString(odontologoSalidaDto));

    return odontologoSalidaDto;
  }

  @Override
  public OdontologoSalidaDto buscarOdontologoPorId(Long id) {
    Odontologo odontologoBuscado =
            odontologoRepository.findById(id).orElse(null);
    LOGGER.info("Odontologo buscado: {}",
            JsonPrinter.toString(odontologoBuscado));
    OdontologoSalidaDto odontologoEncontrado = null;
    if(odontologoBuscado != null) {
      odontologoEncontrado = modelMapper.map(odontologoBuscado,
              OdontologoSalidaDto.class);
      LOGGER.info("Odontologo encontrado: {}",
              JsonPrinter.toString(odontologoEncontrado));
    } else
      LOGGER.error("No se ha encontrado el odontologo con id {}", id);
    return odontologoEncontrado;
  }
  /*
  @Override
  public void eliminarOdontologo(Long id) throws ResourceNotFoundException {
    Odontologo odontologoEncontrado =
            odontologoRepository.findById(id).orElse(null);
    if(odontologoEncontrado != null) {
      LOGGER.warn("Se ha eliminado el odontólogo {}", odontologoEncontrado);
      odontologoRepository.deleteById(id);
      LOGGER.warn("Se ha eliminado el odontologo con id {}", id);
    } else {
      throw new ResourceNotFoundException("Imposible eliminar no existe el " +
              "odontologo con id: " + id);
    }
  }
  */
  @Override
  @Transactional
    public void eliminarOdontologo(Long id) throws ResourceNotFoundException {
        Odontologo odontologo = odontologoRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Odontologo no encontrado"));
        // Ahora podemos eliminar el odontólogo
        odontologoRepository.delete(odontologo);
    }

  @Override
  public OdontologoSalidaDto actualizarOdontologo(
          OdontologoEntradaDto odontologoEntradaDto, Long id) throws ResourceNotFoundException{
    LOGGER.info("OdontologoEntradaDto: {}",
            JsonPrinter.toString(odontologoEntradaDto));

    Odontologo odontologoPorActualizar =
            odontologoRepository.findById(id).orElse(null);
    LOGGER.info("Odontologo por actualizar por Id: {}",
            JsonPrinter.toString(odontologoPorActualizar));

    Odontologo entidadOdontologo = modelMapper.map(odontologoEntradaDto,
            Odontologo.class);

    LOGGER.info("EntidadOdontologo: {}",
            JsonPrinter.toString(entidadOdontologo));
    //Imprime a String el odóntologo Entidad, y es registrado al LOGGER

    OdontologoSalidaDto odontologoActualizado = null;
    if(odontologoPorActualizar != null) {
      entidadOdontologo.setId(odontologoPorActualizar.getId());
      odontologoPorActualizar = entidadOdontologo;
      odontologoRepository.save(odontologoPorActualizar);
      odontologoActualizado = modelMapper.map(odontologoPorActualizar,
              OdontologoSalidaDto.class);
      LOGGER.warn("Odontologo actualizado: {}",
              JsonPrinter.toString(odontologoActualizado));
    } else {
      LOGGER.error("No fue posible actualizar el odontologo porque no se " +
              "encuentra en nuestra base de datos");
      throw new ResourceNotFoundException("No fue posible actualizar el " +
              "odontólogo con id: "+ id +", registro inexistente");
    }
    return odontologoActualizado;
  }

  @Override
  public List<OdontologoSalidaDto> listarOdontologos() {
    List<OdontologoSalidaDto> odontologosSalidaDtos =
            odontologoRepository.findAll().stream().map(odontologo -> modelMapper.map(odontologo, OdontologoSalidaDto.class)).toList();
    LOGGER.info("Listado de todos los odontologos: {}",
            JsonPrinter.toString(odontologosSalidaDtos));
    return odontologosSalidaDtos;
  }
}
