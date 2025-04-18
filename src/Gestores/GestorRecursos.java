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

public class GestorRecursos {
    private List<RecursoBase> Recursos = new ArrayList<>();
    private final ServicioNotificaciones notificador;

    public GestorRecursos(ServicioNotificaciones notificador) {
        this.notificador = notificador;
    }

    public void addRecurso(String titulo, String autor, EstadoRecurso estado, Usuario admin) {
        RecursoBase recurso = new RecursoBase(titulo, autor, estado);
        Recursos.add(recurso);

        // Notificación al administrador
        notificador.enviarNotificacion(admin,
                "Nuevo recurso añadido: " + titulo +
                        " (ID: " + recurso.getIdRecursoBase() + ")");
    }

    public void mostrarRecursos(){
        System.out.println("Lista de Recursos completa");
        Recursos.forEach(System.out::println);
    }
}
