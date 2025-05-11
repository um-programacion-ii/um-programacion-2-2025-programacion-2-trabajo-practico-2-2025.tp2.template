package Gestores;
import Usuarios.*;
import Excepciones.*;
import Interfaces.ServicioNotificaciones;
import java.util.ArrayList;
import java.util.List;

public class GestorUsuarios {
    private List<Usuario> Usuarios = new ArrayList<>();
    private final ServicioNotificaciones notificador;

    public GestorUsuarios(ServicioNotificaciones notificador) {
        this.notificador = notificador;
    }
    public void addUsuario(String nombre, String email) {
        Usuario usuarioNuevo = new Usuario(nombre, email);
        this.Usuarios.add(usuarioNuevo);

        notificador.enviarNotificacion(usuarioNuevo,
                "Bienvenido " + nombre + "! Tu cuenta fue creada exitosamente.");
    }
    public void mostrarLista() {
        Usuarios.forEach(System.out::println);
    }

    public Usuario buscarPorEmail(String email) throws UsuarioNoEncontradoException {
        for (Usuario u : Usuarios) {
            if (u.getEmail().equalsIgnoreCase(email.trim())) {
                return u;
            }
        }
        throw new UsuarioNoEncontradoException("No se encontró ningún usuario con el email: " + email);
    }



}
