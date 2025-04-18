package Interfaces;
import Interfaces.*;
import Recursos.*;
import Gestores.*;
import Servicios.*;
import Interaccion.*;
import Usuarios.*;
import java.time.LocalDateTime;

public interface Prestable {
    boolean estaDisponible();
    LocalDateTime getFechaDevolucion();
    void prestar(Usuario usuario);
}
