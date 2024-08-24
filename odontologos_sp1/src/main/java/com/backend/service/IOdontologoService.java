package com.backend.service;

import com.backend.entity.Odontologo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IOdontologoService {
  Odontologo registrarOdontologo(Odontologo odontologo);

  Odontologo buscarOdontologoPorId(Long id);

  Odontologo eliminarOdontologo(Long id);

  List<Odontologo> listarOdontologos();
}
