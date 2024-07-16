package com.example.foro.domain.topico;

import com.example.foro.domain.curso.Curso;
import com.example.foro.domain.usuario.Usuario;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


public record DatosRegistroTopico(
        @NotBlank
        String titulo,
        @NotBlank
        String mensaje,
        @NotNull
        Long usuarioId,
        @NotNull
        Long cursoId
) {
}
