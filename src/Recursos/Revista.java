package Recursos;
import Interfaces.*;
import Recursos.*;
import Gestores.*;
import Servicios.*;
import Interaccion.*;
import Usuarios.*;

import java.time.LocalDateTime;

public class Revista extends RecursoBase implements Renovable {
    private boolean disponible = true;
    private LocalDateTime fechaDevolucion;

    public Revista(String titulo, String autor, EstadoRecurso estado, CategoriaRecurso categoria) {
        super(titulo, autor, estado, categoria);
    }



    @Override
    public LocalDateTime getFechaDevolucion() {
        return fechaDevolucion;
    }


    @Override
    public boolean esRenovable() {
        return false;
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
