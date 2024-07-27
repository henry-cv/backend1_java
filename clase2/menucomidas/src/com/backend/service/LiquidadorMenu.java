package com.backend.service;

import com.backend.modelo.Menu;
import com.backend.modelo.MenuClasico;
import com.backend.modelo.MenuInfantil;
import com.backend.modelo.MenuVegetariano;


public abstract class LiquidadorMenu {

  //Método Template
  public String prepararMenu(Menu menu ){
    String pedido = armarMenu(menu);
    double precio = calcularPrecio(menu);
    return pedido + ", costo: $ " + precio;
  }
  protected abstract String armarMenu(Menu menu);

  public double calcularPrecio(Menu menu){
    //if(menu instanceof MenuClasico mc) mc.setRecargo(0.0);
    //Patrones de instanceof no son reconocidos en JDK 11
    return menu.getPrecio() + menu.getRecargo();
  }
}
/*
Tareas o responsabilidades a resolver
- Armar el menú
- Calcular el precio
- Preparar el menú
* */