package com.example.foro.domain.respuesta;

import com.example.foro.domain.topico.Topico;
import com.example.foro.domain.usuario.Usuario;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Date;

@Table(name = "respuestas")
@Entity(name = "Respuesta")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Respuesta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String mensaje;

    private LocalDateTime fechaCreacion;

    private Boolean solucion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "topico_id", nullable = false)
    @JsonBackReference
    private Topico topico;

    public Respuesta(String mensaje, Usuario usuario, Topico topico) {
        this.mensaje = mensaje;
        this.fechaCreacion = LocalDateTime.now();
        this.usuario = usuario;
        this.topico = topico;
        this.solucion = false;
    }
}
