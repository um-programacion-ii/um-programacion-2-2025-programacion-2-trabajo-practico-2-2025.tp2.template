import consola.Consola;
import gestor.GestorRecursos;
import gestor.GestorUsuarios;

public class Main {
    public static void main(String[] args) {

        GestorUsuarios gestorUsuarios = new GestorUsuarios();
        GestorRecursos gestorRecursos = new GestorRecursos();
        Consola consola = new Consola(gestorUsuarios, gestorRecursos);
        consola.iniciar();
    }
}
