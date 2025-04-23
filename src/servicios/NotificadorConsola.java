package servicios;

import interfaces.Notificable;
import modelo.*;

import java.util.ArrayList;
import java.util.List;

public class NotificadorConsola implements Notificable {
    private final List<Notificacion> notificaciones = new ArrayList<>();

    @Override
    public void enviarNotificacion(String mensaje) {
        Notificacion notificacion = new Notificacion(mensaje);
        notificaciones.add(notificacion);
        System.out.println("🔔 NOTIFICACIÓN: " + mensaje);
    }

    @Override
    public List<Notificacion> getNotificacionesPendientes() {
        return notificaciones;
    }

    public static class SistemaPrestamos {
        private final List<Prestamo> prestamos = new ArrayList<>();
        private final AlertaVencimiento.SistemaReservas sistemaReservas;

        public SistemaPrestamos(AlertaVencimiento.SistemaReservas sistemaReservas) {
            this.sistemaReservas = sistemaReservas;
        }

        public boolean realizarPrestamo(Usuario usuario, RecursoBase recurso) {
            if (usuario == null || recurso == null || !recurso.estaDisponible()) return false;

            Prestamo prestamo = new Prestamo(usuario, recurso);
            prestamos.add(prestamo);
            recurso.prestar(usuario);
            return true;
        }

        public boolean devolverRecurso(String idRecurso) {
            for (Prestamo prestamo : prestamos) {
                if (prestamo.getRecurso().getIdentificador().equals(idRecurso)) {
                    prestamo.getRecurso().devolver();
                    System.out.println("✅ Recurso devuelto correctamente.");

                    if (sistemaReservas.hayReservasPendientes(idRecurso)) {
                        Reserva siguiente = sistemaReservas.procesarProximaReserva(idRecurso);
                        prestamo.getRecurso().prestar(siguiente.getUsuario());
                        System.out.println("📢 El recurso fue entregado automáticamente a: " + siguiente.getUsuario().getNombre());
                    }

                    return true;
                }
            }
            return false;
        }

        public List<Prestamo> getTodos() {
            return prestamos;
        }
    }
}
