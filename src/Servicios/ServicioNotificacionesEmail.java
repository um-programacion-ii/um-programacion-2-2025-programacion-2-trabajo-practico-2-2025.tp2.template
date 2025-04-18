package Servicios;
import Interfaces.*;
import Recursos.*;
import Gestores.*;
import Servicios.*;
import Interaccion.*;
import Usuarios.*;
import Interfaces.ServicioNotificaciones;

public class ServicioNotificacionesEmail implements ServicioNotificaciones {
    @Override
    public void enviarNotificacion(Usuario destinatario, String mensaje) {
        System.out.println("[EMAIL] Enviando a " + destinatario.getEmail() + ": " + mensaje);
        // Lógica real de envío de email
    }
}
