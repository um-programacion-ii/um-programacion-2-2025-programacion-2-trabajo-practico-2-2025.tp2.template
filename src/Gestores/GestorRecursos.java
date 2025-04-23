package Gestores;
import Interfaces.*;
import Recursos.*;
import Gestores.*;
import Servicios.*;
import Interaccion.*;
import Usuarios.*;
import Interfaces.ServicioNotificaciones;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class GestorRecursos {
    private List<RecursoBase> Recursos = new ArrayList<>();
    private final ServicioNotificaciones notificador;

    public GestorRecursos(ServicioNotificaciones notificador) {
        this.notificador = notificador;
    }

    public void addRecurso(String titulo, String autor, EstadoRecurso estado, CategoriaRecurso categoria) {
        RecursoBase recurso = new RecursoBase(titulo, autor, estado, categoria);
        Recursos.add(recurso);

    }

    public List<RecursoBase> buscarPorTitulo(String titulo) {
        return Recursos.stream()
                .filter(r -> r.getTitulo().toLowerCase().contains(titulo.toLowerCase()))
                .toList();
    }

    public List<RecursoBase> filtrarPorCategoria(CategoriaRecurso categoria) {
        return Recursos.stream()
                .filter(r -> r.getCategoria() == categoria)
                .toList();
    }

    public List<RecursoBase> buscarPorId(int id) {
        return Recursos.stream()
                .filter(r -> {
                    try {
                        return Integer.parseInt(r.getIdentificador()) == id;
                    } catch (NumberFormatException e) {
                        return false;
                    }
                })
                .toList();
    }


    public List<RecursoBase> obtenerOrdenadosPorTitulo() {
        return Recursos.stream()
                .sorted(Comparator.comparing(RecursoBase::getTitulo))
                .toList();
    }



    public void mostrarTodos() {
        Recursos.forEach(System.out::println);
    }

    public void mostrarCategorias() {
        for (CategoriaRecurso c : CategoriaRecurso.values()) {
            System.out.println("- " + c);
        }
    }
    public void mostrarRecursos(){
        System.out.println("Lista de Recursos completa");
        Recursos.forEach(System.out::println);
    }


}
