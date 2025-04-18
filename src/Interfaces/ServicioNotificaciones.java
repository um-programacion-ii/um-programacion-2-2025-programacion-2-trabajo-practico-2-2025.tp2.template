package Interfaces;
import Usuarios.*;
public interface ServicioNotificaciones {
    void enviarNotificacion(Usuario destinatario, String mensaje);
}
