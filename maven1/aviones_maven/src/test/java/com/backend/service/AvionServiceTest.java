package com.backend.service;

import com.backend.dbconnection.H2Connection;
import com.backend.entity.Avion;
import com.backend.repository.impl.AvionDaoH2;
import com.backend.service.impl.AvionService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AvionServiceTest {
  private AvionService avionService;

  @BeforeAll
  public static void inicializarH2(){
    H2Connection.crearTabla();
  }
  @Test
  public void deberiaRegistrarunAvionEnH2YRetornarSuID(){
    avionService = new AvionService(new AvionDaoH2());
    Avion avionARegistrar = new Avion("jet1","pasajeros","abc-123","2022/08/15");
    Avion avionRegistrado =avionService.registrarAvion(avionARegistrar);
    assertNotNull(avionRegistrado.getId());

  }
  @Test
  public void dadoUnIdDeberiaDevolverUnAvionExistenteEnH2(){
    avionService = new AvionService(new AvionDaoH2());
    deberiaRegistrarunAvionEnH2YRetornarSuID();
    Avion encontrado = avionService.buscarAvion(1L);
    assertNotNull(encontrado.getId());
  }
  @Test
  public void deberiaPoderListarTodosLosAviones(){
    avionService = new AvionService(new AvionDaoH2());
    List<Avion> listadoAviones = avionService.listarAviones();
    System.out.println("Tamaño listadoAviones: "+listadoAviones.size());
    assertNotEquals(0, listadoAviones.size());
  }
  /*@Test
  public void dadoUnIdDeberiaEliminarUnAvionExistenteEnH2(){
    avionService = new AvionService(new AvionDaoH2());
    Avion avionEliminado = avionService.eliminarAvion(14L);
    assertNotNull(avionEliminado.getId());
  }*/
}