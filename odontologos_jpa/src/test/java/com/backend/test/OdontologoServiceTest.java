package com.backend.test;

import com.backend.dto.entrada.OdontologoEntradaDto;
import com.backend.dto.salida.OdontologoSalidaDto;
import com.backend.service.IOdontologoService;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class OdontologoServiceTest {

  @Autowired
  IOdontologoService odontologoService;

  @Test @Order(1)
  public void debeSerPosibleRegistrarNuevoOdontologo(){
    OdontologoEntradaDto ingresaOdontologo = new OdontologoEntradaDto("mat-011", "Juliana", "Vera");
    OdontologoSalidaDto odontologoRegistrado = odontologoService.registrarOdontologo(ingresaOdontologo);
      System.out.println("SALIDA SYSTEM OUT: "+odontologoRegistrado);
    assertNotNull(odontologoRegistrado.getId());
  }
  @Test @Order(3)
  void seDebePoderListarTodosLosOdontologosAlmacenadosEnH2CuandoListaDistintaDeCero() {
    List<OdontologoSalidaDto> listadoOdontologos = odontologoService.listarOdontologos();
    assertNotEquals(0, listadoOdontologos.size());
  }

  @Test @Order(2)
  void dadoUnIdExistenteSeDebeBuscarEnH2ElOdontologoCorrespondiente() {
    OdontologoSalidaDto encontrado = odontologoService.buscarOdontologoPorId(2L);
    assertNotNull(encontrado.getId());
  }
  @Test @Order(5)
  void dadoUnIdExistenteSeDebeEliminarOdontologoCorrespondienteEnH2() {
    //En este caso elimina el último de la lista
    Long id = odontologoService.listarOdontologos().get(odontologoService.listarOdontologos().size()-1).getId();
    OdontologoSalidaDto encontrado = odontologoService.buscarOdontologoPorId(id);
    System.err.println("Odontologo Borrado: " + encontrado);
    odontologoService.eliminarOdontologo(id);
    assertNotNull(encontrado);
  }
  @Test @Order(4)
  void dadoUnIdExistenteYDatosSeDebeModificarOdontologoCorrespondienteEnH2() {
    //En este caso modifica el último de la lista
    Long id = odontologoService.listarOdontologos().get(odontologoService.listarOdontologos().size()-1).getId();
    OdontologoEntradaDto actualizaOdontologo = new OdontologoEntradaDto("prueba-001","Antonio","Lamas");
    OdontologoSalidaDto odontologoSalidaDto = odontologoService.actualizarOdontologo(actualizaOdontologo, id);
    assertNotNull(odontologoSalidaDto);
  }
}