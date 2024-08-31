package com.backend.repository.impl;

import com.backend.entity.Odontologo;
import com.backend.repository.IDao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

public class OdontologoDaoMemoria implements IDao<Odontologo> {
  private static final List<Odontologo> listaOdontologos = new ArrayList<>();
  private static final Logger LOGGER = LoggerFactory.getLogger(OdontologoDaoMemoria.class);
  private static Long localId = 1L;

  @Override
  public Odontologo registrar(Odontologo odontologo) {
    Odontologo odontologoX = new Odontologo(odontologo.getMatricula(), odontologo.getNombre(),
            odontologo.getApellido());
    odontologoX.setId(localId);
    listaOdontologos.add(odontologoX);
    localId++;
    LOGGER.info("Odontologo registrado: " + odontologoX);
    return odontologoX;
  }

  @Override
  public Odontologo buscar(Long id) {
    Odontologo buscado = null;
    for(Odontologo odontologo : listaOdontologos) {
      if(odontologo.getId().equals(id)) {
        buscado = odontologo;
        return buscado;
      }
    }
    LOGGER.info("Odontologo buscado: " + buscado);
    return buscado;
  }

  @Override
  public Odontologo eliminar(Long id) {
    Odontologo odontologoEliminado = buscar(id);
    LOGGER.info("Odontologo Eliminado: " + odontologoEliminado);
    listaOdontologos.remove(odontologoEliminado);
    return odontologoEliminado;
  }

  @Override
  public List<Odontologo> listar() {
    LOGGER.info("Listando Todos los Odontologos: " + listaOdontologos);
    return listaOdontologos;
  }
}
