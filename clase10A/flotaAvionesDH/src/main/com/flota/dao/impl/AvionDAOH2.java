package main.com.flota.dao.impl;

import main.com.flota.dao.IDao;
import main.com.flota.model.Avion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AvionDAOH2 implements IDao<Avion> {
  private static final String DB_JDBC_DRIVER = "org.h2.Driver";
  private static final String DB_URL = "jdbc:h2:~/avionesdb";
  private static final String DB_USER = "sa";
  private static final String DB_PASSWORD = "sa";

  public AvionDAOH2() {
  }

  @Override
  public Avion registrarAvion(Avion avion) {
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    try {
      //1 Levantar el driver y conectarnos
      Class.forName(DB_JDBC_DRIVER);
      connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

      //2 Crear una sentencia
      preparedStatement = connection.prepareStatement("INSERT INTO aviones VALUES (?, ?, ?, ?, ?)");
      preparedStatement.setLong(1, avion.getId());
      preparedStatement.setString(2, avion.getMarca());
      preparedStatement.setString(3, avion.getModelo());
      preparedStatement.setString(4, avion.getMatricula());
      preparedStatement.setString(5, avion.getFechaEntrada());

      //3 Ejecutar la sentencia SQL
      preparedStatement.executeUpdate();
      preparedStatement.close();
    } catch (SQLException | ClassNotFoundException throwables) {
      throwables.printStackTrace();
    }
    return avion;
  }

  @Override
  public Avion buscarAvion(Long id) {
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    Avion avion = null;

    try {
      //1 Levantar el driver y conectarnos
      Class.forName(DB_JDBC_DRIVER);
      connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

      //2 Crear una sentencia
      preparedStatement = connection.prepareStatement("SELECT id, marca, modelo, matricula, fechaEntrada FROM aviones WHERE id = ? ");
      preparedStatement.setLong(1, id);

      //3 Ejecutar una sentencia
      ResultSet resultAvion = preparedStatement.executeQuery();

      //4 Obtener los resultados
      while (resultAvion.next()) {
        Long idAvion = resultAvion.getLong("id");
        String marca = resultAvion.getString("marca");
        String modelo = resultAvion.getString("modelo");
        String matricula = resultAvion.getString("matricula");
        String fechaEntrada = resultAvion.getString("fechaEntrada");
        avion = new Avion(idAvion, marca, modelo, matricula, fechaEntrada);
      }
      preparedStatement.close();
    } catch (SQLException | ClassNotFoundException throwables) {
      throwables.printStackTrace();
    }

    return avion;
  }

  @Override
  public void eliminarAvion(Long id) {
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    try {
      //1 Levantar el driver y Conectarnos
      Class.forName(DB_JDBC_DRIVER);
      connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

      //2 Crear una sentencia
      preparedStatement = connection.prepareStatement("DELETE FROM aviones where id = ?");
      preparedStatement.setLong(1, id);

      // 2.1 Imprimir Avion a Eliminar
      System.out.println("Avion a eliminar: "+buscarAvion(id));
      //3 Ejecutar una sentencia SQL
      preparedStatement.executeUpdate();
      preparedStatement.close();
    } catch (SQLException | ClassNotFoundException throwables) {
      throwables.printStackTrace();
    }

  }

  @Override
  public List<Avion> buscarTodos() {
    List<Avion> aviones = new ArrayList<Avion>();
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    try {
      Class.forName(DB_JDBC_DRIVER);
      connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

      preparedStatement = connection.prepareStatement("SELECT * FROM aviones");
      ResultSet resultLista = preparedStatement.executeQuery();

      while (resultLista.next()) {
        Long id = resultLista.getLong("id");
        String marca = resultLista.getString("marca");
        String modelo = resultLista.getString("modelo");
        String matricula = resultLista.getString("matricula");
        String fecha = resultLista.getString("fechaEntrada");
        aviones.add(new Avion(id, marca, modelo, matricula, fecha));
      }
    } catch (SQLException | ClassNotFoundException throwables) {
      throwables.printStackTrace();
    }
    return aviones;
  }
}
