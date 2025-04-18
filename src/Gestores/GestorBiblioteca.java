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

public class GestorBiblioteca {
    private final GestorUsuarios gestorUsuarios;
    private final GestorRecursos gestorRecursos;

    public GestorBiblioteca(ServicioNotificaciones notificador) {
        this.gestorUsuarios = new GestorUsuarios(notificador);
        this.gestorRecursos = new GestorRecursos(notificador);

    }

    public void agregarUsuario(String nombre, String email) {
        gestorUsuarios.addUsuario(nombre, email);
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
    public void buscarRecursoPorTitulo(String titulo) {
        var resultados = gestorRecursos.buscarPorTitulo(titulo);
        resultados.forEach(System.out::println);
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
