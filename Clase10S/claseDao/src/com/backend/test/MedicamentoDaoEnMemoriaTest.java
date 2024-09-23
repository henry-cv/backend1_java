package com.backend.test;

import com.backend.entity.Medicamento;
import com.backend.repository.impl.MedicamentoDaoEnMemoria;
import com.backend.service.impl.MedicamentoService;
import org.junit.BeforeClass;
import org.junit.jupiter.api.Test;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MedicamentoDaoEnMemoriaTest {

    @BeforeClass
    public static void iniciar(){
        MedicamentoService ms = new MedicamentoService(new MedicamentoDaoEnMemoria());
        Medicamento med = new Medicamento(123,"paracetamol","phisher",10, 1.0 );
        ms.registrarMedicamento(med);
        Medicamento med2 = new Medicamento(322,"aspirina","bayer",12,1.5);
        ms.registrarMedicamento(med2);
    }
    @Test
    public void deberiaRegistrarUnMedicamentoEnMemoriaYRetornarSuId(){
        MedicamentoService medicamentoService = new MedicamentoService(new MedicamentoDaoEnMemoria());
        Medicamento medicamentoARegistrar = new Medicamento(4560, "Paracetamol", "Phisher", 50, 1.5);
        Medicamento medicamentoPersistido = medicamentoService.registrarMedicamento(medicamentoARegistrar);
        assertNotNull(medicamentoPersistido.getId());
        assertNotEquals(medicamentoARegistrar.getId(),medicamentoPersistido.getId());
    }
    @Test
    public void dadoUnMedicamentoConCantidadCeroDarError(){
        MedicamentoService ms = new MedicamentoService(new MedicamentoDaoEnMemoria());
        Medicamento medx = new Medicamento(321,"sertal", "dh",0,50);
        Medicamento medMemoria = ms.registrarMedicamento(medx);
        assertEquals(0,medx.getCantidad());
        assertNull(medx.getId());
    }
    @Test
    public void siExisteAlMenosUnMedicmentoEnMemoriaSeDebeListar(){
        MedicamentoService medSer = new MedicamentoService(new MedicamentoDaoEnMemoria());
        ArrayList<Medicamento> medList = (ArrayList<Medicamento>) medSer.listarMedicamentos();
        assertTrue(medList.size() >= 0);
    }
}