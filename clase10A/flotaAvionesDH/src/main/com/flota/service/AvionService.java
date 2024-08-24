package main.com.flota.service;

import main.com.flota.dao.IDao;
import main.com.flota.model.Avion;

import java.util.List;

public class AvionService {
  IDao<Avion> avionDao;

  public AvionService() {
  }

  public AvionService(IDao<Avion> avionDao) {
    this.avionDao = avionDao;
  }

  public IDao getAvionDao() {
    return avionDao;
  }

  public void setAvionDao(IDao<Avion> avionDao) {
    this.avionDao = avionDao;
  }
  public Avion registrarAvion(Avion avion){
    avionDao.registrarAvion(avion);
    return avion;
  }
  public void eliminarAvion(Long id){
    avionDao.eliminarAvion(id);
  }
  public Avion buscarAvion(Long id){
    return avionDao.buscarAvion(id);
  }
  public List<Avion> buscarTodos(){
    return avionDao.buscarTodos();
  }
}
