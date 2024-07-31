package com.backend.service;

import com.backend.modelo.Arbol;

import java.util.ArrayList;
import java.util.List;

public class Bosque {
  private final List<Arbol> arboles = new ArrayList<Arbol>();
  public void plantarArbol(Arbol arbol){

  }
  public void mostrarArboles(){
    for (Arbol arbol: arboles){
      System.out.println(arbol);
    }
  }
}
