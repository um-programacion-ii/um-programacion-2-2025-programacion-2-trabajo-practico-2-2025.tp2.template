package Interfaces;
import Interfaces.*;
import Recursos.*;
import Gestores.*;
import Servicios.*;
import Interaccion.*;
import Usuarios.*;
public interface ServicioNotificaciones {
    void enviarNotificacion(Usuario destinatario, String mensaje);
}
