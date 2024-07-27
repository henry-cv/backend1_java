package com.backend.service;

import com.backend.modelo.Menu;
import com.backend.modelo.MenuInfantil;
import com.backend.modelo.MenuVegetariano;

public class LiquidadorMenuInfantil extends LiquidadorMenu{

  @Override
  protected String armarMenu(Menu menu) {
    if(menu instanceof MenuInfantil) return "Menú Infantil + "+((MenuInfantil) menu).getCantJuguetes() +" juguetes";
    return "El Menú no corresponde a Infantil";
  }
  public void calcularRecargo(Menu menu){
    ((MenuInfantil) menu).setRecargo(((MenuInfantil) menu).getCantJuguetes()*3);
  }
  @Override
  public double calcularPrecio(Menu menu){
    calcularRecargo(menu);
    return menu.getPrecio()+menu.getRecargo();
  }
}
