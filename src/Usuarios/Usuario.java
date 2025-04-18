package Usuarios;

public class Usuario {
    static int contadorUsuarios = 0;
    private int idUsuario;
    private String nombre;
    private String email;
    private String telefono;

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    @Override
    public String toString() {
        return
                "ID de Usuarios.Usuario: " + idUsuario +
                ", Nombre: '" + nombre + '\'' +
                ", Email: '" + email + '\'';
    }

    public Usuario(String nombre, String email) {
        this.idUsuario = contadorUsuarios ;
        contadorUsuarios ++;
        this.nombre = nombre;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
