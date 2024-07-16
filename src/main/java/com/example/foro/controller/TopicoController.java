package com.example.foro.controller;

import com.example.foro.domain.topico.*;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
    public ResponseEntity<Topico> crearTopico(@RequestBody DatosRegistroTopico datos) {
        Topico topico = topicoService.crearTopico(datos);
        return ResponseEntity.ok(topico);
    }

    @GetMapping
    public ResponseEntity<Page<DatosListadoTopico>> listadoTopicos(@PageableDefault(size = 5) Pageable paginacion){
        var listaTopico = topicoService.listadoTopicos(paginacion);
        return ResponseEntity.ok(listaTopico);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<Topico> actualizarTopico(@RequestBody @Valid DatosActualizarTopico datosActualizarTopico){
        var topicoActualizado = topicoService.actualizarTopico(datosActualizarTopico);
        return ResponseEntity.ok(topicoActualizado);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> eliminarTopico(@PathVariable Long id){
        try {
            topicoService.eliminarTopico(id);
            return ResponseEntity.ok().build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

}
