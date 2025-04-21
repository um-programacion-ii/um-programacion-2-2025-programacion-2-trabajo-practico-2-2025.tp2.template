package modelo.recurso;

public class Libro implements RecursoDigital {
    private final String titulo;
    private final String autor;

    public Libro(String titulo, String autor) {
        this.titulo = titulo;
        this.autor = autor;
    }

    @Override
    public String getTitulo() {
        return titulo;
    }

    public String getAutor() {
        return autor;
    }

    @Override
    public String getEstado() {
        return "";
    }

    @Override
    public String getDescripcion() {
        return "📘 Libro - Título: " + titulo + ", Autor: " + autor;
    }
}
