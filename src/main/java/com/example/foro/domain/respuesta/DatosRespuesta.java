package com.example.foro.domain.respuesta;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DatosRespuesta(
        @NotNull
        Long id,
        @NotBlank
        String mensaje,
        LocalDateTime fechaCreacion
) {
}
