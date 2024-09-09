package com.backend.service.impl;

import com.backend.dto.entrada.DomicilioEntradaDto;
import com.backend.dto.entrada.OdontologoEntradaDto;
import com.backend.dto.entrada.OdontologoEntradaDto;
import com.backend.dto.entrada.PacienteEntradaDto;
import com.backend.dto.salida.OdontologoSalidaDto;
import com.backend.dto.salida.PacienteSalidaDto;
import com.backend.entity.Domicilio;
import com.backend.entity.Odontologo;
import com.backend.entity.Paciente;
import com.backend.exceptions.ResourceNotFoundException;
import com.backend.repository.OdontologoRepository;
import com.backend.repository.PacienteRepository;
import com.backend.service.IOdontologoService;
import org.junit.jupiter.api.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class OdontologoServiceTest {
  private final OdontologoRepository odontologoRepositoryMock =
    mock(OdontologoRepository.class);
  private final ModelMapper modelMapper = new ModelMapper();

  private final OdontologoService odontologoService =
    new OdontologoService(odontologoRepositoryMock, modelMapper);

  private static OdontologoEntradaDto odontologoEntradaDto;
  private static Odontologo odontologo;

  @BeforeAll
  static void setUp() {

    // Inicializa el objeto común antes de cada prueba
    odontologo = new Odontologo(1L, "mat-1001", "Anabel","Mendez");

    odontologoEntradaDto = new OdontologoEntradaDto("mat-1001", "Anabel",
      "Mendez");

  }
  @Test
  void deberiaMandarAlRepositorioUnOdontologoDeNombreAnabel_yRetornarUnSalidaDtoConSuId() {
    when(odontologoRepositoryMock.save(any(Odontologo.class))).thenReturn(odontologo);

    OdontologoSalidaDto odontologoSalidaDto =
      odontologoService.registrarOdontologo(odontologoEntradaDto);

    assertNotNull(odontologoSalidaDto);
    assertNotNull(odontologoSalidaDto.getId());
    assertEquals("Anabel", odontologoSalidaDto.getNombre());
    verify(odontologoRepositoryMock, times(1)).save(any(Odontologo.class));
  }
  @Test
  void deberiaDevolverUnaListaNoVaciaDeOdontologos() {
    List<Odontologo> odontologos =
      new java.util.ArrayList<>(List.of(odontologo));
    when(odontologoRepositoryMock.findAll()).thenReturn(odontologos);

    List<OdontologoSalidaDto> listadoDeOdontologos =
      odontologoService.listarOdontologos();
    assertFalse(listadoDeOdontologos.isEmpty());
  }

}
