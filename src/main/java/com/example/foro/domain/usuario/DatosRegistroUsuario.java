package com.example.foro.domain.usuario;

import jakarta.validation.constraints.NotNull;

public record DatosRegistroUsuario(
        @NotNull
        String nombre,
        @NotNull
        String email,
        @NotNull
        String contrasena
) {
}
