package com.backend.service;
import com.backend.entity.Avion;
import java.util.List;

public interface IAvionService {
  Avion registrarAvion(Avion avion);
  Avion buscarAvion(Long id);
  Avion eliminarAvion(Long id);
  List<Avion> listarAviones();
}
