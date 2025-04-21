package modelo.recurso;

public abstract class RecursoDigital {
    private String id;
    private String titulo;
    private EstadoRecurso estado;

    public RecursoDigital(String id, String titulo) {
        this.id = id;
        this.titulo = titulo;
        this.estado = EstadoRecurso.DISPONIBLE;
    }

    public String getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public EstadoRecurso getEstado() {
        return estado;
    }

    public void setEstado(EstadoRecurso estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "RecursoDigital{" +
                "ID='" + id + '\'' +
                ", Titulo='" + titulo + '\'' +
                ", Estado=" + estado +
                '}';
    }
}
