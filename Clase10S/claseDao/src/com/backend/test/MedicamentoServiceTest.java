package com.backend.test;

import com.backend.entity.Medicamento;
import com.backend.repository.impl.MedicamentoDaoEnMemoria;
import com.backend.repository.impl.MedicamentoDaoH2;
import com.backend.service.impl.MedicamentoService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MedicamentoServiceTest {
    private MedicamentoService medicamentoService;

    @Test
    void deberiaRegistrarUnMedicamentoEnH2YRetornarSuId(){
        medicamentoService = new MedicamentoService(new MedicamentoDaoH2());
        Medicamento medicamentoARegistrar = new Medicamento(1236, "Aspirina", "Bayer", 50, 5000.);

        Medicamento medicamentoPersistido = medicamentoService.registrarMedicamento(medicamentoARegistrar);

        assertNotNull(medicamentoPersistido.getId());

    }
     @Test
     void dadoUnMedicamentoConCantidadCeroDarError(){
         MedicamentoService ms = new MedicamentoService(new MedicamentoDaoH2());
         Medicamento medx = new Medicamento(321,"sertal", "dh",0,50);
         Medicamento medMemoria = ms.registrarMedicamento(medx);
         assertNull(medx.getId());
     }



}