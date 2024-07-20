package com.example.foro.controller;

import com.example.foro.domain.respuesta.*;
import com.example.foro.infra.errores.ValidacionDeIntegridad;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/respuestas")
public class RespuestaController {

    @Autowired
    private RespuestaService respuestaService;

    @PostMapping
    public ResponseEntity<?> crearRespuesta(@RequestBody DatosRegistroRespuesta datos) {
        try {
            DatosRespuesta respuesta = respuestaService.crearRespuesta(datos);
            return ResponseEntity.ok(respuesta);
        } catch (ValidacionDeIntegridad ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        }catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocurrió un error inesperado");
        }
    }

    @PutMapping
    @Transactional
    public ResponseEntity<DatosActualizarRespuesta> actualizarRespuesta(@RequestBody @Valid DatosActualizarRespuesta datos){
        var respuestaActualizada = respuestaService.actualizarRespuesta(datos);
        return ResponseEntity.ok(respuestaActualizada);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> eliminarRespuesta(@PathVariable Long id){
        try {
            respuestaService.eliminarRespuesta(id);
            return ResponseEntity.ok("Respuesta eliminada exitosamente");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El id de la respuesta no existe");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> retornarRespuestaPorId(@PathVariable Long id) {

        try{
            Respuesta respuestaPorId = respuestaService.retornarRespuestaPorId(id);
            return ResponseEntity.ok(new DatosRespuesta(respuestaPorId.getId(),
                    respuestaPorId.getMensaje(), respuestaPorId.getFechaCreacion()));
        }catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El id de la respuesta no existe");
        }

    }

}
