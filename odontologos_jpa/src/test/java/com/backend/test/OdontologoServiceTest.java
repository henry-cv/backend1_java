package com.backend.test;

import com.backend.dto.entrada.OdontologoEntradaDto;
import com.backend.dto.salida.OdontologoSalidaDto;
import com.backend.service.IOdontologoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class OdontologoServiceTest {

  @Autowired
  IOdontologoService odontologoService;

  @Test
  public void debeSerPosibleRegistrarNuevoOdontologo(){
    OdontologoEntradaDto ingresaOdontologo = new OdontologoEntradaDto("mat-011", "Juliana", "Vera");
    OdontologoSalidaDto odontologoRegistrado = odontologoService.registrarOdontologo(ingresaOdontologo);
      System.out.println("SALIDA SYSTEM OUT: "+odontologoRegistrado);
    assertNotNull(odontologoRegistrado.getId());
  }
  @Test
  void seDebePoderListarTodosLosOdontologosAlmacenadosEnH2CuandoListaDistintaDeCero() {
    List<OdontologoSalidaDto> listadoOdontologos = odontologoService.listarOdontologos();
    assertNotEquals(0, listadoOdontologos.size());
  }

  @Test
  void dadoUnIdExistenteSeDebeBuscarEnH2ElOdontologoCorrespondiente() {
    OdontologoSalidaDto encontrado = odontologoService.buscarOdontologoPorId(2L);
    assertNotNull(encontrado.getId());
  }
  @Test
  void dadoUnIdExistenteSeDebeEliminarOdontologoCorrespondienteEnH2() {
    //En este caso elimina el último de la lista
    Long id = odontologoService.listarOdontologos().get(odontologoService.listarOdontologos().size()-1).getId();
    OdontologoSalidaDto encontrado = odontologoService.buscarOdontologoPorId(id);
    System.err.println("Odontologo Borrado: " + encontrado);
    odontologoService.eliminarOdontologo(id);
    assertNotNull(encontrado);
  }
  @Test
  void dadoUnIdExistenteYDatosSeDebeModificarOdontologoCorrespondienteEnH2() {
    //En este caso modifica el último de la lista
    Long id = odontologoService.listarOdontologos().get(odontologoService.listarOdontologos().size()-1).getId();
    OdontologoEntradaDto actualizaOdontologo = new OdontologoEntradaDto("prueba-001","Antonio","Lamas");
    OdontologoSalidaDto odontologoSalidaDto = odontologoService.actualizarOdontologo(actualizaOdontologo, id);
    assertNotNull(odontologoSalidaDto);
  }
}