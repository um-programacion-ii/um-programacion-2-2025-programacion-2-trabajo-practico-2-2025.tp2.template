package src;

public interface RecursoDigital {
    String getTitulo();
    String getId(); // Changed to getId()
    EstadoRecurso getEstado();
    void actualizarEstado(EstadoRecurso estado);
}