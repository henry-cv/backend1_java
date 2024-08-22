package com.backend.service;

import com.backend.entity.Odontologo;

import java.util.List;

public interface IOdontologoService {
  Odontologo registrarOdontologo(Odontologo odontologo);

  Odontologo buscarOdontologoPorId(Long id);

  Odontologo eliminarOdontologo(Long id);

  List<Odontologo> listarOdontologos();
}
