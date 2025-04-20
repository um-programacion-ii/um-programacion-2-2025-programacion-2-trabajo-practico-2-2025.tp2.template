package gestores;

import modelo.Prestamo;
import modelo.RecursoBase;
import modelo.Usuario;

import java.util.ArrayList;
import java.util.List;

public class SistemaPrestamos {
    private final List<Prestamo> prestamos = new ArrayList<>();

    public boolean realizarPrestamo(Usuario usuario, RecursoBase recurso) {
        if (usuario == null || recurso == null || !recurso.estaDisponible()) return false;

        Prestamo prestamo = new Prestamo(usuario, recurso);
        prestamos.add(prestamo);
        recurso.prestar(usuario);
        return true;
    }

    public List<Prestamo> getTodos() {
        return prestamos;
    }
}
