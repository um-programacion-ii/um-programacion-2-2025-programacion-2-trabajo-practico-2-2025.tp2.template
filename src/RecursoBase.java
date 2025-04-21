package src;

import java.time.LocalDateTime;

public abstract class RecursoBase implements RecursoDigital, Prestable {
    String id;
    String titulo;
    String autor;

    public RecursoBase(String id, String titulo, String autor) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public abstract EstadoRecurso getEstado(); // Será implementado por las subclases

    @Override
    public abstract void actualizarEstado(EstadoRecurso estado); // Será implementado por las subclases

    @Override
    public boolean estaDisponible() {
        return getEstado() == EstadoRecurso.DISPONIBLE;
    }

    @Override
    public LocalDateTime getFechaDevolucion() {
        return null; // Por defecto, no hay fecha de devolución hasta que se preste
    }

    @Override
    public void prestar(Usuario usuario) {
        actualizarEstado(EstadoRecurso.PRESTADO);
        // Aquí podríamos registrar la fecha de préstamo y el usuario
    }

    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }
}