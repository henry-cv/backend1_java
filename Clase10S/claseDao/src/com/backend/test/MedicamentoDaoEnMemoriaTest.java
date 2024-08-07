package com.backend.test;

import com.backend.entity.Medicamento;
import com.backend.repository.impl.MedicamentoDaoEnMemoria;
import com.backend.service.impl.MedicamentoService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MedicamentoDaoEnMemoriaTest {
    @Test
    void deberiaRegistrarUnMedicamentoEnMemoriaYRetornarSuId(){

        MedicamentoService medicamentoService = new MedicamentoService(new MedicamentoDaoEnMemoria());
        Medicamento medicamentoARegistrar = new Medicamento(4560, "Paracetamol", "Phiser", 50, 1.5);

        Medicamento medicamentoPersistido = medicamentoService.registrarMedicamento(medicamentoARegistrar);

        assertNotNull(medicamentoPersistido.getId());
    }
    @Test
    void dadoUnMedicamentoConCantidadCeroDarError(){
        MedicamentoService ms = new MedicamentoService(new MedicamentoDaoEnMemoria());
        Medicamento medx = new Medicamento(321,"sertal", "dh",0,50);
        Medicamento medMemoria = ms.registrarMedicamento(medx);
        assertNull(medx);
    }

}