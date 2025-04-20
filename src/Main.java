import gestores.GestorRecursos;
import gestores.GestorUsuarios;
import gestores.SistemaPrestamos;
import modelo.Audiolibro;
import modelo.Libro;
import modelo.Prestamo;
import modelo.RecursoBase;
import modelo.Revista;
import modelo.Usuario;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        // Instanciar gestores
        GestorUsuarios gestorUsuarios = new GestorUsuarios();
        GestorRecursos gestorRecursos = new GestorRecursos();
        SistemaPrestamos sistemaPrestamos = new SistemaPrestamos();

        // Registrar usuarios
        Usuario usuario1 = new Usuario("U001", "Bruno Piastrellini", "bruno@email.com");
        gestorUsuarios.registrarUsuario(usuario1);

        // Registrar recursos
        Libro libro = new Libro("El Principito", "L001");
        Revista revista = new Revista("Muy Interesante", "R001");
        Audiolibro audiolibro = new Audiolibro("Cuentos para escuchar", "A001");

        gestorRecursos.registrarRecurso(libro);
        gestorRecursos.registrarRecurso(revista);
        gestorRecursos.registrarRecurso(audiolibro);

        // Buscar usuario y recurso
        Usuario usuarioEncontrado = gestorUsuarios.buscarUsuarioPorId("U001");
        RecursoBase recursoEncontrado = gestorRecursos.buscarRecursoPorId("L001");

        // Intentar realizar préstamo
        boolean exito = sistemaPrestamos.realizarPrestamo(usuarioEncontrado, recursoEncontrado);

        if (exito) {
            System.out.println("✅ Préstamo realizado con éxito.");
        } else {
            System.out.println("❌ No se pudo realizar el préstamo.");
        }

        // Mostrar préstamos realizados
        List<Prestamo> prestamosRealizados = sistemaPrestamos.getTodos();
        System.out.println("\n📋 Lista de préstamos:");
        for (Prestamo p : prestamosRealizados) {
            System.out.println(p.getUsuario().getNombre() + " → " + p.getRecurso().getTitulo());
        }
    }
}
