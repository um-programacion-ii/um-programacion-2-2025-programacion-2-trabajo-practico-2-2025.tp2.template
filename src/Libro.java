public class Libro {
    private int idLibro;
    private int añoPublicacion;
    private String titulo;
    private String autor;
    private String genero;

    public Libro(int idLibro, int añoPublicacion, String titulo, String autor, String genero) {
        this.idLibro = idLibro;
        this.añoPublicacion = añoPublicacion;
        this.titulo = titulo;
        this.autor = autor;
        this.genero = genero;
    }

    public Libro() {
    }

    public int getIdLibro() {
        return idLibro;
    }

    public void setIdLibro(int idLibro) {
        this.idLibro = idLibro;
    }

    public int getAñoPublicacion() {
        return añoPublicacion;
    }

    public void setAñoPublicacion(int añoPublicacion) {
        this.añoPublicacion = añoPublicacion;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }
}
