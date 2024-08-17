package com.backend.service.impl;

import com.backend.entity.Odontologo;
import com.backend.repository.IDao;
import com.backend.service.IOdontologoService;

import java.util.List;

public class OdontologoService implements IOdontologoService {
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
  public List<Odontologo> listarOdontologos() {
    return odontologoDao.listar();
  }
}
