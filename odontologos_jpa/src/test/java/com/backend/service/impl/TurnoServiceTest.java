package com.backend.service.impl;

import com.backend.dto.entrada.TurnoEntradaDto;
import com.backend.dto.salida.DomicilioSalidaDto;
import com.backend.dto.salida.OdontologoSalidaDto;
import com.backend.dto.salida.PacienteSalidaDto;
import com.backend.dto.salida.TurnoSalidaDto;
import com.backend.entity.Domicilio;
import com.backend.entity.Odontologo;
import com.backend.entity.Paciente;
import com.backend.entity.Turno;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

import com.backend.exceptions.BadRequestException;
import com.backend.exceptions.ResourceNotFoundException;
import com.backend.repository.OdontologoRepository;
import com.backend.repository.PacienteRepository;
import com.backend.repository.TurnoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class TurnoServiceTest {
  private final ModelMapper modelMapper = new ModelMapper();

  private final OdontologoRepository odontologoRepositoryMock =
    mock(OdontologoRepository.class);

  private final OdontologoService odontologoService =
    new OdontologoService(odontologoRepositoryMock, modelMapper);

  private final PacienteRepository pacienteRepositoryMock =
    mock(PacienteRepository.class);
  private final PacienteService pacienteService =
    new PacienteService(pacienteRepositoryMock, modelMapper);

  private final TurnoRepository turnoRepositoryMock= mock(TurnoRepository.class);
  private final TurnoService turnoService =
    new TurnoService(turnoRepositoryMock, modelMapper,odontologoService,
      pacienteService);

  private static final LocalDate fechaIngreso = LocalDate.now();
  private static final LocalDateTime fechaTurno =
    LocalDateTime.now().plusDays(1);
  private static Domicilio domicilio;
  private static DomicilioSalidaDto domicilioSalidaDto;
  private static Paciente paciente;
  private static PacienteSalidaDto pacienteSalidaDto;
  private static Odontologo odontologo;
  private static OdontologoSalidaDto odontologoSalidaDto;
  private static Turno turnoExistente;
  private static TurnoEntradaDto turnoEntradaDto;
  private static TurnoSalidaDto turnoSalidaDto;

  @BeforeAll
  public static void setup() {
    // Datos de prueba para Domicilio
    domicilio = new Domicilio(1L, "Calle Falsa", 123, "Springfield", "Ohio");
    domicilioSalidaDto = new DomicilioSalidaDto(domicilio.getId(),
      domicilio.getCalle(), domicilio.getNumero(), domicilio.getLocalidad(),
      domicilio.getProvincia());

    // Datos de prueba para Paciente
    paciente = new Paciente(1L, "Juan", "Perez", 12345678, fechaIngreso, domicilio);
    pacienteSalidaDto = new PacienteSalidaDto(paciente.getId(),
      paciente.getNombre(),paciente.getApellido(),paciente.getDni(),
      paciente.getFechaIngreso(),domicilioSalidaDto);

    // Datos de prueba para Odontologo
    odontologo = new Odontologo(1L, "mat-1001", "Anabel", "Mendez");

    // Datos de prueba para Turno
    turnoExistente = new Turno(1L, paciente, odontologo,fechaTurno);

    // DTOs de prueba
    turnoEntradaDto = new TurnoEntradaDto(1L, 1L,fechaTurno);
    turnoSalidaDto = new TurnoSalidaDto(1L, pacienteSalidaDto,odontologoSalidaDto,
      fechaTurno);
  }
  @Test
  public void testActualizarTurno_Exitoso() throws BadRequestException,
    ResourceNotFoundException {
    when(turnoRepositoryMock.findById(1L)).thenReturn(Optional.of(turnoExistente));
    when(pacienteRepositoryMock.findById(1L)).thenReturn(Optional.of(paciente));
    when(odontologoRepositoryMock.findById(1L)).thenReturn(Optional.of(odontologo));
    when(turnoRepositoryMock.save(any(Turno.class))).thenReturn(turnoExistente);
    when(modelMapper.map(any(Turno.class), eq(TurnoSalidaDto.class))).thenReturn(turnoSalidaDto);

    TurnoSalidaDto resultado = turnoService.actualizarTurno(turnoEntradaDto, 1L);

    assertNotNull(resultado);
    assertEquals(turnoSalidaDto.getId(), resultado.getId());
    assertEquals(turnoSalidaDto.getFechaHora(), resultado.getFechaHora());
    assertEquals(turnoSalidaDto.getPacienteSalidaDto().getId(),
      resultado.getPacienteSalidaDto().getId());
    assertEquals(turnoSalidaDto.getOdontologoSalidaDto().getId(),
      resultado.getOdontologoSalidaDto().getId());
  }
}
