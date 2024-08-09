package com.backend.repository.impl;

import com.backend.entity.Odontologo;
import com.backend.repository.IDao;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class OdontologoDaoMemoria implements IDao<Odontologo> {
    private static final List <Odontologo> listaOdontologos = new ArrayList<>();
    private final Logger LOGGER = Logger.getLogger(OdontologoDaoMemoria.class);
    private static Long localId=1L;

    @Override
    public Odontologo registrar(Odontologo odontologo) {
        Odontologo odontologoX = new Odontologo(odontologo.getMatricula(), odontologo.getNombre(),odontologo.getApellido());
        odontologoX.setId(localId);
        listaOdontologos.add(odontologoX);
        localId++;
        LOGGER.info("Odontologo registrado: "+odontologoX);
        return odontologoX;
    }

    @Override
    public List<Odontologo> listar() {
        LOGGER.info("Listando Todos los Odontologos: "+listaOdontologos);
        return listaOdontologos;
    }
}
