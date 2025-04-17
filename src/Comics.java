public class Comics extends Libro implements RecursoDigital{
    private int idComic;
    private int cantidadPaginas;
    private String ilustrador;
    public Comics(int idLibro, int añoPublicacion, String titulo, String autor, String genero) {
        super(idLibro, añoPublicacion, titulo, autor, genero);
    }

    public Comics() {
    }

    public int getIdComic() {
        return idComic;
    }

    public void setIdComic(int idComic) {
        this.idComic = idComic;
    }

    public int getCantidadPaginas() {
        return cantidadPaginas;
    }

    public void setCantidadPaginas(int cantidadPaginas) {
        this.cantidadPaginas = cantidadPaginas;
    }

    public String getIlustrador() {
        return ilustrador;
    }

    public void setIlustrador(String ilustrador) {
        this.ilustrador = ilustrador;
    }

    @Override
    public void mostrarInformacionLibro() {
        System.out.println("---------------------Informacion Comic-----------------------");
        System.out.println("idComic " + idComic);
        System.out.println("idLibro " + getIdLibro());
        System.out.println("Año de Publicacion " + getAñoPublicacion());
        System.out.println("Titulo " + getTitulo());
        System.out.println("Autor " + getAutor());
        System.out.println("Genero " + getGenero());
        System.out.println("Cantidad de Paginas " + getCantidadPaginas());
        System.out.println("Ilustrador " + ilustrador);
    }

    @Override
    public void acceder() {
        System.out.println("Se inicia la lectura de las " + getCantidadPaginas() + " paginas del Libro");
    }
}
