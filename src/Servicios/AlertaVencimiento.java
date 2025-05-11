package Servicios;
import Interfaces.*;
import Recursos.*;
import Prestamos.*;
import Usuarios.*;
import Servicios.*;
import Interaccion.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

public class AlertaVencimiento {
    private final SistemaPrestamos sistemaPrestamos;
    private final ServicioNotificaciones notificador;
    private final List<String> historialAlertas = new ArrayList<>();

    public void mostrarHistorial() {
        System.out.println("Historial de alertas:");
        historialAlertas.forEach(System.out::println);
    }

    public void registrarAlerta(String mensaje, NivelUrgencia nivel) {
        String icono = switch (nivel) {
            case INFO -> "ℹ️";
            case WARNING -> "⚠️";
            case ERROR -> "❗";
        };
        String alertaFormateada = icono + " [" + nivel + "] " + mensaje;
        historialAlertas.add(LocalDateTime.now() + " - " + alertaFormateada);
        System.out.println(alertaFormateada);
    }


    public AlertaVencimiento(SistemaPrestamos sistemaPrestamos, ServicioNotificaciones notificador) {
        this.sistemaPrestamos = sistemaPrestamos;
        this.notificador = notificador;
    }

    public void revisarVencimientos() {
        LocalDate hoy = LocalDate.now();

        for (Prestamo p : sistemaPrestamos.getPrestamosActivos()) {
            long diasRestantes = ChronoUnit.DAYS.between(hoy, p.getFechaFin());

            if (diasRestantes == 1) {
                String mensaje = "Tu préstamo del recurso '" + p.getRecurso().getTitulo() + "' vence mañana.";
                registrarAlerta(mensaje, NivelUrgencia.INFO);
                notificador.enviarNotificacion(p.getUsuario(), mensaje);
            } else if (diasRestantes == 0) {
                String mensaje = "Hoy vence tu préstamo del recurso '" + p.getRecurso().getTitulo() + "'.";
                registrarAlerta(mensaje, NivelUrgencia.WARNING);
                notificador.enviarNotificacion(p.getUsuario(), mensaje);
            } else if (diasRestantes < 0) {
                String mensaje = "El préstamo del recurso '" + p.getRecurso().getTitulo() + "' ya está vencido.";
                registrarAlerta(mensaje, NivelUrgencia.ERROR);
                notificador.enviarNotificacion(p.getUsuario(), mensaje);

                System.out.println("¿Deseás renovar este préstamo vencido? (s/n)");
                Scanner scanner = new Scanner(System.in);
                String opcion = scanner.nextLine();
                if (opcion.equalsIgnoreCase("s")) {
                    RecursoBase recurso = p.getRecurso();
                    if (recurso instanceof Renovable) {
                        ((Renovable) recurso).renovar();
                        System.out.println("Préstamo renovado correctamente.");
                    } else {
                        System.out.println("Este recurso no es renovable.");
                    }
                }
            }
        }
    }

}


