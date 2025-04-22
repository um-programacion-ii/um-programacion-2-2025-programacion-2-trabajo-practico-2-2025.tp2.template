package entidades;

public abstract class RecursoDigital {
    private int id;
    private String nombre;
    private String autor;
    private String fechaPublicacion;
    private String formato;

    public RecursoDigital(int id, String nombre, String autor, String fechaPublicacion, String formato, String url) {
        this.id = id;
        this.nombre = nombre;
        this.autor = autor;
        this.fechaPublicacion = fechaPublicacion;
        this.formato = formato;
    }

    public RecursoDigital() {
        // Constructor vacío
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(String fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    public String getFormato() {
        return formato;
    }

    public void setFormato(String formato) {
        this.formato = formato;
    }

}
