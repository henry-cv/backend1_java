package com.backend.service;

import com.backend.modelo.Arbol;

import java.util.HashMap;
import java.util.Map;

public class ArbolFactory {
  private static Map<String, Arbol> arbolesMap = new HashMap<>();

  public static Arbol obtenerArbol(double alto, double ancho, String color, String tipoArbol) {
    String key = alto + ancho + color + tipoArbol;
    System.out.println(key);
    if (!arbolesMap.containsKey(key)) {
      Arbol arbol = new Arbol(alto, ancho, color, tipoArbol);
      arbolesMap.put(key, arbol);
      System.out.printf("arbol creado:" + arbol);
    } else {
      System.out.println("Arbol ya existente");
    }
    return arbolesMap.get(key);
  }
}
