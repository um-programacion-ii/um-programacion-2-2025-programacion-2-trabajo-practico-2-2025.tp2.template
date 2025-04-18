package Interfaces;
import Usuarios.*;
import java.time.LocalDateTime;

public interface Prestable {
    boolean estaDisponible();
    LocalDateTime getFechaDevolucion();
    void prestar(Usuario usuario);
}
