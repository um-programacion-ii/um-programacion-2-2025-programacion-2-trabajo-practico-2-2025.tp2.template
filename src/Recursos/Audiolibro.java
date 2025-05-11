package Recursos;

import Interfaces.Renovable;

import java.time.LocalDateTime;
import Interfaces.*;
import Recursos.*;
import Gestores.*;
import Servicios.*;
import Interaccion.*;
import Usuarios.*;
public class Audiolibro extends RecursoBase implements Renovable {
    private LocalDateTime fechaDevolucion;

    public Audiolibro(String titulo, String autor, EstadoRecurso estado, CategoriaRecurso categoria) {
        super(titulo, autor, estado, categoria);
    }

    @Override
    public boolean esRenovable() {
        return getEstado() == EstadoRecurso.EnPrestamo;
    }

    @Override
    public LocalDateTime getFechaDevolucion() {
        return fechaDevolucion;
    }

    @Override
    public void prestar(Usuario usuario) {

    }

    @Override
    public void renovar() {
        if (esRenovable()) {
            this.fechaDevolucion = this.fechaDevolucion.plusDays(7);
            System.out.println("Recursos.Libro renovado con éxito. Nueva fecha de devolución: " + this.fechaDevolucion);
        } else {
            System.out.println("No se puede renovar: estado actual = " + getEstado());
        }
    }

}
