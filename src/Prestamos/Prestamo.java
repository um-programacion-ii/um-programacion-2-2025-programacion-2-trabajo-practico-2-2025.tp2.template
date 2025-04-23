package Prestamos;
import Recursos.*;
import Usuarios.*;
import java.time.LocalDateTime;

public class Prestamo {
    private Usuario usuario;
    private RecursoBase recurso;
    private LocalDateTime fechaInicio;
    private LocalDateTime fechaFin;
    private LocalDateTime fechaDevolucion;

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public void setRecurso(RecursoBase recurso) {
        this.recurso = recurso;
    }

    public void setFechaInicio(LocalDateTime fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public void setFechaFin(LocalDateTime fechaFin) {
        this.fechaFin = fechaFin;
    }

    public LocalDateTime getFechaDevolucion() {
        return fechaDevolucion;
    }

    public void setFechaDevolucion(LocalDateTime fechaDevolucion) {
        this.fechaDevolucion = fechaDevolucion;
    }

    public Prestamo(Usuario usuario, RecursoBase recurso) {
        this.usuario = usuario;
        this.recurso = recurso;
        this.fechaInicio = LocalDateTime.now();
        this.fechaFin = fechaInicio.plusDays(14);
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
