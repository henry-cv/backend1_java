package com.backend.service.impl;

import com.backend.modelo.Usuario;
import com.backend.service.Descarga;

public class ServiceDescarga implements Descarga {
  @Override
  public String descargar(Usuario usuario) {
    return "Usuario: "+usuario.getIdentificador()+ ", puede realizar la descarga";
  }
}
