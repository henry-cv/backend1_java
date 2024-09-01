package com.backend.controller;

import com.backend.dto.entrada.OdontologoEntradaDto;
import com.backend.dto.salida.OdontologoSalidaDto;
import com.backend.service.IOdontologoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/odontologos")
public class OdontologoController {
  private final IOdontologoService odontologoService;

  public OdontologoController(IOdontologoService odontologoService) {
    this.odontologoService = odontologoService;
  }

  //POST para crear un registro Odontologo

  //http://localhost:8080/odontologos/registrar
  @PostMapping("/registrar")
  public ResponseEntity<OdontologoSalidaDto> registrarOdontologo(
          @RequestBody @Valid OdontologoEntradaDto odontologoEntradaDto) {
    OdontologoSalidaDto odontologoSalidaDto = odontologoService.registrarOdontologo(odontologoEntradaDto);
    return new ResponseEntity<>(odontologoSalidaDto, HttpStatus.CREATED);
  }

  @GetMapping("/listar")
  public ResponseEntity<List<OdontologoSalidaDto>> listarOdontologos() {
    return new ResponseEntity<>(odontologoService.listarOdontologos(), HttpStatus.OK);
  }

  //Buscar por ID
  @GetMapping("/{id}") //localhost:8080/odontologos/x
  public ResponseEntity<OdontologoSalidaDto> buscarOdontologoPorId(@PathVariable Long id) {
    return new ResponseEntity<>(odontologoService.buscarOdontologoPorId(id), HttpStatus.OK);
  }

  //DELETE Borrar por Id
  @DeleteMapping("/{id}") //localhost:8080/odontologos/x
  public ResponseEntity<String> eliminarOdontologoPorId(@PathVariable Long id) {
    odontologoService.eliminarOdontologo(id);
    return new ResponseEntity<>("Odontologo eliminado correctamente", HttpStatus.NO_CONTENT);
  }
  @PutMapping("/actualizar/{id}")
  public ResponseEntity<OdontologoSalidaDto> actualizarOdontologo(@RequestBody @Valid OdontologoEntradaDto odontologoEntradaDto, @PathVariable Long id){
    return new ResponseEntity<>(odontologoService.actualizarOdontologo(odontologoEntradaDto, id), HttpStatus.OK);
  }

}