package modelo;

import java.time.LocalDateTime;

public class Reserva {
    private final Usuario usuario;
    private final RecursoBase recurso;
    private final LocalDateTime fechaReserva;

    public Reserva(Usuario usuario, RecursoBase recurso) {
        this.usuario = usuario;
        this.recurso = recurso;
        this.fechaReserva = LocalDateTime.now();
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public RecursoBase getRecurso() {
        return recurso;
    }

    public LocalDateTime getFechaReserva() {
        return fechaReserva;
    }
}
