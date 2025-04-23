package Prestamos;
import Interfaces.*;
import Recursos.*;
import Usuarios.*;
import Gestores.*;
import Servicios.*;
import Interaccion.*;
import Excepciones.*;
import Prestamos.*;

public class Reserva {
    private final Usuario usuario;
    private final RecursoBase recurso;

    public Reserva(Usuario usuario, RecursoBase recurso) {
        this.usuario = usuario;
        this.recurso = recurso;
    }

    public Usuario getUsuario() { return usuario; }
    public RecursoBase getRecurso() { return recurso; }
}
