package consola;

import gestor.GestorRecursos;
import gestor.GestorUsuarios;
import modelo.usuario.Usuario;

import java.util.Scanner;

public class Consola {
    private GestorUsuarios gestorUsuarios;
    private GestorRecursos gestorRecursos;
    private Scanner scanner;

    public Consola(GestorUsuarios gestorUsuarios, GestorRecursos gestorRecursos) {
        this.gestorUsuarios = gestorUsuarios;
        this.gestorRecursos = gestorRecursos;
        this.scanner = new Scanner(System.in);
    }

    public void iniciar() {
        int opcion;
        do {
            System.out.println("\n--- Sistema de Biblioteca Digital ---");
            System.out.println("1. Registrar Usuario");
            System.out.println("2. Listar Usuarios");
            System.out.println("3. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // consumir newline

            switch (opcion) {
                case 1 -> registrarUsuario();
                case 2 -> gestorUsuarios.listarUsuarios();
                case 3 -> System.out.println("Finalizando...");
                default -> System.out.println("Opción inválida");
            }
        } while (opcion != 3);
    }

    private void registrarUsuario() {
        System.out.print("Ingrese ID: ");
        String id = scanner.nextLine();
        System.out.print("Ingrese nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingrese email: ");
        String email = scanner.nextLine();

        Usuario usuario = new Usuario(id, nombre, email);
        gestorUsuarios.registrarUsuario(usuario);
        System.out.println("Usuario registrado con éxito.");
    }
}
