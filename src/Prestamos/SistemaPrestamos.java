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


    public List<Prestamo> obtenerPrestamosActivos() {
        List<Prestamo> prestamosFiltrados = new ArrayList<>();

        for (Prestamo prestamo : prestamosActivos) {
            if (prestamo.getRecurso().getEstado() == EstadoRecurso.EnPrestamo) {
                prestamosFiltrados.add(prestamo);
            }
        }

        return prestamosFiltrados;
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

    public void devolverRecurso(int recursoId, GestorBiblioteca gestor) {
        String recursoIdStr = String.valueOf(recursoId);
        System.out.println("DEBUG: Buscando recurso con ID: " + recursoIdStr);

        RecursoBase recursoADevolver = null;
        for (RecursoBase recurso : gestor.getRecursos()) {
            if (recurso.getEstado() == EstadoRecurso.EnPrestamo &&
                    recurso.getIdentificador().equals(recursoIdStr)) {
                recursoADevolver = recurso;
                break;
            }
        }

        if (recursoADevolver != null) {
            Prestamo prestamoEncontrado = null;
            for (Prestamo prestamo : prestamosActivos) {
                if (prestamo.getRecurso().getIdentificador().equals(recursoIdStr)) {
                    prestamoEncontrado = prestamo;
                    break;
                }
            }

            if (prestamoEncontrado == null) {
                Usuario usuarioTemporal = new Usuario("Usuario temporal", "temp@example.com");
                prestamoEncontrado = new Prestamo(usuarioTemporal, recursoADevolver);
                prestamosActivos.add(prestamoEncontrado);
            }

            recursoADevolver.devolver();
            prestamosActivos.remove(prestamoEncontrado);

            System.out.println("Recurso devuelto exitosamente: " + recursoADevolver.getTitulo());

            if (!prestamoEncontrado.getUsuario().getNombre().equals("Usuario temporal")) {
                notificador.enviarNotificacion(prestamoEncontrado.getUsuario(),
                        "Devolución completada para el recurso: " + recursoADevolver.getTitulo());
            }

            BlockingQueue<Reserva> cola = reservasPendientes.get(recursoADevolver);
            if (cola != null && !cola.isEmpty()) {
                Reserva siguienteReserva = cola.poll();
                if (siguienteReserva != null) {
                    recursoADevolver.setEstado(EstadoRecurso.EnPrestamo);
                    prestamosActivos.add(new Prestamo(siguienteReserva.getUsuario(), recursoADevolver));
                    executor.submit(() -> notificador.enviarNotificacion(
                            siguienteReserva.getUsuario(),
                            "Tu reserva del recurso ha sido activada automáticamente."
                    ));
                    System.out.println("Reserva de " + siguienteReserva.getUsuario().getNombre() + " activada.");
                }
            }
        } else {
            System.out.println("No se encontró un recurso en préstamo con el ID: " + recursoIdStr);
        }
    }


public void renovarPrestamo(Usuario usuario, int recursoId, GestorBiblioteca gestor) {
    String recursoIdStr = String.valueOf(recursoId);
    System.out.println("DEBUG: Intentando renovar recurso con ID: " + recursoIdStr);

    Prestamo prestamoEncontrado = null;
    for (Prestamo prestamo : prestamosActivos) {
        if (prestamo.getUsuario().equals(usuario) &&
                prestamo.getRecurso().getIdentificador().equals(recursoIdStr)) {
            prestamoEncontrado = prestamo;
            break;
        }
    }

    if (prestamoEncontrado == null) {
        System.out.println("No se encontró un préstamo activo de ese recurso para este usuario.");
        System.out.println("Intentando forzar la renovación...");

        RecursoBase recurso = null;
        for (RecursoBase r : gestor.getRecursos()) {
            if (r.getIdentificador().equals(recursoIdStr)) {
                recurso = r;
                break;
            }
        }

        if (recurso != null && recurso instanceof Renovable) {
            ((Renovable) recurso).renovar();
            System.out.println("Renovación forzada para el recurso: " + recurso.getTitulo());

            Usuario usuarioTemporal = new Usuario("Usuario temporal", "temp@example.com");
            Prestamo prestamoTemporal = new Prestamo(usuarioTemporal, recurso);
            prestamosActivos.add(prestamoTemporal);

            System.out.println("Préstamo simulado agregado (temporal).");

        } else if (recurso != null) {
            System.out.println("El recurso existe pero no es renovable.");
        } else {
            System.out.println("No se encontró el recurso con ese ID.");
        }
        return;
    }

    RecursoBase recurso = prestamoEncontrado.getRecurso();
    if (recurso instanceof Renovable) {
        ((Renovable) recurso).renovar();
        System.out.println("Préstamo renovado para el recurso: " + recurso.getTitulo());
        notificador.enviarNotificacion(usuario, "Renovaste el préstamo de: " + recurso.getTitulo());
    } else {
        System.out.println("Este recurso no es renovable.");
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
