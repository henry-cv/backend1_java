/*
package com.backend.service.impl;

import com.backend.dto.entrada.TurnoEntradaDto;
import com.backend.dto.salida.DomicilioSalidaDto;
import com.backend.dto.salida.OdontologoSalidaDto;
import com.backend.dto.salida.PacienteSalidaDto;
import com.backend.dto.salida.TurnoSalidaDto;
import com.backend.entity.Odontologo;
import com.backend.entity.Paciente;
import com.backend.entity.Turno;
import com.backend.exceptions.BadRequestException;
import com.backend.exceptions.ResourceNotFoundException;
import com.backend.repository.TurnoRepository;
import org.junit.jupiter.api.*;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TurnoServiceTest {

  private final TurnoRepository turnoRepositoryMock = mock(TurnoRepository.class);
  private final ModelMapper modelMapper = mock(ModelMapper.class);
  private final OdontologoService odontologoServiceMock = mock(OdontologoService.class);
  private final PacienteService pacienteServiceMock = mock(PacienteService.class);

  private final TurnoService turnoService = new TurnoService(turnoRepositoryMock, modelMapper, odontologoServiceMock, pacienteServiceMock);

  private static LocalDateTime fechaTurno;
  private static Turno turnoExistente;
  private static TurnoEntradaDto turnoEntradaDto;
  private static TurnoSalidaDto turnoSalidaDto;
  private static Paciente paciente;
  private static Odontologo odontologo;
  private static PacienteSalidaDto pacienteSalidaDto;
  private static OdontologoSalidaDto odontologoSalidaDto;
  private static DomicilioSalidaDto domicilioSalidaDto;

  @BeforeAll
  static void setUp() {
    domicilioSalidaDto = new DomicilioSalidaDto(1L, "Calle", 123, "Localidad", "Provincia");
    paciente = new Paciente(1L, "Juan", "Perez", 123456, LocalDate.of(2024, 6, 22), null);
    odontologo = new Odontologo(1L, "123456", "Dr. Smith", "Ortodoncia");
    fechaTurno = LocalDateTime.now().plusDays(1);
    turnoExistente = new Turno(1L, paciente, odontologo, fechaTurno);

    turnoEntradaDto = new TurnoEntradaDto(1L,1L,fechaTurno);
    pacienteSalidaDto = new PacienteSalidaDto(1L, "Juan", "Perez", 123456, LocalDate.of(2024, 6, 22), domicilioSalidaDto);
    odontologoSalidaDto = new OdontologoSalidaDto(1L, "123456", "Dr. Smith", "Ortodoncia");
    turnoSalidaDto = new TurnoSalidaDto(1L, pacienteSalidaDto, odontologoSalidaDto, LocalDateTime.of(2024, 9, 12, 10, 0));
  }


  @Test
  void deberiaMandarAlRepositorioUnTurno_yRetornarUnSalidaDtoConSuId() throws BadRequestException {
    when(turnoRepositoryMock.save(any(Turno.class))).thenReturn(turnoExistente);
    when(modelMapper.map(any(Turno.class), eq(TurnoSalidaDto.class))).thenReturn(turnoSalidaDto);

    TurnoSalidaDto resultado = turnoService.registrarTurno(turnoEntradaDto);

    assertNotNull(resultado);
    assertNotNull(resultado.getId());
    assertEquals(turnoSalidaDto.getId(), resultado.getId());
    verify(turnoRepositoryMock, times(1)).save(any(Turno.class));
  }

  @Test
  void deberiaDevolverUnaListaNoVaciaDeTurnos() {
    List<Turno> turnos = new java.util.ArrayList<>(List.of(turnoExistente));
    when(turnoRepositoryMock.findAll()).thenReturn(turnos);
    when(modelMapper.map(any(Turno.class), eq(TurnoSalidaDto.class))).thenReturn(turnoSalidaDto);

    List<TurnoSalidaDto> listadoDeTurnos = turnoService.listarTurnos();
    assertFalse(listadoDeTurnos.isEmpty());
  }

  @Test
  void dadoElIdUnoDebeBuscarEnRepositorioYRetornarElTurnoConEseId() {
    Long id = 1L;
    when(turnoRepositoryMock.findById(id)).thenReturn(Optional.of(turnoExistente));
    when(modelMapper.map(any(Turno.class), eq(TurnoSalidaDto.class))).thenReturn(turnoSalidaDto);

    TurnoSalidaDto resultado = turnoService.buscarTurnoPorId(id);
    assertNotNull(resultado);
    assertNotNull(resultado.getId());
    assertEquals(turnoSalidaDto.getId(), resultado.getId());
  }

  @Test
  void dadoElIdUnoDebeLanzarExcepcionSiNoEncuentraElTurno() {
    Long id = 1L;
    when(turnoRepositoryMock.findById(id)).thenReturn(Optional.empty());

    assertThrows(ResourceNotFoundException.class, () -> turnoService.buscarTurnoPorId(id));
  }
}
*/