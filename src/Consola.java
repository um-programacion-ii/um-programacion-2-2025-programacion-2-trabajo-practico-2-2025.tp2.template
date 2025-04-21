package src;

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

    public void mostrarMenuPrincipal() {
        int opcion;
        do {
            System.out.println("\n--- Menú Principal ---");
            System.out.println("1. Gestión de Usuarios");
            System.out.println("2. Gestión de Recursos");
            System.out.println("0. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir la nueva línea

            switch (opcion) {
                case 1:
                    mostrarMenuGestionUsuarios();
                    break;
                case 2:
                    mostrarMenuGestionRecursos();
                    break;
                case 0:
                    System.out.println("Saliendo del sistema.");
                    break;
                default:
                    System.out.println("Opción inválida. Intente nuevamente.");
            }
        } while (opcion != 0);
    }

    private void mostrarMenuGestionUsuarios() {
        int opcion;
        do {
            System.out.println("\n--- Gestión de Usuarios ---");
            System.out.println("1. Agregar Usuario");
            System.out.println("2. Buscar Usuario");
            System.out.println("0. Volver al Menú Principal");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir la nueva línea

            switch (opcion) {
                case 1:
                    agregarUsuarioConsola();
                    break;
                case 2:
                    buscarUsuarioConsola();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opción inválida. Intente nuevamente.");
            }
        } while (opcion != 0);
    }

    private void agregarUsuarioConsola() {
        System.out.println("\n--- Agregar Usuario ---");
        System.out.print("Ingrese el ID del usuario: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Ingrese el nombre del usuario: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingrese el apellido del usuario: ");
        String apellido = scanner.nextLine();
        System.out.print("Ingrese el email del usuario: ");
        String email = scanner.nextLine();

        Usuario nuevoUsuario = new Usuario();
        nuevoUsuario.id = id;
        nuevoUsuario.nombre = nombre;
        nuevoUsuario.apellido = apellido;
        nuevoUsuario.email = email;

        gestorUsuarios.agregarUsuario(nuevoUsuario);
        System.out.println("Usuario agregado correctamente.");
    }

    private void buscarUsuarioConsola() {
        System.out.println("\n--- Buscar Usuario ---");
        System.out.print("Ingrese el ID del usuario a buscar: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Usuario usuarioEncontrado = gestorUsuarios.obtenerUsuario(id);
        if (usuarioEncontrado != null) {
            System.out.println("Usuario encontrado:");
            System.out.println("ID: " + usuarioEncontrado.id);
            System.out.println("Nombre: " + usuarioEncontrado.nombre);
            System.out.println("Apellido: " + usuarioEncontrado.apellido);
            System.out.println("Email: " + usuarioEncontrado.email);
        } else {
            System.out.println("Usuario con ID " + id + " no encontrado.");
        }
    }

    private void mostrarMenuGestionRecursos() {
        int opcion;
        do {
            System.out.println("\n--- Gestión de Recursos ---");
            System.out.println("1. Agregar Recurso (Temporal)");
            System.out.println("0. Volver al Menú Principal");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // Consumir la nueva línea

            switch (opcion) {
                case 1:
                    agregarRecursoTemporalConsola();
                    break;
                case 0:
                    break;
                default:
                    System.out.println("Opción inválida. Intente nuevamente.");
            }
        } while (opcion != 0);
    }

    private void agregarRecursoTemporalConsola() {
        System.out.println("\n--- Agregar Recurso Temporal ---");
        System.out.print("Ingrese el ID del recurso: ");
        String id = scanner.nextLine();
        System.out.print("Ingrese el título del recurso: ");
        String titulo = scanner.nextLine();
        System.out.print("Ingrese el autor del recurso: ");
        String autor = scanner.nextLine();

        // Temporalmente creamos un RecursoBase (necesitaremos clases concretas)
        RecursoBase nuevoRecurso = new RecursoBase(id, titulo, autor) {
            @Override
            public String getId() {
                return this.id;
            }

            @Override
            public EstadoRecurso getEstado() {
                return EstadoRecurso.DISPONIBLE; // Temporal
            }

            @Override
            public void actualizarEstado(EstadoRecurso estado) {
                // Temporal
            }

            @Override
            public boolean estaDisponible() {
                return true; // Temporal
            }

            @Override
            public java.time.LocalDateTime getFechaDevolucion() {
                return null; // Temporal
            }

            @Override
            public void prestar(Usuario usuario) {
                // Temporal
            }
        };
        gestorRecursos.agregarRecurso(nuevoRecurso);
        System.out.println("Recurso temporal agregado correctamente.");
    }

    public void cerrarScanner() {
        scanner.close();
    }

    public static void main(String[] args) {
        GestorUsuarios gestorUsuarios = new GestorUsuarios();
        GestorRecursos gestorRecursos = new GestorRecursos();
        Consola consola = new Consola(gestorUsuarios, gestorRecursos);
        consola.mostrarMenuPrincipal();
        consola.cerrarScanner();
    }
}