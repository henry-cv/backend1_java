package com.backend.service.impl;

import com.backend.entity.Odontologo;
import com.backend.repository.IDao;
import com.backend.service.IOdontologoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OdontologoService implements IOdontologoService {

  @Autowired
  private final IDao<Odontologo> odontologoDao;

  public OdontologoService(IDao<Odontologo> odontologoDao) {
    this.odontologoDao = odontologoDao;

  }

  @Override
  public Odontologo registrarOdontologo(Odontologo odontologo) {
    return odontologoDao.registrar(odontologo);
  }

  @Override
  public Odontologo buscarOdontologoPorId(Long id) {
    return odontologoDao.buscar(id);
  }

  @Override
  public Odontologo eliminarOdontologo(Long id) {
    return odontologoDao.eliminar(id);
  }

  @Override
  public List<Odontologo> listarOdontologos() {
    return odontologoDao.listar();
  }
}
