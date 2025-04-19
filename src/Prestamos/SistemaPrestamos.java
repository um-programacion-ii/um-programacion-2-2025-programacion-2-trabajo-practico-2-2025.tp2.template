package Prestamos;
import Interfaces.*;
import Recursos.*;
import Usuarios.*;
import Gestores.*;
import Servicios.*;
import Interaccion.*;
import Excepciones.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.*;

public class SistemaPrestamos {
    private final List<Prestamo> prestamosActivos;
    private final ServicioNotificaciones notificador;
    private final ExecutorService executor = Executors.newFixedThreadPool(2);
    private final Map<RecursoBase, BlockingQueue<Reserva>> reservasPendientes = new HashMap<>();

    public SistemaPrestamos(ServicioNotificaciones notificador) {
        this.notificador = notificador;
        this.prestamosActivos = new ArrayList<>();
    }



    public void solicitarPrestamo(Usuario usuario, RecursoBase recurso) {
        if (recurso.estaDisponible()) {
            try {
                recurso.prestar(usuario);
                Prestamo nuevoPrestamo = new Prestamo(usuario, recurso);
                prestamosActivos.add(nuevoPrestamo);
                executor.submit(() -> notificador.enviarNotificacion(usuario, "Tu recurso ha sido prestado."));
            } catch (RecursoNoDisponibleException e) {
                System.out.println("Error al prestar recurso: " + e.getMessage());
                notificador.enviarNotificacion(usuario, "No se pudo prestar el recurso: " + recurso.getTitulo());
            }
        } else {
            System.out.println("El recurso no está disponible.");
            notificador.enviarNotificacion(usuario, "Recurso no disponible: " + recurso.getTitulo());
        }
    }

    public void devolverRecurso(int recursoId) {
        Prestamo prestamoEncontrado = null;

        for (Prestamo prestamo : prestamosActivos) {
            int idRecurso = Integer.parseInt(prestamo.getRecurso().getIdentificador());
            if (idRecurso == recursoId) {
                prestamoEncontrado = prestamo;
                break;
            }
        }
        if (prestamoEncontrado != null) {
            RecursoBase recurso = prestamoEncontrado.getRecurso();
            recurso.devolver();
            prestamosActivos.remove(prestamoEncontrado);
            notificador.enviarNotificacion(prestamoEncontrado.getUsuario(),
                    "Devolución completada para el recurso: " + recurso.getTitulo());
            System.out.println("Recurso devuelto exitosamente: " + recurso.getTitulo());
            BlockingQueue<Reserva> cola = reservasPendientes.get(recurso);
            if (cola != null && !cola.isEmpty()) {
                Reserva siguienteReserva = cola.poll();
                if (siguienteReserva != null) {
                    recurso.setEstado(EstadoRecurso.EnPrestamo);
                    prestamosActivos.add(new Prestamo(siguienteReserva.getUsuario(), recurso));
                    executor.submit(() -> notificador.enviarNotificacion(
                            siguienteReserva.getUsuario(),
                            "Tu reserva del recurso '" + recurso.getTitulo() + "' ha sido activada automáticamente."
                    ));
                    System.out.println("Reserva de " + siguienteReserva.getUsuario().getNombre() + " activada.");
                }
            }
        } else {
            System.out.println("No se encontró un préstamo activo para ese recurso.");
        }
    }

    public void reservarRecurso(Usuario usuario, RecursoBase recurso) {
        if (recurso.getEstado() == EstadoRecurso.Disponible) {
            System.out.println("El recurso está disponible, no es necesario reservarlo.");
            return;
        }

        reservasPendientes.putIfAbsent(recurso, new LinkedBlockingQueue<>());
        reservasPendientes.get(recurso).add(new Reserva(usuario, recurso));
        System.out.println("Reserva registrada para el usuario " + usuario.getNombre());
    }

    public void mostrarReservas() {
        reservasPendientes.forEach((recurso, cola) -> {
            System.out.println("Reservas para: " + recurso.getTitulo());
            cola.forEach(reserva -> System.out.println(" - " + reserva.getUsuario().getNombre()));
        });
    }

    public List<Prestamo> getPrestamosActivos() {
        return prestamosActivos;
    }
}
