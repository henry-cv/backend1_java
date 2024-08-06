package main.com.flota.test;

import main.com.flota.dao.IDao;
import main.com.flota.dao.impl.AvionDAOH2;
import main.com.flota.model.Avion;
import org.junit.BeforeClass;

public class AvionDAOH2Test {
  private static final IDao avionDAO = new AvionDAOH2();

  @BeforeClass
  public static void registrarAvion(){
    avionDAO.registrarAvion(new Avion(1L, "surim", "b3", "abc-001", "2021/08/12"));
    avionDAO.registrarAvion(new Avion(2L, "teslax", "c2", "lab-002", "2022/03/21"));
  }
}