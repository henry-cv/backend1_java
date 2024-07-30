package com.backend.service;

import com.backend.modelo.Arbol;

import java.util.HashMap;
import java.util.Objects;

public class ArbolFactory {

  public static final ArbolFactory instance = new ArbolFactory();
  private static final HashMap<String, Arbol> ARBOL_MAP = new HashMap();
  public static final String ORNAMENTAL = "ORNAMENTAL";
  public static final String FRUTAL = "FRUTAL";
  public static final String FLORAL = "FLORAL";
  public static String OTRO = "OTRO";

  private ArbolFactory() {}

  public static ArbolFactory getInstance() {
    return instance;
  }

  public Arbol crearArbol(String tipoArbol) {
    Arbol arbol = new Arbol(200, 100, "roble", tipoArbol);
    switch (tipoArbol) {
      case ORNAMENTAL:
        return crearArbolOrnamental();
      case FLORAL:
        return crearArbolFloral();
      case FRUTAL:
        return crearArbolFrutal();
      default:
        return arbol;
    }
  }

  public Arbol obtenerArbol(final String tipoArbol) {
    Arbol arbol = ARBOL_MAP.get(tipoArbol);
    if (Objects.isNull(arbol)) {
      arbol = crearArbol(tipoArbol);
      ARBOL_MAP.put(tipoArbol, arbol);
      System.out.println("Creando árbol de tipo: " + tipoArbol);
      return arbol;
    }
    System.out.println("Recuperando árbol de tipo: " + tipoArbol);
    return arbol;
  }

  private Arbol crearArbolOrnamental() {
    return new Arbol(200, 400, "verde", "Ornamental");
  }

  private Arbol crearArbolFrutal() {
    return new Arbol(500, 300, "rojo", "Frutal");
  }

  private Arbol crearArbolFloral() {
    return new Arbol(100, 200, "celeste", "Frutal");
  }
}
