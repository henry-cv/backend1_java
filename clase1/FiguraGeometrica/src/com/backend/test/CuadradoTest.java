package com.backend.test;

import com.backend.modelo.Cuadrado;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CuadradoTest {
  @Test
  public void getLado(){
    Cuadrado cuadrado= new Cuadrado(5.66);
    assertEquals(5.66, cuadrado.getLado());
  }
}