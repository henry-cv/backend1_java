package com.backend.modelo;

public abstract class Menu {
  private double precio;
  private double recargo;

  public Menu(double precio) {
    this.precio = precio;
    this.recargo = 0;
  }

  public double getPrecio() {
    return precio;
  }

  public void setPrecio(double precio) {
    this.precio = precio;
  }

  public double getRecargo() {
    return recargo;
  }

  public void setRecargo(double recargo) {
    this.recargo = recargo;
  }
}
