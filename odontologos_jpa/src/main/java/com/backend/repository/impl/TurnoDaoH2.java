package com.backend.repository.impl;

import com.backend.dbconnection.H2Connection;
import com.backend.entity.Odontologo;
import com.backend.entity.Paciente;
import com.backend.entity.Turno;
import com.backend.repository.IDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Repository
public class TurnoDaoH2 implements IDao<Turno> {

  private final Logger LOGGER = LogManager.getLogger(TurnoDaoH2.class);
  private TurnoDaoH2 turnoDaoH2;

  @Override
  public Turno registrar(Turno turno) {
    return null;
  }

  @Override
  public Turno buscar(Long id) {
    return null;
  }

  @Override
  public Turno eliminar(Long id) {
    return null;
  }

  @Override
  public List<Turno> listar() {
    List<Turno> listaTurnos = new ArrayList<>();
    Connection connection = null;
    Turno turno = null;
    try {
      connection = H2Connection.getConnection();
      PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM TURNOS");
      ResultSet resultSet = preparedStatement.executeQuery();
      while (resultSet.next()) {
        turno = crearObjetoTurno(resultSet);
        listaTurnos.add(turno);
        LOGGER.info(turno);
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
    return listaTurnos;
  }

  private Turno crearObjetoTurno(ResultSet resultSet) throws SQLException {
    Paciente paciente = new PacienteDaoH2().buscar(resultSet.getLong("PACIENTE_ID"));
    LOGGER.info("Paciente localizado con resulSet: " + paciente);
    Odontologo odontologo = new OdontologoDaoH2().buscar(resultSet.getLong("ODONTOLOGO_ID"));
    LOGGER.info("Odontologo localizado con resulSet: " + paciente);
    LocalDateTime fecha = resultSet.getObject("fechahora", LocalDateTime.class);
    LOGGER.info("Fecha obtenida con resulSet: " + paciente);
    return new Turno(resultSet.getLong("id"), paciente, odontologo, fecha);
  }
}
