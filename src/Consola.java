public class Consola {
    public static void testerLSP(RecursoDigital t) {
        t.mostrarInformacionLibro();
        t.acceder();
    }

    public void iniciar() {
        Revista revista1 = new Revista();
        revista1.setTitulo("Remix Semanal");
        revista1.setAñoPublicacion(2022);
        revista1.setIdRevista(1);
        revista1.setGenero("Juvenil");
        revista1.setAutor("Remix");
        revista1.setIdLibro(1);
        revista1.setCantidadPaginas(12);
        revista1.mostrarInformacionLibro();
        revista1.acceder();

        AudioLibro AudioLibro1 = new AudioLibro();
        AudioLibro1.setTitulo("La paciencia");
        AudioLibro1.setAñoPublicacion(2022);
        AudioLibro1.setIdAudioLibro(1);
        AudioLibro1.setGenero("Autoayuda");
        AudioLibro1.setAutor("Gabriel Rolon");
        AudioLibro1.setIdLibro(2);
        AudioLibro1.setDuracionMinutos(55);
        AudioLibro1.mostrarInformacionLibro();
        AudioLibro1.acceder();

        Comics Comic1 = new Comics();
        Comic1.setTitulo("El diario de Long");
        Comic1.setAñoPublicacion(2021);
        Comic1.setIdComic(1);
        Comic1.setGenero("Manga");
        Comic1.setAutor("Xi Wang");
        Comic1.setIdLibro(3);
        Comic1.setCantidadPaginas(40);
        Comic1.setIlustrador("Xi Wong Liu");
        Comic1.mostrarInformacionLibro();
        Comic1.acceder();

        System.out.println("****************************** Testeo LSP **************************************");
        testerLSP(Comic1);
        testerLSP(AudioLibro1);
        testerLSP(revista1);

    }
}
