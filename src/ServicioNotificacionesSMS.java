package src;

public class ServicioNotificacionesSMS implements ServicioNotificaciones {
    @Override
    public void enviarNotificaciones(Usuario usuario, String mensaje) { // Debe coincidir exactamente con la interfaz
        System.out.println("[SMS] Para: " + usuario.getId() + ", Mensaje: " + mensaje);
        // Aquí iría la lógica real para enviar un SMS
    }
}