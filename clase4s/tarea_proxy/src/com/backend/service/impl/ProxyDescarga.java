package com.backend.service.impl;

import com.backend.modelo.TipoUsuario;
import com.backend.modelo.Usuario;
import com.backend.service.Descarga;

public class ProxyDescarga implements Descarga {

  private ServiceDescarga serviceDescarga;

  @Override
  public String descargar(Usuario usuario) {
    String respuesta =
            "Usuario: " +
                    usuario.getIdentificador() +
                    " no puede realizar la descarga";
    if (isPremium(usuario)) {
      serviceDescarga = new ServiceDescarga();
      respuesta = serviceDescarga.descargar(usuario);
    }
    return respuesta;
  }

  public boolean isPremium(Usuario usuario) {
    return (usuario.getTipoUsuario().equals(TipoUsuario.PREMIUM));
  }
}