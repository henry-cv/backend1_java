package com.backend.test;

import com.backend.service.FiguraService;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class FiguraServiceTest {
  private final FiguraService FIGURASERVICE = new FiguraService();
  String resultado="";

  //TEST PARA EL CUADRADO
  @Test
  public void dadoUnLadoNegativoMuestreMensaje_AlCalcularAreaCuadrado(){
    resultado = FIGURASERVICE.calcularAreaFigura(FIGURASERVICE.CUADRADO,-3);
    assertEquals("El valor del radio o lado debe ser mayor que cero", resultado);
  }
  @Test
  public void dadoLadoPositivo_CalculeAreaCuadrado(){
    resultado = FIGURASERVICE.calcularAreaFigura(FiguraService.CUADRADO,5);
    assertEquals("El área del "+ FiguraService.CUADRADO + " es " +FIGURASERVICE.getArea() + " unidades",resultado);
  }

  //TEST PARA EL CIRCULO
  @Test
  public void dadoUnRadioNegativoMuestreMensaje_AlCalcularAreaCirculo(){
    resultado = FIGURASERVICE.calcularAreaFigura(FIGURASERVICE.CIRCULO,-3);
    assertEquals("El valor del radio o lado debe ser mayor que cero", resultado);
  }
  @Test
  public void dadoRadioPositivo_CalculeAreaCirculo(){
    resultado = FIGURASERVICE.calcularAreaFigura(FiguraService.CIRCULO,5);
    assertEquals("El área del "+ FIGURASERVICE.CIRCULO + " es " +FiguraService.area + " unidades",resultado);
    System.out.println(FiguraService.area);
    System.out.println(FiguraService.CIRCULO);
  }

  //TEST PARA FIGURA NO SOPORTADA
  @Test
  public void dadoNombreFiguraNoSoportada_CalculeAreaFigura(){
    resultado = FIGURASERVICE.calcularAreaFigura("ROMBO",5);
    assertEquals("FIGURA NO SOPORTADA", resultado);
  }
}