package Interfaces;

import java.time.LocalDateTime;
public interface Renovable {
    boolean esRenovable();
    LocalDateTime getFechaDevolucion();
    void renovar();
}
