package modelo.recurso;

public interface RecursoDigital {
    String getId();
    String getTitulo();
    EstadoRecurso getEstado();
    void setEstado(EstadoRecurso estado);
    String getDescripcion();
}
