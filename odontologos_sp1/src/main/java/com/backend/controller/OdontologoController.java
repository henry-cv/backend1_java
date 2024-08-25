package com.backend.controller;

import com.backend.dto.entrada.OdontologoEntradaDto;
import com.backend.dto.entrada.OdontologoEntradaDto;
import com.backend.dto.salida.OdontologoSalidaDto;
import com.backend.dto.salida.PacienteSalidaDto;
import com.backend.entity.Odontologo;
import com.backend.repository.impl.OdontologoDaoH2;
import com.backend.repository.impl.OdontologoDaoMemoria;
import com.backend.service.IOdontologoService;
import com.backend.service.impl.OdontologoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/odontologos")
public class OdontologoController {
  private IOdontologoService odontologoService;

  public OdontologoController(IOdontologoService odontologoService) {
    this.odontologoService = odontologoService;
  }

  //POST para crear un registro Odontologo

  //http://localhost:8080/odontologos/registrar
  @PostMapping("/registrar")
  public ResponseEntity<OdontologoSalidaDto> registrarOdontologo(@RequestBody @Valid OdontologoEntradaDto odontologoEntradaDto){
    OdontologoSalidaDto odontologoSalidaDto = odontologoService.registrarOdontologo(odontologoEntradaDto);
    return new ResponseEntity<>(odontologoSalidaDto, HttpStatus.CREATED);
  }
  @GetMapping("/listar")
  public ResponseEntity<List<OdontologoSalidaDto>> listarOdontologos(){
    return new ResponseEntity<>(odontologoService.listarOdontologos(), HttpStatus.OK);
  }

  @GetMapping("/{id}") //localhost:8080/odontologos/x
  public ResponseEntity<OdontologoSalidaDto> buscarOdontologoPorId(@PathVariable Long id){
    return new ResponseEntity<>(odontologoService.buscarOdontologoPorId(id), HttpStatus.OK);
  }

}
