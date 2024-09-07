package com.backend.controller;

import com.backend.dto.entrada.TurnoEntradaDto;
import com.backend.dto.salida.TurnoSalidaDto;
import com.backend.exceptions.BadRequestException;
import com.backend.service.ITurnoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/turnos") //localhost:8080/turnos
public class TurnoController {
  private ITurnoService turnoService;

  public TurnoController(ITurnoService turnoService) {
    this.turnoService = turnoService;
  }

  //localhost:8080/turnos/registrar
  @PostMapping("/registrar")
  public ResponseEntity<TurnoSalidaDto> registrarTurno(@RequestBody @Valid TurnoEntradaDto turnoEntradaDto) throws BadRequestException {
    TurnoSalidaDto turnoSalidaDto = turnoService.registrarTurno(turnoEntradaDto);
    return new ResponseEntity<>(turnoSalidaDto, HttpStatus.CREATED);
  }

  //Get
  @GetMapping("/listar")
  public ResponseEntity<List<TurnoSalidaDto>> listarTurnos() {
    return new ResponseEntity<>(turnoService.listarTurnos(), HttpStatus.OK);
  }

  @GetMapping("/{id}")//localhost:8080/turnos/x
  public ResponseEntity<TurnoSalidaDto> buscarTurnoPorId(@PathVariable Long id) {
    return new ResponseEntity<>(turnoService.buscarTurnoPorId(id), HttpStatus.OK);
  }

  //PUT
  @PutMapping("/actualizar/{id}")
  public ResponseEntity<TurnoSalidaDto> actualizarTurno(@RequestBody @Valid TurnoEntradaDto paciente,
                                                        @PathVariable Long id) {
    return new ResponseEntity<>(turnoService.actualizarTurno(paciente, id), HttpStatus.OK);
  }

  //DELETE
  @DeleteMapping("/eliminar")//localhost:8080/turnos/eliminar?id=x
  public ResponseEntity<String> eliminarTurno(@RequestParam Long id) {
    turnoService.eliminarTurno(id);
    return new ResponseEntity<>("Turno eliminado correctamente", HttpStatus.NO_CONTENT);
  }
}
