package src;

import java.util.HashMap;
import java.util.Map;

public class GestorRecursos {
    private Map<String, RecursoDigital> recursos;

    public GestorRecursos() {
        this.recursos = new HashMap<>();
    }

    public void agregarRecurso(RecursoDigital recurso) {
        this.recursos.put(recurso.getId(), recurso);
    }

    public RecursoDigital obtenerRecurso(String id) {
        return recursos.get(id);
    }

    public void eliminarRecurso(String id) {
        recursos.remove(id);
    }
}