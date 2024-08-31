package com.backend.odontologos_sp1;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OdontologosSp1Application {
  private static final Logger LOGGER = LoggerFactory.getLogger(OdontologosSp1Application.class);

  public static void main(String[] args) {
    SpringApplication.run(OdontologosSp1Application.class, args);
    LOGGER.info(" Odontólogo Application FUNCIONANDO ------->");
  }

}
