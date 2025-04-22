package src;

public class ServicioNotificacionesSMS implements ServicioNotificaciones {
    @Override
    public void enviarNotificacion(Usuario usuario, String mensaje) {
        System.out.println("[SMS] Para: " + usuario.getId() + ", Mensaje: " + mensaje);
        // Aquí iría la lógica real para enviar un SMS
    }
}