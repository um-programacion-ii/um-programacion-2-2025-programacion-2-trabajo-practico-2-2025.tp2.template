package Gestores;
import Interfaces.*;
import Recursos.*;
import Gestores.*;
import Usuarios.*;
import Servicios.*;
import Interaccion.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class GestorBiblioteca {
    private final GestorUsuarios gestorUsuarios;
    private final GestorRecursos gestorRecursos;

    public GestorBiblioteca(ServicioNotificaciones notificador, GestorRecursos gestorRecursos) {
        this.gestorUsuarios = new GestorUsuarios(notificador);
        this.gestorRecursos = gestorRecursos;
    }

    public void agregarUsuario(String nombre, String email) {
        gestorUsuarios.addUsuario(nombre, email);
    }

    public List<RecursoBase> getRecursos() {
        return gestorRecursos.getRecursos();
    }

    public List<RecursoBase> obtenerRecursosPrestados() {
        return gestorRecursos.getRecursos().stream()
                .filter(r -> r.getEstado() == EstadoRecurso.EnPrestamo)
                .collect(Collectors.toList());
    }

    public void agregarRecurso(String titulo, String autor, EstadoRecurso estado, CategoriaRecurso categoria) {
        gestorRecursos.addRecurso(titulo, autor, estado, categoria);
        System.out.println("El recurso " + titulo + " de "+ autor + " con estado "+ estado + " y categoria " + categoria + " fue agregado correctamente");
    }


    public void verUsuarios() {
        gestorUsuarios.mostrarLista();
    }

    public void verRecursos() {
        gestorRecursos.mostrarTodos();
    }
    public RecursoBase buscarRecursoPorTitulo(String titulo) {
        if (titulo == null || titulo.isBlank()) {
            System.out.println("El título está vacío o es null.");
            return null;
        }

        titulo = titulo.trim();

        var resultados = gestorRecursos.buscarPorTitulo(titulo);

        if (resultados == null || resultados.isEmpty()) {
            System.out.println("No se encontraron recursos con el título: " + titulo);
            return null;
        }

        System.out.println("Encontrado: " + resultados.get(0).getTitulo());
        return resultados.get(0);
    }

    public RecursoBase buscarRecursoPorId(int id) {
        var resultados = gestorRecursos.buscarPorId(id);
        if (resultados.isEmpty()) {
            return null;
        }
        return resultados.get(0);
    }


    public void filtrarPorCategoria(CategoriaRecurso categoria) {
        var resultados = gestorRecursos.filtrarPorCategoria(categoria);
        resultados.forEach(System.out::println);
    }

    public void mostrarCategoriasDisponibles() {
        gestorRecursos.mostrarCategorias();
    }

    public void mostrarRecursosOrdenados() {
        var ordenados = gestorRecursos.obtenerOrdenadosPorTitulo();
        ordenados.forEach(System.out::println);
    }

    public Usuario buscarUsuarioPorEmail(String email) {
        return gestorUsuarios.buscarPorEmail(email);
    }
}
