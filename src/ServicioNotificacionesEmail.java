package src;

public class ServicioNotificacionesEmail implements ServicioNotificaciones {
    @Override
    public void enviarNotificaciones(Usuario usuario, String mensaje) { // Cambiado a "enviarNotificaciones"
        System.out.println("[Email] Para: " + usuario.getEmail() + ", Mensaje: " + mensaje);
        // Aquí iría la lógica real para enviar un email
    }
}