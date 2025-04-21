package consola;

import gestor.GestorRecursos;
import gestor.GestorUsuarios;
import modelo.recurso.Audiolibro;
import modelo.recurso.Libro;
import modelo.recurso.Revista;
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
            System.out.println("4. Registrar Recurso");
            System.out.println("5. Listar Recursos");

            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // consumir newline

            switch (opcion) {
                case 1 -> registrarUsuario();
                case 2 -> gestorUsuarios.listarUsuarios();
                case 3 -> System.out.println("Finalizando...");
                case 4 -> registrarRecurso();
                case 5 -> gestorRecursos.listarRecursos();

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
    private void registrarRecurso() {
        System.out.println("Seleccionar tipo de recurso:");
        System.out.println("1. Libro");
        System.out.println("2. Revista");
        System.out.println("3. Audiolibro");
        System.out.print("Opción: ");
        int opcion = Integer.parseInt(scanner.nextLine());

        switch (opcion) {
            case 1 -> registrarLibro();
            case 2 -> registrarRevista();
            case 3 -> registrarAudiolibro();
            default -> System.out.println("❌ Opción no válida.");
        }
    }
    private void registrarLibro() {
        System.out.print("Título del libro: ");
        String titulo = scanner.nextLine();
        System.out.print("Autor: ");
        String autor = scanner.nextLine();
        gestorRecursos.agregarRecurso(new Libro(titulo, autor));
        System.out.println("✅ Libro registrado.");
    }

    private void registrarRevista() {
        System.out.print("Título de la revista: ");
        String titulo = scanner.nextLine();
        System.out.print("Número de edición: ");
        int edicion = Integer.parseInt(scanner.nextLine());
        gestorRecursos.agregarRecurso(new Revista(titulo, edicion) {
            @Override
            public String getEstado() {
                return "";
            }
        });
        System.out.println("✅ Revista registrada.");
    }

    private void registrarAudiolibro() {
        System.out.print("Título del audiolibro: ");
        String titulo = scanner.nextLine();
        System.out.print("Narrador: ");
        String narrador = scanner.nextLine();
        gestorRecursos.agregarRecurso(new Audiolibro(titulo, narrador));
        System.out.println("✅ Audiolibro registrado.");
    }

}
