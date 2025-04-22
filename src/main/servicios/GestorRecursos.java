package servicios;

public class GestorRecursos {
    private static GestorRecursos instance = null;

    private GestorRecursos() {
        // Constructor privado para evitar instanciación externa
    }

    public static GestorRecursos getInstance() {
        if (instance == null) {
            instance = new GestorRecursos();
        }
        return instance;
    }
}
