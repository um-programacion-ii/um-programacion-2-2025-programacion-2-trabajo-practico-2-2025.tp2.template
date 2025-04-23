package servicios;

import modelo.*;

import java.time.LocalDate;
import java.util.*;

public class AlertaVencimiento {

    public void verificarVencimientos(List<Prestamo> prestamosActivos) {
        LocalDate hoy = LocalDate.now();

        for (Prestamo prestamo : prestamosActivos) {
            LocalDate vencimiento = prestamo.getFechaDevolucion();

            if (vencimiento != null) {
                if (vencimiento.equals(hoy.plusDays(1))) {
                    System.out.println("🔔 ALERTA: El recurso '" + prestamo.getRecurso().getTitulo()
                            + "' vence MAÑANA para el usuario " + prestamo.getUsuario().getNombre());
                } else if (vencimiento.equals(hoy)) {
                    System.out.println("⚠️ URGENTE: El recurso '" + prestamo.getRecurso().getTitulo()
                            + "' vence HOY para el usuario " + prestamo.getUsuario().getNombre());
                } else if (vencimiento.isBefore(hoy)) {
                    System.out.println("⛔ VENCIDO: El recurso '" + prestamo.getRecurso().getTitulo()
                            + "' ya venció para el usuario " + prestamo.getUsuario().getNombre());
                }
            }
        }
    }

    public static class SistemaReservas {
        private final Map<String, Queue<Reserva>> reservasPorRecurso = new HashMap<>();

        public void realizarReserva(Usuario usuario, RecursoBase recurso) {
            reservasPorRecurso
                    .computeIfAbsent(recurso.getIdentificador(), k -> new LinkedList<>())
                    .add(new Reserva(usuario, recurso));

            System.out.println("📌 Reserva registrada para el recurso '" + recurso.getTitulo() +
                    "' por el usuario " + usuario.getNombre());
        }

        public Reserva obtenerProximaReserva(String idRecurso) {
            Queue<Reserva> cola = reservasPorRecurso.get(idRecurso);
            return (cola != null && !cola.isEmpty()) ? cola.peek() : null;
        }

        public Reserva procesarProximaReserva(String idRecurso) {
            Queue<Reserva> cola = reservasPorRecurso.get(idRecurso);
            if (cola != null) {
                Reserva siguiente = cola.poll();
                if (siguiente != null) {
                    siguiente.setEstado(EstadoReserva.ATENDIDA);
                }
                return siguiente;
            }
            return null;
        }

        public boolean hayReservasPendientes(String idRecurso) {
            Queue<Reserva> cola = reservasPorRecurso.get(idRecurso);
            return cola != null && !cola.isEmpty();
        }

        public void mostrarCola(String idRecurso) {
            Queue<Reserva> cola = reservasPorRecurso.get(idRecurso);
            if (cola == null || cola.isEmpty()) {
                System.out.println("📭 No hay reservas pendientes para el recurso.");
            } else {
                System.out.println("📋 Cola de reservas:");
                for (Reserva r : cola) {
                    System.out.println("- " + r.getUsuario().getNombre()
                            + " (reservado en " + r.getFechaReserva()
                            + ") Estado: " + r.getEstado());
                }
            }
        }
    }
}
