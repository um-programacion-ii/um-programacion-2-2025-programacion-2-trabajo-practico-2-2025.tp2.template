package Interfaces;
import Interfaces.*;
import Recursos.*;
import Gestores.*;
import Servicios.*;
import Interaccion.*;
import Usuarios.*;
public interface RecursoDigital {
    String getIdentificador();
    EstadoRecurso getEstado();
    void actualizarEstado(EstadoRecurso estado);
    void mostrarInformacion();
}
