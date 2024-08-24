package com.backend.controller;

import com.backend.dto.entrada.OdontologoEntradaDto;
import com.backend.dto.entrada.PacienteEntradaDto;
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

  //htt
  @PostMapping("/registrar")
  public OdontologoSalidaDto registrarOdontologo(@RequestBody @Valid OdontologoEntradaDto odontologo){
    //return odontologoService.registrarOdontologo(odontologo);
    return null;
  }

  @GetMapping("/listar")
  public List<Odontologo> listarOdontologos(){

    OdontologoService odontologoService = new OdontologoService(new OdontologoDaoH2());
    /*
    odontologoService.registrarOdontologo(new Odontologo("matricula-001", "Pepito", "Perez"));
    odontologoService.registrarOdontologo(new Odontologo("matricula-002", "Aquiles", "Brinco"));
    odontologoService.registrarOdontologo(new Odontologo("matricula-003", "Armando", "Casas"));
    odontologoService.registrarOdontologo(new Odontologo("matricula-004", "Betty", "Pinzón"));
    */

    return odontologoService.listarOdontologos();
  }

  @GetMapping("/{id}") //localhost:8080/odontologos/x
  public ResponseEntity<Odontologo> buscarOdontologoPorId(@PathVariable Long id){
    return new ResponseEntity<>(odontologoService.buscarOdontologoPorId(id), HttpStatus.OK);
  }

}
