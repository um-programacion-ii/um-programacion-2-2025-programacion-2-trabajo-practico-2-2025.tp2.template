package modelo.recurso;

public class Revista implements RecursoDigital {
    private final String titulo;
    private final int numeroEdicion;

    public Revista(String titulo, int numeroEdicion) {
        this.titulo = titulo;
        this.numeroEdicion = numeroEdicion;
    }

    @Override
    public String getTitulo() {
        return titulo;
    }

    @Override
    public String getDescripcion() {
        return "📰 Revista - Título: " + titulo + ", Edición Nº: " + numeroEdicion;
    }

    @Override
    public String getAutor() {
        return "";
    }

    @Override
    public String getEstado() {
        return "";
    }
}
