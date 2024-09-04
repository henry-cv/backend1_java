package com.backend.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler({ResourceNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, String> manejarResourceNotFoundException(ResourceNotFoundException resourceNotFoundException){
        Map<String, String> mensaje = new HashMap<>();
        mensaje.put("mensaje", "Recurso no encontrado:  " + resourceNotFoundException.getMessage());
        return mensaje;
    }



    @ExceptionHandler({MethodArgumentNotValidException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> manejarValidationException(MethodArgumentNotValidException methodArgumentNotValidException) {

        Map<String, String> mensaje = new HashMap<>();

        methodArgumentNotValidException.getBindingResult().getAllErrors().forEach(e -> {
            String nombreCampo = ((FieldError) e).getField();
            String mensajeError = e.getDefaultMessage();
            mensaje.put(nombreCampo, mensajeError);
        });

        return mensaje;
    }
    /*
    // Manejador para BadRequestException
    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handleBadRequestException(BadRequestException badRequestException) {
        // Retorna un ResponseEntity con código de estado 400 y el mensaje de la excepción
        //return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
        Map<String, String> mensaje = new HashMap<>();

        badRequestException.getBindingResult().getAllErrors().forEach(e -> {
            String nombreCampo = ((FieldError) e).getField();
            String mensajeError = e.getDefaultMessage();
            mensaje.put(nombreCampo, mensajeError);
        });

        return mensaje;
    }
    */

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> handleBadRequestException(BadRequestException ex) {
        // Creación del mapa para devolver los detalles del error
        Map<String, String> errorResponse = new HashMap<>();

        // Puedes agregar más detalles del error utilizando los métodos de RuntimeException
        // como getMessage(), getCause(), etc.
        errorResponse.put("error", ex.getMessage());

        // Si quieres añadir más detalles, puedes añadir otros campos como "causa" o "timestamp"
        if (ex.getCause() != null) {
            errorResponse.put("cause", ex.getCause().toString());
        }
        // Puedes agregar más campos según la información que quieras retornar

        return errorResponse;
    }
    //TAREA Manejar BadRequetException

}
