package Interfaces;
import Recursos.*;

public interface RecursoDigital {
    String getIdentificador();
    EstadoRecurso getEstado();
    void actualizarEstado(EstadoRecurso estado);
    void mostrarInformacion();
}
