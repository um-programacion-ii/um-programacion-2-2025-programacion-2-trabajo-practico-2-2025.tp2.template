package src;

public interface RecursoDigital {
    String getId(); // Changed to getId()
    EstadoRecurso getEstado();
    void actualizarEstado(EstadoRecurso estado);
}