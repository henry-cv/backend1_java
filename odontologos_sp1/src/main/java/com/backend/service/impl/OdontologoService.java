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
import org.springframework.aop.framework.autoproxy.BeanFactoryAdvisorRetrievalHelper;
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
  public OdontologoSalidaDto registrarOdontologo(OdontologoEntradaDto odontologoEntradaDto) {
    LOGGER.info("OdontologoEntradaDto: {}", JsonPrinter.toString(odontologoEntradaDto));
    //Imprime a String el odóntologo de Entrada DTO, y es registrado al LOGGER

    Odontologo entidadOdontologo = modelMapper.map(odontologoEntradaDto, Odontologo.class);
    // el mapper convierte el odontólogo Entrada a un Odontólogo Entidad

    LOGGER.info("EntidadOdontologo: {}", JsonPrinter.toString(entidadOdontologo));
    //Imprime a String el odóntologo Entidad, y es registrado al LOGGER

    Odontologo odontologoRegistrado = odontologoIDao.registrar(entidadOdontologo);
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
  public OdontologoSalidaDto buscarOdontologoPorId(Long id){
    Odontologo odontologoBuscado = odontologoIDao.buscar(id);
    //Primero el IDao busca un odontologo por id, y lo guarda en odontologoBuscado
    if(odontologoBuscado == null) throw new NullPointerException("No existe odontólogo con ese id");
    return modelMapper.map(odontologoBuscado, OdontologoSalidaDto.class);
    //El mapper convierte ese odontologoBuscado a un OdontologoSalidaDto
  }

  @Override
  public OdontologoSalidaDto eliminarOdontologo(Long id) {
    Odontologo odontologoEncontrado = odontologoIDao.buscar(id);
    // Se envia a buscar el odontologo a eliminar y se guarda en odontologoEncontrado
    if(odontologoEncontrado != null){
      LOGGER.warn("Se ha eliminado el odontólogo con id {}", id);
      odontologoIDao.eliminar(id);
      //Si lo encuentra se elimina el odontologo con ese id
    }
    return modelMapper.map(odontologoEncontrado, OdontologoSalidaDto.class);
    //El mapper convierte en odontologoEncontrado a OdontologoSalidaDto
  }

  @Override
  public OdontologoSalidaDto actualizarOdontologo(OdontologoEntradaDto odontologoEntradaDto, Long id) {
    Odontologo odontologoBuscado = odontologoIDao.buscar(id);
    LOGGER.info("Odontologo Buscado por Id: {}", JsonPrinter.toString(odontologoBuscado));
    //PENDIENTE NO HAY EN REPOSITORY H2 UN MÉTODO ACTUALIZAR

    LOGGER.info("OdontologoEntradaDto: {}", JsonPrinter.toString(odontologoEntradaDto));
    //Imprime a String el odóntologo de Entrada DTO, y es registrado al LOGGER

    Odontologo entidadOdontologo = modelMapper.map(odontologoEntradaDto, Odontologo.class);
    // el mapper convierte el odontólogo Entrada a un Odontólogo Entidad

    LOGGER.info("EntidadOdontologo: {}", JsonPrinter.toString(entidadOdontologo));
    //Imprime a String el odóntologo Entidad, y es registrado al LOGGER

    Odontologo odontologoRegistrado = odontologoIDao.registrar(entidadOdontologo);
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
