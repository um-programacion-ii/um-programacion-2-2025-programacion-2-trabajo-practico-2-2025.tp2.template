package Prestamos;
import Recursos.*;
import Usuarios.*;
import java.time.LocalDateTime;

public class Prestamo {
    private Usuario usuario;
    private RecursoBase recurso;
    private LocalDateTime fechaInicio;
    private LocalDateTime fechaFin;

    public Prestamo(Usuario usuario, RecursoBase recurso) {
        this.usuario = usuario;
        this.recurso = recurso;
        this.fechaInicio = LocalDateTime.now();
        this.fechaFin = fechaInicio.plusDays(14); // préstamo por 14 días
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public RecursoBase getRecurso() {
        return recurso;
    }

    public LocalDateTime getFechaInicio() {
        return fechaInicio;
    }

    public LocalDateTime getFechaFin() {
        return fechaFin;
    }

    public boolean estaVencido() {
        return LocalDateTime.now().isAfter(fechaFin);
    }
}
