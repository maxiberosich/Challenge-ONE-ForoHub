package com.example.foro.controller;

import com.example.foro.domain.respuesta.DatosRegistroRespuesta;
import com.example.foro.domain.respuesta.DatosRespuesta;
import com.example.foro.domain.respuesta.Respuesta;
import com.example.foro.domain.respuesta.RespuestaService;
import com.example.foro.domain.topico.DatosRegistroTopico;
import com.example.foro.domain.topico.Topico;
import com.example.foro.infra.errores.ValidacionDeIntegridad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

}
