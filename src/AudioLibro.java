public class AudioLibro extends Libro implements  RecursoDigital, Renovable{
    private int idAudioLibro;
    private float duracionMinutos;
    public AudioLibro(int idLibro, int añoPublicacion, String titulo, String autor, String genero) {
        super(idLibro, añoPublicacion, titulo, autor, genero);
    }

    public AudioLibro() {
    }

    public int getIdAudioLibro() {
        return idAudioLibro;
    }

    public void setIdAudioLibro(int idAudioLibro) {
        this.idAudioLibro = idAudioLibro;
    }

    public float getDuracionMinutos() {
        return duracionMinutos;
    }

    public void setDuracionMinutos(float duracionMinutos) {
        this.duracionMinutos = duracionMinutos;
    }

    @Override
    public void mostrarInformacionLibro() {
        System.out.println("-------------------------Informacion AudioLibro------------------------");
        System.out.println("idLibro"  + getIdLibro());
        System.out.println("Año de Publicacion " + getAñoPublicacion());
        System.out.println("Titulo " + getTitulo());
        System.out.println("Autor " + getAutor());
        System.out.println("Genero " + getGenero());
        System.out.println("idRevista " + getIdAudioLibro());
        System.out.println("Cantidad de Paginas " + getDuracionMinutos());
    }

    @Override
    public void acceder() {
        System.out.println("Se inicia la reproduccion de los " + getDuracionMinutos() + " minutos del Libro");
    }

    @Override
    public void mostrarSiEsRenovable() {
        System.out.println("El libro seleccionado es renovable");
    }
}
