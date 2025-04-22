package src;

public abstract class RecursoBase implements RecursoDigital {
    private String titulo;
    private String id;

    public RecursoBase(String titulo, String id) {
        this.titulo = titulo;
        this.id = id;
    }

    @Override
    public String getTitulo() {
        return titulo;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public abstract void mostrarDetalles(); // Este método debe ser abstracto
}