package src;

public abstract class RecursoDigital {
    String id;
    String titulo;
    String autor;

    public RecursoDigital(String id, String titulo, String autor) {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;

    }

    public String getId() {
        return id;

    }
    public String getTitulo() {
        return titulo;

    }
    public String getAutor() {
        return autor;

    }
    
}
