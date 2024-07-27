package com.backend.service;

import com.backend.modelo.Menu;
import com.backend.modelo.MenuClasico;

public class LiquidadorMenuClasico extends LiquidadorMenu{

  @Override
  protected String armarMenu(Menu menu) {
    if(menu instanceof MenuClasico) return "Menú clásico";
    return "Este es Liquidador Menu Clásico";
  }
}
