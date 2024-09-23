package com.backend.repository.impl;

import com.backend.dbconnection.H2Connection;
import com.backend.entity.Avion;
import com.backend.repository.IDao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

public class AvionDaoH2 implements IDao<Avion> {
  private final Logger LOGGER = Logger.getLogger(AvionDaoH2.class);

  @Override
  public Avion registrar(Avion avion) {
    LOGGER.info("Avion a registrar: " + avion);
    Avion avionRegistrado = null;
    Connection connection = null;

    try {
      connection = H2Connection.getConnection();
      connection.setAutoCommit(false);

      String insert = "INSERT INTO aviones(marca, modelo, matricula, fechaEntrada) VALUES(?, ?, ?, ?)";
      PreparedStatement preparedStatement = connection.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
      preparedStatement.setString(1, avion.getMarca());
      preparedStatement.setString(2, avion.getModelo());
      preparedStatement.setString(3, avion.getMatricula());
      preparedStatement.setString(4, avion.getFechaEntrada());
      preparedStatement.execute();

      ResultSet result = preparedStatement.getGeneratedKeys();
      while (result.next()) {
        avionRegistrado = new Avion(result.getLong("id"), avion.getMarca(), avion.getModelo(), avion.getMatricula(), avion.getFechaEntrada());
      }
      LOGGER.info("Avion registrado: " + avionRegistrado);
      connection.commit();

    } catch (Exception exception) {
      LOGGER.error(exception.getMessage());
      exception.printStackTrace();
      if (connection != null) {
        try {
          connection.rollback();
          LOGGER.error("Tuvimos un problema: " + exception.getMessage());
          exception.printStackTrace();
        } catch (SQLException sqlException) {
          LOGGER.error(exception.getMessage());
          sqlException.printStackTrace();
        }
      }
    } finally {
      try {
        connection.close();
      } catch (Exception exception) {
        LOGGER.error("No se pudo cerrar la conexión: " + exception.getMessage());
      }
    }
    return avionRegistrado;
  }

  @Override
  public Avion buscar(Long id) {
    LOGGER.info("Avion a buscar con id: " + id);
    Avion avionEncontrado = null;
    Connection connection = null;

    try {
      connection = H2Connection.getConnection();

      String sqlBusquedaId = "SELECT * FROM aviones where id = ?";
      PreparedStatement preparedStatement = connection.prepareStatement(sqlBusquedaId);
      preparedStatement.setLong(1, id);

      ResultSet resultId = preparedStatement.executeQuery();
      while (resultId.next()) {
        avionEncontrado = new Avion(resultId.getLong("id"), resultId.getString("marca"), resultId.getString("modelo"), resultId.getString("matricula"), resultId.getString("fechaEntrada"));
      }

    } catch (Exception exception) {
      LOGGER.error(exception.getMessage());
      exception.printStackTrace();
    } finally {
      try {
        connection.close();
      } catch (Exception ex) {
        LOGGER.error("Ha ocurrido un error al intentar cerrar la bdd. " + ex.getMessage());
        ex.printStackTrace();
      }
    }
    LOGGER.info("Avion encontrado: " + avionEncontrado);
    return avionEncontrado;
  }

  @Override
  public Avion eliminar(Long id) {
    LOGGER.info("Se envió a eliminar Avión con el ID: "+id);
    Avion avionEncontrado = buscar(id);
    Avion avionEliminado = null;
    if(avionEncontrado ==null || avionEncontrado.getId() == null){
      LOGGER.error("No se encontró Avión con el ID: "+id);
      throw new UnsupportedOperationException("NO existe avión con ese id: "+id);
    }
    Connection connection = null;
    try {
      connection = H2Connection.getConnection();
      connection.setAutoCommit(false);
      String deleteById="DELETE FROM aviones WHERE id = ?";
      PreparedStatement preparedStatement = connection.prepareStatement(deleteById, Statement.RETURN_GENERATED_KEYS);
      preparedStatement.setLong(1,id);
      preparedStatement.executeUpdate();
      //preparedStatement.close();
      ResultSet resultEliminado = preparedStatement.getGeneratedKeys();
      while (resultEliminado.next()){
        avionEliminado = new Avion(resultEliminado.getLong("id"), resultEliminado.getString("marca"),
                resultEliminado.getString("modelo"),resultEliminado.getString("matricula"),resultEliminado.getString(
                        "fechaEntrada"));
        LOGGER.info("EN EL WHILE.NEXT avion Eliminado: "+avionEliminado);
      }
      LOGGER.info("Avión Eliminado: "+avionEliminado);
      connection.commit();
    }catch (Exception exception){
      LOGGER.error(exception.getMessage());
      exception.printStackTrace();
      if(connection != null){
        try{
          connection.rollback();
          LOGGER.error("Tuvimos un problema. " + exception.getMessage());
          exception.printStackTrace();
        } catch (SQLException sqlException){
          LOGGER.error(exception.getMessage());
          exception.printStackTrace();
        }
      }
    }
    return avionEliminado;
  }

  @Override
  public List<Avion> listarTodos() {
    LOGGER.info("Se envio a listar todos los Aviones");
    ArrayList<Avion> aviones = new ArrayList<Avion>();
    Connection connection = null;

    try {
      connection = H2Connection.getConnection();
      PreparedStatement preparedStatement = connection.prepareStatement("Select * from aviones");
      ResultSet listado = preparedStatement.executeQuery();
      while (listado.next()) {
        Avion avionX = new Avion(listado.getLong("id"), listado.getString("marca"), listado.getString("modelo"),
                listado.getString("matricula"), listado.getString("fechaEntrada"));
        LOGGER.info("Avión de la lista: \n "+avionX);
        aviones.add(avionX);
      }
    } catch (Exception exception) {
      LOGGER.error(exception.getMessage());
      exception.printStackTrace();
    }finally{
      try {
        connection.close();
      }catch (Exception ex){
        LOGGER.error("Ha ocurrido un error al cerra la conexion a la BD: "+ex.getMessage());
        ex.printStackTrace();
      }
    }
    //LOGGER.info("Listado de todos los Aviones:\n"+aviones);
    return aviones;
  }
}
