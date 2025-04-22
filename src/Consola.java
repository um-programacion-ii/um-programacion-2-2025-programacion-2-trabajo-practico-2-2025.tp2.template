// src/Consola.java
package src;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Supplier;

public class Consola {
    private Scanner scanner;
    private GestorRecursos gestorRecursos;
    private Map<String, Supplier<RecursoDigital>> creadoresRecursos;
    private Map<String, String> tiposRecursos; // Para almacenar la descripción de cada opción

    public Consola(GestorRecursos gestorRecursos) {
        this.scanner = new Scanner(System.in);
        this.gestorRecursos = gestorRecursos;
        this.creadoresRecursos = new HashMap<>();
        this.tiposRecursos = new HashMap<>();
        inicializarOpcionesRecursos();
    }

    private void inicializarOpcionesRecursos() {
        creadoresRecursos.put("1", this::crearLibroDesdeInput);
        tiposRecursos.put("1", "Libro");
        creadoresRecursos.put("2", this::crearRevistaDesdeInput);
        tiposRecursos.put("2", "Revista");
        creadoresRecursos.put("3", this::crearAudiolibroDesdeInput);
        tiposRecursos.put("3", "Audiolibro");
        // Para agregar un nuevo tipo de recurso, simplemente añade otra entrada a ambos Maps
        // creadoresRecursos.put("4", this::crearEbookDesdeInput);
        // tiposRecursos.put("4", "Ebook");
    }

    public void mostrarMenu() {
        System.out.println("\n--- Menú Biblioteca Digital ---");
        System.out.println("1. Agregar Recurso");
        System.out.println("2. Mostrar Recurso por ID");
        System.out.println("3. Prestar Recurso");
        System.out.println("4. Salir");
        System.out.print("Seleccione una opción: ");
    }

    public void ejecutarOpcion(String opcion) {
        switch (opcion) {
            case "1":
                agregarNuevoRecurso();
                break;
            case "2":
                mostrarRecursoPorId();
                break;
            case "3":
                prestarRecurso();
                break;
            case "4":
                System.out.println("Saliendo del sistema.");
                break;
            default:
                System.out.println("Opción inválida.");
        }
    }

    private void agregarNuevoRecurso() {
        System.out.println("\n--- Agregar Nuevo Recurso ---");
        System.out.println("Seleccione el tipo de recurso a agregar:");
        for (Map.Entry<String, String> entry : tiposRecursos.entrySet()) {
            System.out.println(entry.getKey() + ". " + entry.getValue());
        }
        System.out.print("Ingrese su opción: ");
        String opcion = scanner.nextLine();

        Supplier<RecursoDigital> creador = creadoresRecursos.get(opcion);
        if (creador != null) {
            RecursoDigital nuevoRecurso = creador.get();
            if (nuevoRecurso != null) {
                gestorRecursos.agregarRecurso(nuevoRecurso);
                System.out.println("Recurso agregado con ID: " + nuevoRecurso.getId());
            }
        } else {
            System.out.println("Opción inválida.");
        }
    }

    private Libro crearLibroDesdeInput() {
        System.out.print("Ingrese el título del libro: ");
        String titulo = scanner.nextLine();
        System.out.print("Ingrese el ID del libro: ");
        String id = scanner.nextLine();
        System.out.print("Ingrese el autor del libro: ");
        String autor = scanner.nextLine();
        System.out.print("Ingrese el ISBN del libro: ");
        String isbn = scanner.nextLine();
        return new Libro(titulo, id, autor, isbn);
    }

    private Revista crearRevistaDesdeInput() {
        System.out.print("Ingrese el título de la revista: ");
        String titulo = scanner.nextLine();
        System.out.print("Ingrese el ID de la revista: ");
        String id = scanner.nextLine();
        System.out.print("Ingrese el número de la revista: ");
        String numero = scanner.nextLine();
        System.out.print("Ingrese el ISSN de la revista: ");
        String issn = scanner.nextLine();
        return new Revista(titulo, id, numero, issn);
    }

    private Audiolibro crearAudiolibroDesdeInput() {
        System.out.print("Ingrese el título del audiolibro: ");
        String titulo = scanner.nextLine();
        System.out.print("Ingrese el ID del audiolibro: ");
        String id = scanner.nextLine();
        System.out.print("Ingrese el narrador del audiolibro: ");
        String narrador = scanner.nextLine();
        System.out.print("Ingrese la duración del audiolibro: ");
        String duracion = scanner.nextLine();
        return new Audiolibro(titulo, id, narrador, duracion);
    }

    private void mostrarRecursoPorId() {
        System.out.print("Ingrese el ID del recurso a mostrar: ");
        String id = scanner.nextLine();
        RecursoDigital recurso = gestorRecursos.obtenerRecurso(id);
        if (recurso != null) {
            System.out.println("\n--- Detalles del Recurso ---");
            recurso.mostrarDetalles();
        } else {
            System.out.println("No se encontró ningún recurso con el ID: " + id);
        }
    }

    private void prestarRecurso() {
        System.out.print("Ingrese el ID del recurso a prestar: ");
        String id = scanner.nextLine();
        RecursoDigital recurso = gestorRecursos.obtenerRecurso(id);
        if (recurso instanceof Prestable) {
            ((Prestable) recurso).prestar();
        } else {
            System.out.println("El recurso con ID " + id + " no se puede prestar.");
        }
    }

    public void cerrarScanner() {
        scanner.close();
    }

    public static void main(String[] args) {
        GestorRecursos gestor = new GestorRecursos();
        Consola consola = new Consola(gestor);

        String opcion;
        do {
            consola.mostrarMenu();
            opcion = consola.scanner.nextLine();
            consola.ejecutarOpcion(opcion);
        } while (!opcion.equals("4"));

        consola.cerrarScanner();
    }
}