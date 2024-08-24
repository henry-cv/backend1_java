package com.backend.dbconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class H2Connection {
  private static final String DB_JDBC_DRIVER = "org.h2.Driver";
  private static final String DB_URL= "jdbc:h2:~/avionesdb2";
  private static final String DB_USER= "sa";
  private static final String DB_PASSWORD= "sa";

  public static Connection getConnection() throws ClassNotFoundException, SQLException{
    Class.forName(DB_JDBC_DRIVER);
    return DriverManager.getConnection(DB_URL,DB_USER,DB_PASSWORD);
  }
  public static void crearTabla(){
    Connection connection = null;
    try {
      Class.forName(DB_JDBC_DRIVER);
      connection = DriverManager.getConnection("jdbc:h2:~/avionesh2;INIT=RUNSCRIPT FROM 'createTable.sql'",DB_USER,DB_PASSWORD);
    }catch(Exception exception){
      exception.printStackTrace();
    }finally {
      try {
        connection.close();
      }catch (Exception ex){
        ex.printStackTrace();
      }
    }
  }
}
