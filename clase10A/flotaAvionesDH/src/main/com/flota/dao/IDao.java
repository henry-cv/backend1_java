package main.com.flota.dao;

import java.util.List;

public interface IDao<T> {
  T registrarAvion(T t);
  T buscarAvion(Long id);
  void eliminarAvion(Long id);
  List<T> buscarTodos();
}
