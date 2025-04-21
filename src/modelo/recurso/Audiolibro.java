package modelo.recurso;

public class Audiolibro implements RecursoDigital {
    private final String titulo;
    private final String narrador;

    public Audiolibro(String titulo, String narrador) {
        this.titulo = titulo;
        this.narrador = narrador;
    }

    @Override
    public String getTitulo() {
        return titulo;
    }

    @Override
    public String getDescripcion() {
        return "🎧 Audiolibro - Título: " + titulo + ", Narrado por: " + narrador;
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
