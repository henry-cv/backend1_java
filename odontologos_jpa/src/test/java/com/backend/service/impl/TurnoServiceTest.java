package com.backend.service.impl;

import com.backend.repository.OdontologoRepository;
import com.backend.repository.PacienteRepository;
import com.backend.repository.TurnoRepository;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class TurnoServiceTest {
  private final TurnoRepository turnoRepositoryMock =
    mock(TurnoRepository.class);
  private final ModelMapper modelMapper = new ModelMapper();
  private final PacienteRepository pacienteRepositoryMock =
    mock(PacienteRepository.class);
  private final OdontologoRepository odontologoRepository =
    mock(OdontologoRepository.class);
  public TurnoServiceTest() {
  }
}
