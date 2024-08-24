package com.backend.dbconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class H2Connection {
  private static final Logger LOGGER = Logger.getLogger(H2Connection.class.getName());
  private static final String JDBC_URL = "jdbc:h2:~/odontologosdb";
  private static final String JDBC_URL_WITH_SCRIPT = JDBC_URL + ";INIT=RUNSCRIPT FROM 'classpath:statement.sql'";
  private static final String USER = "sa";
  private static final String PASSWORD = "sa";

  static {
    try {
      Class.forName("org.h2.Driver");
    } catch (ClassNotFoundException e) {
      LOGGER.log(Level.SEVERE, "No se pudo cargar el driver H2", e);
      throw new ExceptionInInitializerError(e);
    }
  }

  public static Connection getConnection() throws SQLException {
    return DriverManager.getConnection(JDBC_URL, USER, PASSWORD);
  }

  public static void inicializarScript() {
    try {
      Connection connection = DriverManager.getConnection(JDBC_URL_WITH_SCRIPT, USER, PASSWORD);
      LOGGER.info("Conexión establecida. Script SQL ejecutado.");
    } catch (SQLException e) {
      LOGGER.log(Level.SEVERE, "Error al inicializar la base de datos", e);
    }
  }
}