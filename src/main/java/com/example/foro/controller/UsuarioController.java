package com.example.foro.controller;

import com.example.foro.domain.usuario.DatosActualizarUsuario;
import com.example.foro.domain.usuario.DatosRegistroUsuario;
import com.example.foro.domain.usuario.UsuarioService;
import com.example.foro.infra.errores.ValidacionDeIntegridad;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @PostMapping
    public ResponseEntity<?> crearUsuario(@RequestBody DatosRegistroUsuario datos) {
        try {
            var usuarioCreado = usuarioService.crearUsuario(datos);
            return ResponseEntity.ok(datos);
        } catch (ValidacionDeIntegridad ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocurrió un error inesperado");
        }
    }

    @PutMapping
    @Transactional
    public ResponseEntity<?> actualizarUsuario(@RequestBody @Valid DatosActualizarUsuario datosActualizarUsuario){
        try{
            var usuarioActualizado = usuarioService.actualizarUsuario(datosActualizarUsuario);
            return ResponseEntity.ok(new DatosActualizarUsuario(usuarioActualizado.getId(),usuarioActualizado.getNombre(),
                    usuarioActualizado.getEmail(),datosActualizarUsuario.contrasena()));
        }catch (ValidacionDeIntegridad ex){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        }

    }

}
