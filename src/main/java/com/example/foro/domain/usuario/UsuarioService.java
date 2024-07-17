package com.example.foro.domain.usuario;

import com.example.foro.domain.topico.DatosActualizarTopico;
import com.example.foro.infra.errores.ValidacionDeIntegridad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario crearUsuario(DatosRegistroUsuario datos) {
        if (usuarioRepository.findByNombre(datos.nombre()).equals(datos.nombre())) {
            throw new ValidacionDeIntegridad("El nombre ya existe, ingrese otro nombre");
        }

        String contrasenaCodificada = passwordEncoder().encode(datos.contrasena());
        Usuario usuario = new Usuario(datos);
        usuario.setContrasena(contrasenaCodificada);
        return usuarioRepository.save(usuario);
    }

    public Usuario actualizarUsuario(DatosActualizarUsuario datosActualizarUsuario) {
        if (datosActualizarUsuario == null || datosActualizarUsuario.id() == null){
            throw new ValidacionDeIntegridad("Debe ingresar un ID");
        }

        var usuarioEncontrado = usuarioRepository.getReferenceById(datosActualizarUsuario.id());

        if (!datosActualizarUsuario.nombre().equals(usuarioEncontrado.getNombre())){
            usuarioEncontrado.setNombre(datosActualizarUsuario.nombre());
        }

        if (!datosActualizarUsuario.email().equals(usuarioEncontrado.getEmail())){
            usuarioEncontrado.setEmail(datosActualizarUsuario.email());
        }

        if (!datosActualizarUsuario.contrasena().equals(usuarioEncontrado.getContrasena())){
            String contrasenaCodificada = passwordEncoder().encode(datosActualizarUsuario.contrasena());
            usuarioEncontrado.setContrasena(contrasenaCodificada);
        }
        return usuarioEncontrado;
    }


    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
