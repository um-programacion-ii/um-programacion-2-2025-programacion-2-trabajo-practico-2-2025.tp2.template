package src;

public class Audiolibro implements RecursoDigital, Prestable {
    private String titulo;
    private String id;
    private String narrador;
    private String duracion;
    private boolean prestado = false;

    public Audiolibro(String titulo, String id, String narrador, String duracion) {
        this.titulo = titulo;
        this.id = id;
        this.narrador = narrador;
        this.duracion = duracion;
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
        System.out.println("Audiolibro: " + titulo + " (ID: " + id + ")");
        System.out.println("  Narrador: " + narrador);
        System.out.println("  Duración: " + duracion);
    }

    @Override
    public void prestar(Usuario usuario) {
        this.prestado = true;
        System.out.println("Audiolibro '" + getTitulo() + "' prestado a " + usuario.getNombre() + ".");
    }

    @Override
    public void devolver() {
        this.prestado = false;
        System.out.println("Audiolibro '" + getTitulo() + "' devuelto.");
    }

    @Override
    public boolean estaPrestado() {
        return prestado;
    }
}