package com.backend.controller;

import com.backend.dto.entrada.PacienteEntradaDto;
import com.backend.dto.salida.PacienteSalidaDto;
import com.backend.exceptions.ResourceNotFoundException;
import com.backend.service.IPacienteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/pacientes")
public class PacienteController {

  private IPacienteService pacienteService;

  public PacienteController(IPacienteService pacienteService) {
    this.pacienteService = pacienteService;
  }


  //JSON -> @RequestBody -> DTO Entrada -> controller --> service -> mapper = entidad --> repository --> BDD
  //--> entidad --> repository --> service -> mapper -> DTO Salida --> controller -> @ResponseBody -> JSON --> cliente

  //POST Registrar nuevo Paciente
  @PostMapping("/registrar")
  public ResponseEntity<PacienteSalidaDto> registrarPaciente(
          @RequestBody @Valid PacienteEntradaDto pacienteEntradaDto) {
    PacienteSalidaDto pacienteSalidaDto = pacienteService.registrarPaciente(pacienteEntradaDto);
    return new ResponseEntity<>(pacienteSalidaDto, HttpStatus.CREATED);
  }

  //GET Listar Todos los Pacientes
  @GetMapping("/listar")
  public ResponseEntity<List<PacienteSalidaDto>> listarPacientes() {
    return new ResponseEntity<>(pacienteService.listarPacientes(), HttpStatus.OK);
  }

  //Buscar por Id
  @GetMapping("/{id}")//localhost:8080/pacientes/x
  public ResponseEntity<PacienteSalidaDto> buscarPacientePorId(@PathVariable Long id) {
    return new ResponseEntity<>(pacienteService.buscarPacientePorId(id), HttpStatus.OK);
  }

  //PUT Actualizar por Id
  @PutMapping("/actualizar/{id}")
  public ResponseEntity<PacienteSalidaDto> actualizarPaciente(@RequestBody @Valid PacienteEntradaDto paciente,
                                                              @PathVariable Long id) {
    return new ResponseEntity<>(pacienteService.actualizarPaciente(paciente, id), HttpStatus.OK);
  }
  /*
  //DELETE Eliminar un registro Paciente
  @DeleteMapping("/{id}")//localhost:8080/pacientes/eliminar?id=x
  public ResponseEntity<String> eliminarPaciente(@PathVariable Long id) {
    pacienteService.eliminarPaciente(id);
    return new ResponseEntity<>("Paciente eliminado correctamente", HttpStatus.NO_CONTENT);
  }
  */
  //DELETE
  @DeleteMapping("/eliminar")//localhost:8080/pacientes/eliminar?id=x
  public ResponseEntity<String> eliminarPaciente(@RequestParam Long id) throws ResourceNotFoundException {
    pacienteService.eliminarPaciente(id);
    return new ResponseEntity<>("Paciente eliminado correctamente", HttpStatus.NO_CONTENT);
  }
}
