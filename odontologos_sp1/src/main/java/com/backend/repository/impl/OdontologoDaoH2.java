package com.backend.repository.impl;

import com.backend.dbconnection.H2Connection;
import com.backend.entity.Odontologo;
import com.backend.repository.IDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class OdontologoDaoH2 implements IDao<Odontologo> {
  private final Logger LOGGER = LogManager.getLogger(OdontologoDaoH2.class);

  @Override
  public Odontologo registrar(Odontologo odontologo) {
    Odontologo odontologoRegistrado = null;
    Connection connection = null;

    try {
      connection = H2Connection.getConnection();
      connection.setAutoCommit(false);
      String insert = "INSERT INTO ODONTOLOGOS(MATRICULA, NOMBRE, APELLIDO) VALUES(?, ?, ?)";
      PreparedStatement preparedStatement = connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
      preparedStatement.setString(1, odontologo.getMatricula());
      preparedStatement.setString(2, odontologo.getNombre());
      preparedStatement.setString(3, odontologo.getApellido());
      preparedStatement.execute();

      ResultSet resultSet = preparedStatement.getGeneratedKeys();
      odontologoRegistrado = new Odontologo(odontologo.getMatricula(), odontologo.getNombre(),
              odontologo.getApellido());
      while (resultSet.next()) {
        odontologoRegistrado.setId(resultSet.getLong("id"));
      }
      connection.commit();
      LOGGER.info("Se ha registrado el Odontologo: " + odontologoRegistrado);
      return odontologoRegistrado;
    } catch (Exception e) {
      LOGGER.error(e.getMessage());
      e.printStackTrace();
      if(connection != null) {
        try {
          connection.rollback();
          LOGGER.info("Tuvimos un problema");
          LOGGER.error(e.getMessage());
          e.printStackTrace();
        } catch (SQLException exception) {
          LOGGER.error(exception.getMessage());
          exception.printStackTrace();
        }
      }
    } finally {
      try {
        connection.close();
      } catch (Exception ex) {
        LOGGER.error("No se pudo cerrar la conexion: " + ex.getMessage());
      }
    }
    return odontologoRegistrado;
  }

  @Override
  public Odontologo buscar(Long id) {
    Odontologo buscado = null;
    Connection connection = null;
    try {
      connection = H2Connection.getConnection();
      String busqueda = "SELECT * FROM ODONTOLOGOS WHERE id = ?";
      PreparedStatement preparedStatement = connection.prepareStatement(busqueda);
      preparedStatement.setLong(1, id);
      ResultSet resultId = preparedStatement.executeQuery();
      while (resultId.next()) {
        buscado = new Odontologo(resultId.getLong("id"), resultId.getString("matricula"),
                resultId.getString("nombre"), resultId.getString("apellido"));
      }
    } catch (Exception exception) {
      LOGGER.error(exception.getMessage());
      exception.printStackTrace();
    } finally {
      try {
        connection.close();
      } catch (Exception ex) {
        LOGGER.error("Ha ocurrido un error al intentar cerrar la conexión: " + ex.getMessage());
        ex.printStackTrace();
      }
    }
    LOGGER.info("Odontologo encontrado: " + buscado);
    return buscado;
  }

@Override
  public Odontologo eliminar(Long id) {
    Odontologo odontologoEliminado = buscar(id);
    if(odontologoEliminado == null)
      return null;
    Connection connection = null;
    try {
      connection = H2Connection.getConnection();
      connection.setAutoCommit(false);
      String delete = "DELETE FROM ODONTOLOGOS WHERE id = ?";
      PreparedStatement preparedStatement = connection.prepareStatement(delete);
      preparedStatement.setLong(1, id);
      int registros = preparedStatement.executeUpdate();
      connection.commit();
      LOGGER.info("Se ha eliminado: " + registros + " registros");
      LOGGER.info("Odontologo eliminado: " + odontologoEliminado);
      return odontologoEliminado;
    } catch (Exception ex) {
      LOGGER.error(ex.getMessage());
      if(connection != null) {
        try {
          connection.rollback();
          LOGGER.info("Tuvimos un problema");
          LOGGER.error(ex.getMessage());
          ex.printStackTrace();
        } catch (SQLException sqlException) {
          LOGGER.error(sqlException.getMessage());
          sqlException.printStackTrace();
        }
      }
    } finally {
      try {
        connection.close();
      } catch (Exception ex) {
        LOGGER.error("No se pudo cerrar la conexion: " + ex.getMessage());
      }
    }
    return odontologoEliminado;
  }

  @Override
  public List<Odontologo> listar() {
    List<Odontologo> listaOdontologos = new ArrayList<>();
    Connection connection = null;
    try {
      connection = H2Connection.getConnection();
      PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM ODONTOLOGOS");
      ResultSet resultSet = preparedStatement.executeQuery();
      while (resultSet.next()) {
        Odontologo odontologo = new Odontologo(resultSet.getLong("id"), resultSet.getString("matricula"),
                resultSet.getString("nombre"), resultSet.getString("apellido"));
        listaOdontologos.add(odontologo);
        LOGGER.info(odontologo);
      }

    } catch (Exception e) {
      LOGGER.error(e.getMessage());
      e.printStackTrace();
    } finally {
      try {
        assert connection != null;
        connection.close();
      } catch (Exception ex) {
        LOGGER.error("Ha ocurrido un error al intentar cerrar la bdd. " + ex.getMessage());
        ex.printStackTrace();
      }
    }
    return listaOdontologos;
  }
}
