package com.backend.modelo;

public class Arbol {
  private double alto;
  private double ancho;
  private String color;
  private String tipoArbol;

  public Arbol(double alto, double ancho, String color, String tipoArbol) {
    this.alto = alto;
    this.ancho = ancho;
    this.color = color;
    this.tipoArbol = tipoArbol;
  }

  public double getAlto() {
    return alto;
  }

  public void setAlto(double alto) {
    this.alto = alto;
  }

  public double getAncho() {
    return ancho;
  }

  public void setAncho(double ancho) {
    this.ancho = ancho;
  }

  public String getColor() {
    return color;
  }

  public void setColor(String color) {
    this.color = color;
  }

  public String getTipoArbol() {
    return tipoArbol;
  }

  public void setTipoArbol(String tipoArbol) {
    this.tipoArbol = tipoArbol;
  }

  public String mostrar() {
    return "Se creo un árbol de tipo: " + this.tipoArbol;
  }

  @Override
  public String toString() {
    return "Arbol{" +
            "alto=" + alto +
            ", ancho=" + ancho +
            ", color='" + color + '\'' +
            ", tipoArbol='" + tipoArbol + '\'' +
            '}';
  }
}
