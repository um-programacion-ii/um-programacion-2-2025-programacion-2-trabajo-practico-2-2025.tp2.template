public class Usuario {
    private int idUsuario;
    private String nombre;
    private int edad;
    private String email;

    public Usuario(int idUsuario, String nombre, int edad, String email) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.edad = edad;
        this.email = email;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
