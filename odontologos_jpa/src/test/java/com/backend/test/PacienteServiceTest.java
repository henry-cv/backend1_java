package com.backend.test;


import com.backend.dto.entrada.DomicilioEntradaDto;
import com.backend.dto.entrada.PacienteEntradaDto;
import com.backend.dto.salida.OdontologoSalidaDto;
import com.backend.dto.salida.PacienteSalidaDto;
import com.backend.entity.Domicilio;
import com.backend.service.IPacienteService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class PacienteServiceTest {

    @Autowired
    IPacienteService pacienteService ;


    @Test
    void SeDeberegistrarPacienteSiLosDatosSonCorrectos() {

       DomicilioEntradaDto nuevoDomicilio = new DomicilioEntradaDto("Av juarez",54, "Paez","Lomas");
        LocalDate fechaIngreso = LocalDate.of(2024, 9, 4);

        PacienteEntradaDto nuevoPaciente = new PacienteEntradaDto("Luisa","Perez",35464856,fechaIngreso, nuevoDomicilio);
        PacienteSalidaDto pacienteRegistrado = pacienteService.registrarPaciente(nuevoPaciente);
        System.out.println("SALIDA SYSTEM OUT: "+ nuevoPaciente);
         assertNotNull(pacienteRegistrado.getId());
    }


    @Test
    void dadoUnIdExistenteBuscarPacientePorId() {
        Long id = Long.valueOf(1);
        PacienteSalidaDto pacienteEncontrado = pacienteService.buscarPacientePorId(id);
        assertNotNull(pacienteEncontrado.getId());
    }

    @Test
    void listarPacientes() {
        List<PacienteSalidaDto> listadoPacientes = pacienteService.listarPacientes();
        assertNotEquals(0, listadoPacientes.size());

    }

    @Test
    void eliminarPacienteExistenteUsandosSuId() {
        Long id = Long.valueOf(1);
        PacienteSalidaDto pacienteEncontrado = pacienteService.buscarPacientePorId(id);
        System.err.println("Paciente Borrado: " + pacienteEncontrado);
        pacienteService.eliminarPaciente(id);
        assertNotNull(pacienteEncontrado);


    }

    @Test
    void actualizarPaciente() {
        Long id = Long.valueOf(4);
        DomicilioEntradaDto nuevoDomicilio = new DomicilioEntradaDto("Romero",85, "Lomas","Peru");
        LocalDate fechaIngreso = LocalDate.of(2024, 9, 4);

        PacienteSalidaDto pacienteEncontrado = pacienteService.buscarPacientePorId(id);
        PacienteEntradaDto pacienteActualizado = new PacienteEntradaDto("Carla","Florez",12345,fechaIngreso,nuevoDomicilio);
        PacienteSalidaDto pacienteSalidaDto = pacienteService.actualizarPaciente(pacienteActualizado,id);
        assertNotNull(pacienteSalidaDto);

    }
}