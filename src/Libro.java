package src;

public class Libro implements RecursoDigital, Prestable {
    private String titulo;
    private String id;
    private String autor;
    private String isbn;
    private boolean prestado = false;

    public Libro(String titulo, String id, String autor, String isbn) {
        this.titulo = titulo;
        this.id = id;
        this.autor = autor;
        this.isbn = isbn;
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
    public void mostrarDetalles() {
        System.out.println("Libro: " + titulo + " (ID: " + id + ")");
        System.out.println("  Autor: " + autor);
        System.out.println("  ISBN: " + isbn);
    }

    @Override
    public void prestar(Usuario usuario) {
        this.prestado = true;
        System.out.println("Libro '" + getTitulo() + "' prestado a " + usuario.getNombre() + ".");
    }

    @Override
    public void devolver() {
        this.prestado = false;
        System.out.println("Libro '" + getTitulo() + "' devuelto.");
    }

    @Override
    public boolean estaPrestado() {
        return prestado;
    }
}