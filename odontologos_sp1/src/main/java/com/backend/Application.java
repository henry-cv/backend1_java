package com.backend;

import com.backend.dbconnection.H2Connection;
import com.backend.odontologos_sp1.OdontologosSp1Application;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {
  private static final Logger LOGGER = LoggerFactory.getLogger(OdontologosSp1Application.class);

  public static void main(String[] args) {
      H2Connection.inicializarScript();
      SpringApplication.run(Application.class, args);
  }
}