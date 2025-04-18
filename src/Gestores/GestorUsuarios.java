package Gestores;
import Interfaces.*;
import Recursos.*;
import Gestores.*;
import Servicios.*;
import Interaccion.*;
import Usuarios.*;
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

        // Notificación de bienvenida
        notificador.enviarNotificacion(usuarioNuevo,
                "Bienvenido " + nombre + "! Tu cuenta fue creada exitosamente.");
    }
    public void mostrarLista() {
        System.out.println("Lista completa:");
        Usuarios.forEach(System.out::println);
    }
}
