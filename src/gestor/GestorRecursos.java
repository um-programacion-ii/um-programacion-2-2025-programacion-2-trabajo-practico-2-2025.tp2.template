package gestor;

import modelo.recurso.RecursoDigital;
import java.util.ArrayList;
import java.util.List;

public class GestorRecursos {
    private final List<RecursoDigital> recursos = new ArrayList<>();

    public void agregarRecurso(RecursoDigital recurso) {
        recursos.add(recurso);
    }

    public void listarRecursos() {
        if (recursos.isEmpty()) {
            System.out.println("No hay recursos disponibles.");
        } else {
            recursos.forEach(r -> System.out.println(r.getDescripcion()));
        }
    }
}
