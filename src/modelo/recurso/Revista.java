// Revista.java
package modelo.recurso;

public class Revista extends RecursoDigital {

    private int numeroEdicion;

    public Revista(String id, int numeroEdicion) {
        super(id, titulo);
        this.numeroEdicion = numeroEdicion;
    }

    @Override
    public String getTitulo() {
        return "";
    }

    @Override
    public String getDescripcion() {
        return "Revista edición Nº " + numeroEdicion;
    }

    @Override
    public String getAutor() {
        return "";
    }

    @Override
    public String toString() {
        return super.toString() + " - Edición: " + numeroEdicion;
    }
}
