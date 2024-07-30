package com.backend.test;

import com.backend.modelo.TipoUsuario;
import com.backend.modelo.Usuario;
import com.backend.service.impl.ProxyDescarga;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;

import static org.junit.jupiter.api.Assertions.*;

public class ProxyDescargaTest {
  private final ProxyDescarga PROXY = new ProxyDescarga();

  @Test
  @Tag("Caso 1, free")
  @DisplayName("Usuario Free no puede descargar")
  public void dadoUsuarioFreeMostrarMensajeNoPuedeDescargar(){
    // arrange
    Usuario userFree = new Usuario("free-1234", TipoUsuario.FREE);
    String respuestaEsperada = "Usuario: "+userFree.getIdentificador()+ " no puede realizar la descarga";

    //act
    String respuestaObtenida = PROXY.descargar(userFree);
    //assert
    assertEquals(respuestaEsperada, respuestaObtenida);
  }
  @Test
  @Tag("Caso 2, premium")
  @DisplayName("Usuario Premium puede descargar")
  public void dadoUsuarioPremiumMostrarMensajePuedeDescargar(){
    // arrange
    Usuario userPremium = new Usuario("premium-0001", TipoUsuario.PREMIUM);
    String respuestaEsperada = "Usuario: "+ userPremium.getIdentificador()+ ", puede realizar la descarga";

    //act
    String respuestaObtenida = PROXY.descargar(userPremium);
    //assert
    assertEquals(respuestaEsperada, respuestaObtenida);
  }
}