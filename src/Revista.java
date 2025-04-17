public class Revista extends Libro implements RecursoDigital{
    private int idRevista;
    private int cantidadPaginas;

    public Revista(int idLibro, int añoPublicacion, String titulo, String autor, String genero, int idRevista, int cantidadPaginas) {
        super(idLibro, añoPublicacion, titulo, autor, genero);
    }

    public Revista() {
    }

    public int getIdRevista() {
        return idRevista;
    }

    public void setIdRevista(int idRevista) {
        this.idRevista = idRevista;
    }

    public int getCantidadPaginas() {
        return cantidadPaginas;
    }

    public void setCantidadPaginas(int cantidadPaginas) {
        this.cantidadPaginas = cantidadPaginas;
    }

    @Override
    public void mostrarInformacionLibro() {
        System.out.println("-------------------------Informacion Revista------------------------");
        System.out.println("idLibro"  + getIdLibro());
        System.out.println("Año de Publicacion " + getAñoPublicacion());
        System.out.println("Titulo " + getTitulo());
        System.out.println("Autor " + getAutor());
        System.out.println("Genero " + getGenero());
        System.out.println("idRevista " + getIdRevista());
        System.out.println("Cantidad de Paginas " + getCantidadPaginas());
    }

    @Override
    public void acceder() {
        System.out.println("Se inicia la lectura de las " + getCantidadPaginas() + " paginas del Libro");
    }
}
