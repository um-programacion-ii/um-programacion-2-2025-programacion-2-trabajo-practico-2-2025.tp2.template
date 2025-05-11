package Prestamos;
import Recursos.*;
import Usuarios.*;

public class SolicitudPrestamo {
    private Usuario usuario;
    private RecursoBase recurso;

    public SolicitudPrestamo(Usuario usuario, RecursoBase recurso) {
        this.usuario = usuario;
        this.recurso = recurso;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public RecursoBase getRecurso() {
        return recurso;
    }
}

