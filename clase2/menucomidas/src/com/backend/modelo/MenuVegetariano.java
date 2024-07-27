package com.backend.modelo;

public class MenuVegetariano extends Menu{
  private int cantEspecias;
  private int cantSalsas;

  public MenuVegetariano(double precio, int cantEspecias, int cantSalsas) {
    super(precio);
    this.cantEspecias = cantEspecias;
    this.cantSalsas = cantSalsas;
  }

  public int getCantEspecias() {
    return cantEspecias;
  }

  public void setCantEspecias(int cantEspecias) {
    this.cantEspecias = cantEspecias;
  }

  public int getCantSalsas() {
    return cantSalsas;
  }

  public void setCantSalsas(int cantSalsas) {
    this.cantSalsas = cantSalsas;
  }
}
