// src/ServicioNotificacionesConsola.java
package src;

public class ServicioNotificacionesConsola implements ServicioNotificaciones {
    @Override
    public void enviarNotificaciones(Usuario usuario, String mensaje) {
        System.out.println("[Notificación] Para el usuario " + usuario.getNombre() + " (ID: " + usuario.getId() + "): " + mensaje);
    }
}