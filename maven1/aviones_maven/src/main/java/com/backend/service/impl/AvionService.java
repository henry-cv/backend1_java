package com.backend.service.impl;

import com.backend.entity.Avion;
import com.backend.repository.IDao;
import com.backend.service.IAvionService;
import java.util.List;

public class AvionService implements IAvionService {

  private IDao<Avion> avionIDao;

  public AvionService(IDao<Avion> avionIDao) {
    this.avionIDao = avionIDao;
  }

  public void setAvionIDao(IDao<Avion> avionIDao) {
    this.avionIDao = avionIDao;
  }

  @Override
  public Avion registrarAvion(Avion avion) {
    return avionIDao.registrar(avion);
  }

  @Override
  public Avion buscarAvion(Long id) {
    return avionIDao.buscar(id);
  }

  @Override
  public Avion eliminarAvion(Long id) {
    return avionIDao.eliminar(id);
  }

  @Override
  public List<Avion> listarAviones() {
    return avionIDao.listarTodos();
  }
}
