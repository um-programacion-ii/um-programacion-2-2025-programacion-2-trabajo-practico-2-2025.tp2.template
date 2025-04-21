package gestor;

import modelo.recurso.RecursoDigital;
import java.util.ArrayList;
import java.util.List;

public class GestorRecursos {
    private List<RecursoDigital> recursos = new ArrayList<>();

    public void agregarRecurso(RecursoDigital recurso) {
        recursos.add(recurso);
    }

    public void listarRecursos() {
        recursos.forEach(System.out::println);
    }
}
