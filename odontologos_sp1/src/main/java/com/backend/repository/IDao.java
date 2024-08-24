package com.backend.repository;

import java.util.List;

public interface IDao<T> {
  T registrar(T t);

  T buscar(Long id);

  T eliminar(Long id);

  List<T> listar();
}
