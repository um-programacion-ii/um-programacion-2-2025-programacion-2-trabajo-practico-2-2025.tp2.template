package servicios;

import interfaces.Notificable;
import modelo.Notificacion;

import java.util.ArrayList;
import java.util.List;

public class NotificadorConsola implements Notificable {
    private final List<Notificacion> pendientes = new ArrayList<>();

    @Override
    public void enviarNotificacion(String mensaje) {
        System.out.println("🔔 NOTIFICACIÓN: " + mensaje);
        pendientes.add(new Notificacion(mensaje));
    }

    @Override
    public List<Notificacion> getNotificacionesPendientes() {
        return pendientes;
    }
}
