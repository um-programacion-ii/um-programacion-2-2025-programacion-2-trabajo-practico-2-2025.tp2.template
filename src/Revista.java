package src;

public class Revista implements RecursoDigital{
    private String titulo;
    private String id;
    private String numero;
    private String issn;

    public Revista(String titulo, String id, String numero, String issn) {
        this.titulo = titulo;
        this.id = id;
        this.numero = numero;
        this.issn = issn;
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
        System.out.println("Revista: " + titulo + " (ID: " + id + ")");
        System.out.println("  Número: " + numero);
        System.out.println("  ISSN: " + issn);
    }
}
