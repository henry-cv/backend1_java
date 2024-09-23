package main.com.flota.test;

import main.com.flota.dao.IDao;
import main.com.flota.dao.impl.AvionDAOH2;
import main.com.flota.model.Avion;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;

@RunWith(JUnit4.class)
public class AvionDAOH2Test {
  private static final IDao avionDAO = new AvionDAOH2();


  @BeforeClass
  public static void registrarAvion(){
    avionDAO.registrarAvion(new Avion(1L, "surim", "b3", "abc-001", "2021/08/12"));
    avionDAO.registrarAvion(new Avion(2L, "teslax", "c2", "lab-002", "2022/03/21"));
    avionDAO.registrarAvion(new Avion(3L,"fonda", "cx3", "abc-001", "2020/08/25"));
  }
  @Test
  public void dadoUnAvionAgregarlo(){
    avionDAO.registrarAvion(new Avion(4L, "acme", "d4", "test-003", "2021/05/11"));
  }
  @Test
  public void dadoUnIdBuscarAvion(){
    Avion encontrado = (Avion) avionDAO.buscarAvion(6L);
    System.out.println(encontrado);
  }
  @Test
  public void dadoUnIdEliminarUnAvion(){
    avionDAO.eliminarAvion(2L);
  }
  @Test
  public void mostrarTodosLosAviones(){
    List<Avion> listaAviones = avionDAO.buscarTodos();
    for (Avion av: listaAviones){
      System.out.println(av);
    }
    System.out.println("Tamaño de la lista:"+listaAviones.size() );
  }

}