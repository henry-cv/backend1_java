package com.backend.dbconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class H2Connection {

  public static Connection getConnection() throws ClassNotFoundException, SQLException {
    Class.forName("org.h2.Driver");
    return DriverManager.getConnection("jdbc:h2:~/odontologosdb", "sa", "sa");
  }

  public static void inicializarScript() {
    Connection connection = null;
    try {
      Class.forName("org.h2.Driver");
      connection = DriverManager.getConnection("jdbc:h2:~/odontologosdb;INIT=RUNSCRIPT FROM 'statement.sql'", "sa",
              "sa");
    } catch (Exception exception) {
      exception.printStackTrace();
    } finally {
      try {
        connection.close();
      } catch (Exception ex) {
        ex.printStackTrace();
      }
    }
  }


}
