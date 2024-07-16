package com.example.foro.domain.topico;

import com.example.foro.domain.curso.Curso;
import com.example.foro.domain.curso.CursoRepository;
import com.example.foro.domain.usuario.Usuario;
import com.example.foro.domain.usuario.UsuarioRepository;
import com.example.foro.infra.errores.ValidacionDeIntegridad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TopicoService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private TopicoRepository topicoRepository;

    public Topico crearTopico(DatosRegistroTopico datos) {

        if (topicoRepository.existsByTitulo(datos.titulo())) {
            throw new DataIntegrityViolationException("El título ya existe: " + datos.titulo());
        }

        if(topicoRepository.existsByMensaje(datos.mensaje())){
            throw new DataIntegrityViolationException("El mensaje ya existe: " + datos.mensaje());
        }

        if(usuarioRepository.findById(datos.usuarioId()).isEmpty()){
            throw new ValidacionDeIntegridad("Usuario no encontrado");
        }

        if(cursoRepository.findById(datos.cursoId()).isEmpty()){
            throw new ValidacionDeIntegridad("Curso no encontrado");
        }

        Topico topico = new Topico(datos);

        topicoRepository.save(topico);

        return  topico;
    }

    public Page<DatosListadoTopico> listadoTopicos(Pageable paginacion){
        return topicoRepository.findByStatusTrue(paginacion).map(DatosListadoTopico::new);
    }

    public Topico actualizarTopico(DatosActualizarTopico datosActualizarTopico){
        Topico topico = topicoRepository.getReferenceById(datosActualizarTopico.id());
        topico.setTitulo(datosActualizarTopico.titulo());
        topico.setMensaje(datosActualizarTopico.mensaje());
        return topico;
    }

    public void eliminarTopico(Long topico_id){
        var topico = topicoRepository.getReferenceById(topico_id);
        topico.setStatus(false);
    }

    public Topico retornarTopicoPorId(Long topico_id){
        var topicoPorId = topicoRepository.getReferenceById(topico_id);
        if (!topicoPorId.getStatus()){
            return null;
        }
        return topicoPorId;
    }

}
