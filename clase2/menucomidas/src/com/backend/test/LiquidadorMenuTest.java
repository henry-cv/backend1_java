package com.backend.test;

import com.backend.modelo.Menu;
import com.backend.modelo.MenuClasico;
import com.backend.modelo.MenuInfantil;
import com.backend.modelo.MenuVegetariano;
import com.backend.service.LiquidadorMenu;
import com.backend.service.LiquidadorMenuClasico;
import com.backend.service.LiquidadorMenuInfantil;
import com.backend.service.LiquidadorMenuVegetariano;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class LiquidadorMenuTest {
  private LiquidadorMenu liquidadorMenu;

  @Test
  public void pedidoUnMenuClasico_seDebeCalcularYMostrarCosto(){
    //arrange
    liquidadorMenu = new LiquidadorMenuClasico();
    Menu menuClasico = new MenuClasico(5.65);
    String respuestaEsperada = "Menú clásico, costo: $ 5.65";
    System.out.println("Esperada: " + respuestaEsperada);
    //act
    String respuestaObtenida = liquidadorMenu.prepararMenu(menuClasico);
    System.out.println("Obtenida: " + respuestaObtenida);
    //assert
    assertEquals(respuestaEsperada, respuestaObtenida);
  }

  @Test
  public void pedidoMenuInfantilConDosJuguetesMostrarCosto(){
    //arrange
    liquidadorMenu = new LiquidadorMenuInfantil();
    Menu menuInfantil = new MenuInfantil(6.25, 2);
    String respuestaEsperada = "Menú Infantil + 2 juguetes, costo: $ 12.25";
    System.out.println("Esperada: " + respuestaEsperada);

    //act
    String respuestaObtenida = liquidadorMenu.prepararMenu(menuInfantil);
    System.out.println("Obtenida: " + respuestaObtenida);

    //assert
    assertEquals(respuestaEsperada, respuestaObtenida);
  }

  @Test
  public void pedidoMenuInfantilConCuatroJuguetesMostrarCosto(){
    //arrange
    liquidadorMenu = new LiquidadorMenuInfantil();
    Menu menuInfantil = new MenuInfantil(6.25, 4);
    String respuestaEsperada = "Menú Infantil + "+ ((MenuInfantil) menuInfantil).getCantJuguetes()+" juguetes, costo: $ "+liquidadorMenu.calcularPrecio(menuInfantil);
    System.out.println("Esperada: " + respuestaEsperada);

    //act
    String respuestaObtenida = liquidadorMenu.prepararMenu(menuInfantil);
    System.out.println("Obtenida: " + respuestaObtenida);

    //assert
    assertEquals(respuestaEsperada, respuestaObtenida);
  }

  //Menu vegetariano
  @Test
  public void pedidoMenuVegetarianoDosEspeciasYTresSalsasMostrarCosto(){
    //arrange
    liquidadorMenu = new LiquidadorMenuVegetariano();
    Menu menuVegetariano = new MenuVegetariano(7.00,2, 3);
    String respuestaEsperada = "Menú Vegetariano, con: "+ ((MenuVegetariano) menuVegetariano).getCantEspecias() + " Especias + "+((MenuVegetariano) menuVegetariano).getCantSalsas() + " Salsas, costo: $ "+liquidadorMenu.calcularPrecio(menuVegetariano);
    System.out.println("Esperada: " + respuestaEsperada);

    //act
    String respuestaObtenida = liquidadorMenu.prepararMenu(menuVegetariano);
    System.out.println("Obtenida: " + respuestaObtenida);

    //assert
    assertEquals(respuestaEsperada, respuestaObtenida);
  }
}