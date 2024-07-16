package com.example.foro.controller;

import com.example.foro.domain.topico.*;
import com.example.foro.infra.errores.ValidacionDeIntegridad;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicoService topicoService;

    @PostMapping
    public ResponseEntity<?> crearTopico(@RequestBody DatosRegistroTopico datos) {
        try {
            Topico topico = topicoService.crearTopico(datos);
            return ResponseEntity.ok(topico);
        } catch (ValidacionDeIntegridad ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        } catch (DataIntegrityViolationException ex) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocurrió un error inesperado");
        }
    }

    @GetMapping
    public ResponseEntity<?> listadoTopicos(@PageableDefault(size = 5) Pageable paginacion){
        try{
            var listaTopico = topicoService.listadoTopicos(paginacion);
            return ResponseEntity.ok(listaTopico);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocurrió un error inesperado");
        }

    }

    @PutMapping
    @Transactional
    public ResponseEntity<Topico> actualizarTopico(@RequestBody @Valid DatosActualizarTopico datosActualizarTopico){
        var topicoActualizado = topicoService.actualizarTopico(datosActualizarTopico);
        return ResponseEntity.ok(topicoActualizado);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> eliminarTopico(@PathVariable Long id){
        try {
            topicoService.eliminarTopico(id);
            return ResponseEntity.ok("Tópico eliminado exitosamente");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El id del tópico no existe");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> retornarTopicoPorId(@PathVariable Long id) {
        try {
            Topico topico = topicoService.retornarTopicoPorId(id);
            if(topico == null){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El tópico fue eliminado");
            }
            return ResponseEntity.ok(topico);
        } catch (ValidacionDeIntegridad ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El tópico no está disponible");
        } catch (RuntimeException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El ID no existe o es nulo");
        }
    }

}
