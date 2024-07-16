package com.example.foro.domain.topico;

import java.time.LocalDateTime;

public record DatosListadoTopico(
        Long id,
        String titulo,
        String mensaje,
        LocalDateTime fecha_creacion
) {
    public DatosListadoTopico(Topico topico){
        this(topico.getId(),topico.getTitulo(),topico.getMensaje(),topico.getFechaCreacion());
    }
}
