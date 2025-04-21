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
        for (RecursoDigital recurso : recursos) {
            System.out.println("Título: " + recurso.getTitulo());
            System.out.println("Descripción: " + recurso.getDescripcion());
            System.out.println("Estado: " + recurso.getEstado());
            System.out.println("----------------------------------");
        }
    }


}
