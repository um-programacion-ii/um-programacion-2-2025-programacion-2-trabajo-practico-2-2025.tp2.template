package gestores;
import excepciones.UsuarioNoEncontradoException;
import excepciones.RecursoNoDisponibleException;

import modelo.SolicitudPrestamo;
import modelo.Usuario;
import modelo.RecursoBase;
import modelo.Prestamo;
import modelo.Reserva;
import servicios.AlertaVencimiento;
import servicios.ServicioNotificaciones;

import java.util.concurrent.*;
import java.util.*;

public class SistemaPrestamosConcurrente {
    private final BlockingQueue<SolicitudPrestamo> colaSolicitudes = new LinkedBlockingQueue<>();
    private final List<Prestamo> prestamos = new ArrayList<>();
    private final AlertaVencimiento.SistemaReservas sistemaReservas;
    private final ServicioNotificaciones servicioNotificaciones;
    private final ExecutorService executor;

    public SistemaPrestamosConcurrente(AlertaVencimiento.SistemaReservas sistemaReservas, ServicioNotificaciones servicioNotificaciones) {
        this.sistemaReservas = sistemaReservas;
        this.servicioNotificaciones = servicioNotificaciones;
        this.executor = Executors.newSingleThreadExecutor();
        iniciarProcesador();
    }

    private void iniciarProcesador() {
        executor.execute(() -> {
            try {
                while (true) {
                    SolicitudPrestamo solicitud = colaSolicitudes.take();
                    procesarSolicitud(solicitud);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("🛑 Procesador de préstamos detenido.");
            }
        });
    }

    public void agregarSolicitud(SolicitudPrestamo solicitud) {
        colaSolicitudes.add(solicitud);
    }

    private void procesarSolicitud(SolicitudPrestamo solicitud) {
        try {
            Usuario usuario = solicitud.getUsuario();
            RecursoBase recurso = solicitud.getRecurso();

            if (usuario == null) {
                throw new UsuarioNoEncontradoException("Usuario no encontrado.");
            }

            if (recurso == null || !recurso.estaDisponible()) {
                throw new RecursoNoDisponibleException("El recurso no está disponible para préstamo.");
            }

            recurso.prestar(usuario);
            Prestamo prestamo = new Prestamo(usuario, recurso);
            synchronized (prestamos) {
                prestamos.add(prestamo);
            }

            System.out.println("✅ [HILO] Préstamo procesado: " + usuario.getNombre() + " → " + recurso.getTitulo());

        } catch (UsuarioNoEncontradoException | RecursoNoDisponibleException e) {
            System.out.println("❌ [ERROR] " + e.getMessage());
        }
    }


    public void devolverRecurso(String idRecurso) {
        try {
            synchronized (prestamos) {
                Prestamo encontrado = null;

                for (Prestamo prestamo : prestamos) {
                    if (prestamo.getRecurso().getIdentificador().equals(idRecurso)) {
                        encontrado = prestamo;
                        break;
                    }
                }

                if (encontrado == null) {
                    throw new RecursoNoDisponibleException("No se encontró un préstamo activo para ese recurso.");
                }

                if (encontrado.getRecurso().estaDisponible()) {
                    throw new RecursoNoDisponibleException("El recurso ya está disponible.");
                }

                encontrado.getRecurso().devolver();
                System.out.println("✅ Recurso devuelto correctamente.");

                if (sistemaReservas.hayReservasPendientes(idRecurso)) {
                    Reserva siguiente = sistemaReservas.procesarProximaReserva(idRecurso);
                    encontrado.getRecurso().prestar(siguiente.getUsuario());
                    servicioNotificaciones.enviar("El recurso '" + encontrado.getRecurso().getTitulo()
                            + "' fue entregado automáticamente a " + siguiente.getUsuario().getNombre());
                }
            }

        } catch (RecursoNoDisponibleException e) {
            System.out.println("❌ [ERROR] " + e.getMessage());
        }
    }


    public void apagarProcesador() {
        executor.shutdownNow();
    }

    public List<Prestamo> getTodos() {
        return prestamos;
    }

}
