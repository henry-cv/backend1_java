package com.backend.modelo;

public class MenuInfantil extends Menu{
  private int cantJuguetes;

  public MenuInfantil(double precio, int cantJuguetes) {
    super(precio);
    this.cantJuguetes = cantJuguetes;
  }

  public int getCantJuguetes() {
    return cantJuguetes;
  }

  public void setCantJuguetes(int cantJuguetes) {
    this.cantJuguetes = cantJuguetes;
  }
}
