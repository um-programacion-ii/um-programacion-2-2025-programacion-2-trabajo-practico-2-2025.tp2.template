package Recursos;
import Recursos.*;
import Gestores.*;
import Servicios.*;
import Interaccion.*;
import Usuarios.*;
import Interfaces.Renovable;

import java.time.LocalDateTime;

public class Libro extends RecursoBase implements Renovable {

    public Libro(String titulo, String autor, EstadoRecurso estado, CategoriaRecurso categoria) {
        super(titulo, autor, estado, categoria);
    }

    public Libro() {
    }

    @Override
    public boolean esRenovable() {
        return getEstado() == EstadoRecurso.EnPrestamo;
    }



    @Override
    public void renovar() {
        if (esRenovable()) {
            if (getFechaDevolucion() == null) {
                setFechaDevolucion(LocalDateTime.now().plusDays(7));
            } else {
                setFechaDevolucion(getFechaDevolucion().plusDays(7));
            }
            System.out.println("Libro renovado. Nueva fecha: " + getFechaDevolucion());
        } else {
            System.out.println("No se puede renovar: estado actual = " + getEstado());
        }
    }


}
