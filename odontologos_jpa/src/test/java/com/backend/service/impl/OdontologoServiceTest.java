package com.backend.service.impl;

import com.backend.dto.entrada.OdontologoEntradaDto;
import com.backend.dto.salida.OdontologoSalidaDto;
import com.backend.entity.Odontologo;
import com.backend.exceptions.ResourceNotFoundException;
import com.backend.repository.OdontologoRepository;
import org.junit.jupiter.api.*;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

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
  private static Odontologo odontologoExistente;

  @BeforeAll
  static void setUp() {
    // Inicializa el objeto común antes de cada prueba
    odontologoExistente = new Odontologo(1L, "mat-1001", "Anabel", "Mendez");
    odontologoEntradaDto = new OdontologoEntradaDto("mat-1001", "Anabel",
      "Mendez");
  }

  @Test
  void deberiaMandarAlRepositorioUnOdontologoDeNombreAnabel_yRetornarUnSalidaDtoConSuId() {
    when(odontologoRepositoryMock.save(any(Odontologo.class))).thenReturn(odontologoExistente);

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
      new java.util.ArrayList<>(List.of(odontologoExistente));
    when(odontologoRepositoryMock.findAll()).thenReturn(odontologos);

    List<OdontologoSalidaDto> listadoDeOdontologos =
      odontologoService.listarOdontologos();
    assertFalse(listadoDeOdontologos.isEmpty());
  }

  @Test
  void dadoElIdUnoDebeBuscarEnRepositorioYRetornarElOdontologoConEseId() {
    Long id = 1L;
    when(odontologoRepositoryMock.findById(id)).thenReturn(Optional.of(odontologoExistente));
    OdontologoSalidaDto odontologoSalidaDto =
      odontologoService.buscarOdontologoPorId(id);
    assertNotNull(odontologoSalidaDto);
    assertNotNull(odontologoSalidaDto.getId());
    assertEquals("Mendez", odontologoSalidaDto.getApellido());
  }

  @Test
  public void seDebePoderActualizarOdontologoExistente() throws ResourceNotFoundException {
    // Paso 1: Configurar el mock para devolver el odontólogo existente al
    // buscar por ID
    when(odontologoRepositoryMock.findById(1L)).thenReturn(Optional.of(odontologoExistente));

    // Paso 2: Configurar el odontólogo actualizado con los valores del DTO
    // de entrada
    odontologoEntradaDto.setMatricula("mat-2002");
    odontologoEntradaDto.setNombre("Ana");
    odontologoEntradaDto.setApellido("Mendez");

    Odontologo odontologoActualizado = new Odontologo();
    odontologoActualizado.setId(1L);
    odontologoActualizado.setMatricula(odontologoEntradaDto.getMatricula());
    // La
    // matrícula no cambia
    odontologoActualizado.setNombre(odontologoEntradaDto.getNombre());
    odontologoActualizado.setApellido(odontologoEntradaDto.getApellido());

    // Paso 3: Configurar el mock para simular la actualización y devolver el
    // odontólogo actualizado
    when(odontologoRepositoryMock.save(any(Odontologo.class))).thenReturn(odontologoActualizado);

    // Paso 4: Realizar la actualización a través del servicio
    OdontologoSalidaDto resultado =
      odontologoService.actualizarOdontologo(odontologoEntradaDto, 1L);

    // Verificar que el resultado es el esperado utilizando OdontologoSalidaDto
    assertNotNull(resultado);
    assertEquals(1L, resultado.getId());
    assertEquals("mat-2002", resultado.getMatricula());
    assertEquals("Ana", resultado.getNombre());
    assertEquals("Mendez", resultado.getApellido());

    // Verificar que el método save fue llamado con cualquier objeto Odontologo
    verify(odontologoRepositoryMock).save(argThat(odontologo -> odontologo.getId().equals(1L) && odontologo.getMatricula().equals("mat-2002") && odontologo.getNombre().equals("Ana") && odontologo.getApellido().equals("Mendez")));
  }

  @Test
  public void alBuscarOdontologoPorId_Inexistente_DebeRetornarNull() {
    // Paso 1: Configurar el mock para devolver un Optional vacío al buscar
    // por un ID inexistente
    Long idInexistente = 99L;
    when(odontologoRepositoryMock.findById(idInexistente)).thenReturn(Optional.empty());

    assertNull(odontologoService.buscarOdontologoPorId(idInexistente));

    verify(odontologoRepositoryMock).findById(idInexistente);
  }

}
