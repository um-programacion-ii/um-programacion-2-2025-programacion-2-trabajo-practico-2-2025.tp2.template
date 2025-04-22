package servicios;

public class GestorUsuarios {
    private static GestorUsuarios instance = null;

    private GestorUsuarios() {
        // Constructor privado para evitar instanciación externa
    }

    public static GestorUsuarios getInstance() {
        if (instance == null) {
            instance = new GestorUsuarios();
        }
        return instance;
    }
}
