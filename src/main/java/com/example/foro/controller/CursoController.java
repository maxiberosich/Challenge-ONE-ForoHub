package com.example.foro.controller;

import com.example.foro.domain.curso.Curso;
import com.example.foro.domain.curso.CursoService;
import com.example.foro.domain.curso.DatosActualizarCurso;
import com.example.foro.domain.curso.DatosRegistroCurso;
import com.example.foro.infra.errores.ValidacionDeIntegridad;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cursos")
public class CursoController {

    @Autowired
    private CursoService cursoService;

    @PostMapping
    public ResponseEntity<?> crearCurso(@RequestBody DatosRegistroCurso datos) {
        Curso curso = cursoService.crearCurso(datos);
        return ResponseEntity.ok(curso);
    }

    @GetMapping
    public ResponseEntity<?> listadoCursos(){
        try{
            var listaCurso = cursoService.listadoCursos();
            return ResponseEntity.ok(listaCurso);
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocurrió un error inesperado");
        }

    }

    @PutMapping
    @Transactional
    public ResponseEntity<Curso> actualizarCurso(@RequestBody @Valid DatosActualizarCurso datos){
        var cursoActualizado = cursoService.actualizarCurso(datos);
        return ResponseEntity.ok(cursoActualizado);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> eliminarCurso(@PathVariable Long id){
        try {
            cursoService.eliminarCurso(id);
            return ResponseEntity.ok("Curso eliminado exitosamente");
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("El id del tópico no existe");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> retornarCursoPorId(@PathVariable Long id) {

            Curso cursoPorId = cursoService.retornarCursoPorId(id);
            return ResponseEntity.ok(cursoPorId);

    }

}
