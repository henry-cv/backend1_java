package com.backend.repository.impl;

import com.backend.entity.Medicamento;
import com.backend.repository.IDao;

import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;

public class MedicamentoDaoEnMemoria implements IDao<Medicamento> {
    private static Long id = 1L;
    private static final List<Medicamento> listaMedicamentosMemoria = new ArrayList<>();
    private static final Logger LOGGER = Logger.getLogger(MedicamentoDaoEnMemoria.class);

    @Override
    public Medicamento registrar(Medicamento medicamento) {
        medicamento.setId(id);
        listaMedicamentosMemoria.add(medicamento);
        id++;
        LOGGER.info("Medicamento registrado: "+medicamento);
        return medicamento;
    }

    @Override
    public List<Medicamento> listarTodos(){
        for (Medicamento med: listaMedicamentosMemoria){
            //System.out.println(med);
            LOGGER.info(med);
        }
        return listaMedicamentosMemoria;
    }

    public static void main(String[] args) {
        MedicamentoDaoEnMemoria daoEnMemoria = new MedicamentoDaoEnMemoria();
        Medicamento med = new Medicamento(123,"paracetamol","phisher",10, 1.0 );
        daoEnMemoria.registrar(med);
        Medicamento med2 = new Medicamento(322,"aspirina","bayer",12,1.5);
        daoEnMemoria.registrar(med2);
        daoEnMemoria.listarTodos();
    }
}
