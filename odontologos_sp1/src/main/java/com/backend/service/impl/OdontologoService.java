package com.backend.service.impl;

import com.backend.dto.entrada.OdontologoEntradaDto;
import com.backend.dto.salida.OdontologoSalidaDto;
import com.backend.entity.Odontologo;
import com.backend.repository.IDao;
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
  private final IDao<Odontologo> odontologoIDao;
  private final ModelMapper modelMapper;

  public OdontologoService(IDao<Odontologo> odontologoIDao, ModelMapper modelMapper) {
    this.odontologoIDao = odontologoIDao;
    this.modelMapper = modelMapper;
  }

  @Override
  /*public Odontologo registrarOdontologo(Odontologo odontologo) {
    return odontologoDao.registrar(odontologo);
  }*/
  public OdontologoSalidaDto registrarOdontologo(OdontologoEntradaDto odontologo) {

    LOGGER.info("OdontologoEntradaDto: {}", JsonPrinter.toString(odontologo));
    Odontologo entidadOdontologo = modelMapper.map(odontologo, Odontologo.class);
    LOGGER.info("EntidadOdontologo: {}", JsonPrinter.toString(entidadOdontologo));
    Odontologo odontologoRegistrado = odontologoIDao.registrar(entidadOdontologo);
    LOGGER.info("OdontologoRegistrado: {}", JsonPrinter.toString(odontologoRegistrado));
    OdontologoSalidaDto odontologoSalidaDto = modelMapper.map(odontologoRegistrado, OdontologoSalidaDto.class);
    LOGGER.info("OdontologoSalidaDto: {}", JsonPrinter.toString(odontologoSalidaDto));
    return odontologoSalidaDto;
  }

  @Override
  public OdontologoSalidaDto buscarOdontologoPorId(Long id){
    Odontologo odontologoBuscado = odontologoIDao.buscar(id);
    return modelMapper.map(odontologoBuscado, OdontologoSalidaDto.class);
  }

  @Override
  public OdontologoSalidaDto eliminarOdontologo(Long id) {
    Odontologo odontologoEncontrado = odontologoIDao.buscar(id);
    if(odontologoEncontrado != null){
      LOGGER.warn("Se ha eliminado el paciente con id {}", id);
      odontologoIDao.eliminar(id);
    }
    return modelMapper.map(odontologoEncontrado, OdontologoSalidaDto.class);
  }

  @Override
  public List<OdontologoSalidaDto> listarOdontologos() {
    List<OdontologoSalidaDto> odontologosSalidaDtos = odontologoIDao.listar()
            .stream()
            .map(odontologo -> modelMapper.map(odontologo, OdontologoSalidaDto.class))
            .toList();
    LOGGER.info("Listado de todos los odontologos: {}", JsonPrinter.toString(odontologosSalidaDtos));
    return odontologosSalidaDtos;
  }
  /*private void configureMapping(){
    modelMapper.typeMap(OdontologoEntradaDto.class, Odontologo.class)
            .addMappings(mapper -> mapper.map(OdontologoEntradaDto::getDomicilioEntradaDto, Odontologo::setDomicilio));
    modelMapper.typeMap(Odontologo.class, OdontologoSalidaDto.class)
            .addMappings(mapper -> mapper.map(Odontologo::getDomicilio, OdontologoSalidaDto::setDomicilioSalidaDto));
  }*/
}
