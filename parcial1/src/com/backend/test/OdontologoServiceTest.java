package com.backend.test;

import com.backend.entity.Odontologo;
import com.backend.repository.impl.OdontologoDaoH2;
import com.backend.repository.impl.OdontologoDaoMemoria;
import com.backend.service.impl.OdontologoService;
import org.apache.log4j.Logger;
//import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OdontologoServiceTest {
    private static final org.apache.log4j.Logger LOGGER = Logger.getLogger(OdontologoServiceTest.class);

   @BeforeAll
    public static void cargarODontologosEnMemoria() {
        OdontologoService odontologoService = new OdontologoService(new OdontologoDaoMemoria());
        odontologoService.registrarOdontologo(new Odontologo("matricula-001", "Pepito", "Perez"));
        odontologoService.registrarOdontologo(new Odontologo("matricula-002", "Aquiles", "Brinco"));
        odontologoService.registrarOdontologo(new Odontologo("matricula-003", "Armando", "Casas"));
        odontologoService.registrarOdontologo(new Odontologo("matricula-004", "Betty", "Pinzón"));

    }
    @Test
    void seDebePoderListarTodosLosOdontologosAlmacenadosEnH2CuandoListaDistintaDeCero(){
        OdontologoService odontologoService = new OdontologoService(new OdontologoDaoH2());
        List<Odontologo> listadoOdontologos = odontologoService.listarOdontologos();
        assertNotEquals(0, listadoOdontologos.size());
    }
    @Test
    void seDebePoderListarTodosLosOdontologosAlmacenadosEnMemoriaYListaDistintaDeCero(){
        OdontologoService odontologoService = new OdontologoService(new OdontologoDaoMemoria());
        List<Odontologo> listadoOdontologos = odontologoService.listarOdontologos();
        assertNotEquals(0, listadoOdontologos.size());
    }
}