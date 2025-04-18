package Servicios;
import Interfaces.*;
import Recursos.*;
import Gestores.*;
import Servicios.*;
import Interaccion.*;
import Usuarios.*;
import Interfaces.ServicioNotificaciones;

public class ServicioNotificacionesSMS implements ServicioNotificaciones {
    @Override
    public void enviarNotificacion(Usuario destinatario, String mensaje) {
        System.out.println("[SMS] Enviando a " + destinatario.getTelefono() + ": " + mensaje);
    }
}