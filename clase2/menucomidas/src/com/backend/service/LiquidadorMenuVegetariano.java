package com.backend.service;

import com.backend.modelo.Menu;
import com.backend.modelo.MenuVegetariano;

public class LiquidadorMenuVegetariano extends LiquidadorMenu{

  @Override
  protected String armarMenu(Menu menu) {
    if(menu instanceof MenuVegetariano) return "Menú Vegetariano, con: "+((MenuVegetariano) menu).getCantEspecias()+ " Especias + " + ((MenuVegetariano) menu).getCantSalsas() + " Salsas";
    return "El menú no corresponde a Vegetariano";
  }

  public void calcularRecargo(Menu menu){
    ((MenuVegetariano) menu).setRecargo(((MenuVegetariano) menu).getCantEspecias()*0.01*menu.getPrecio()+((MenuVegetariano) menu).getCantSalsas()*2);
  }
  @Override
  public double calcularPrecio(Menu menu){
    calcularRecargo(menu);
    return menu.getPrecio()+menu.getRecargo();
  }
}
