package modelo.usuario;

public class Usuario {
    private final String id;
    private final String nombre;
    private final String email;

    public Usuario(String id, String nombre, String email) {
        if (id == null || nombre == null || email == null) {
            throw new IllegalArgumentException("Ningún campo puede ser nulo");
        }
        this.id = id;
        this.nombre = nombre;
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return nombre + " (" + email + ")";
    }
}
