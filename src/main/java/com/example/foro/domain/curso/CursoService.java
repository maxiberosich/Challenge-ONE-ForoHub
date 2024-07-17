package com.example.foro.domain.curso;

import com.example.foro.domain.topico.DatosListadoTopico;
import com.example.foro.domain.topico.Topico;
import com.example.foro.infra.errores.ValidacionDeIntegridad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CursoService {

    @Autowired
    private CursoRepository cursoRepository;

    public Curso crearCurso(DatosRegistroCurso datos) {
        Curso curso = new Curso(datos);
        return cursoRepository.save(curso);
    }

    public List<Curso> listadoCursos() {
        return cursoRepository.findAll();
    }

    public Curso actualizarCurso(DatosActualizarCurso datosActualizarCurso) {
        Curso cursoModificado = cursoRepository.getReferenceById(datosActualizarCurso.id());
        cursoModificado.setNombre(datosActualizarCurso.nombre());
        cursoModificado.setCategoria(datosActualizarCurso.categoria());
        return cursoModificado;
    }

    public void eliminarCurso(Long id) {
        var curso = cursoRepository.getReferenceById(id);
        curso.setStatus(false);
    }

    public Curso retornarCursoPorId(Long id) {
        return cursoRepository.getReferenceById(id);
    }
}
