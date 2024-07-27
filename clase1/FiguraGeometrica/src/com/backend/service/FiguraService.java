package com.backend.service;

import com.backend.modelo.Circulo;
import com.backend.modelo.Cuadrado;

public class FiguraService {
  public final static String CIRCULO="CIRCULO";
  public final static String CUADRADO="CUADRADO";
  private Circulo circulo;
  private Cuadrado cuadrado;
  public static double area=0.0;

  public double getArea() {
    return area;
  }

  public static Circulo crearCirculo(double radio){
    return new Circulo(radio);
  }
  public static Cuadrado crearCuadrado(double lado){
    return new Cuadrado(lado);
  }
  private double calcularAreaCirculo(double radio){
    circulo = crearCirculo(radio);
    return Math.PI * Math.pow(circulo.getRadio(),2);
  }
  private double calcularAreaCuadrado(double lado){
    cuadrado=crearCuadrado(lado);
    return Math.pow(cuadrado.getLado(),2);
  }
  public String calcularAreaFigura(String tipo, double valor){
    if(valor <=0 ) return "El valor del radio o lado debe ser mayor que cero";
    switch (tipo){
      case CIRCULO:
        area = calcularAreaCirculo(valor);
        return "El área del "+ CIRCULO + " es " +area + " unidades";
      case CUADRADO:
        area = calcularAreaCuadrado(valor);
        return "El área del "+ CUADRADO + " es " +area + " unidades";
      default:
        return "Las Figuras pueden ser Circulo o Cuadrado";
    }
  }

}
//