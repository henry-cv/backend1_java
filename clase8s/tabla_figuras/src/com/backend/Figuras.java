package com.backend;

import org.apache.log4j.Logger;

//import java.sql.Connection;
import java.sql.*;
//import static java.sql.DriverManager.getConnection;

public class Figuras {
  private static final Logger LOGGER = Logger.getLogger(Figuras.class);

  public static void main(String[] args) {
    Connection connection = null;
    try {
      connection = getConnection();
      String create = "DROP TABLE IF EXISTS FIGURAS; CREATE TABLE FIGURAS(ID INT AUTO_INCREMENT PRIMARY KEY, FIGURA VARCHAR(20) NOT NULL, COLOR VARCHAR(20) NOT NULL)";
      //crear la tabla
      Statement statement = connection.createStatement();
      statement.execute(create);

      String insert = "INSERT INTO FIGURAS(FIGURA, COLOR) VALUES ('Circulo', 'café'), ('Circulo', 'rojo'),('Cuadrado', 'azul'), ('Cuadrado', 'negro'), ('Cuadrado', 'verde')";
      //insertar los registros
      statement.execute(insert);
      //select all
      String select = "SELECT * FROM FIGURAS";
      ResultSet resultSet = statement.executeQuery(select);
      while (resultSet.next()){
        LOGGER.info("Figura: " + resultSet.getInt(1) + " - " + resultSet.getString(2) + " - " + resultSet.getString("color"));
      }
      LOGGER.info("--------------------------------------------------------------------");

      //eliminar un registro
      statement.execute("DELETE FROM FIGURAS WHERE ID = 3");

      resultSet = statement.executeQuery(select);
      while (resultSet.next()){
        LOGGER.info("Figura: " + resultSet.getInt(1) + " - " + resultSet.getString(2) + " - " + resultSet.getString("color"));
      }
    }catch(Exception exception){
      System.err.println("Error: "+exception);
    }
    finally {
      try{
        assert connection != null;
        connection.close();
      } catch(Exception exception){
        LOGGER.error(exception.getMessage());
      }
    }
  }


  public static Connection getConnection() throws ClassNotFoundException, SQLException {
    //indicar que driver vamos a usar para conectarnos
    Class.forName("org.h2.Driver");
    return DriverManager.getConnection("jdbc:h2:~/clase8S", "sa", "sa");
  }
}
