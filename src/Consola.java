import gestores.*;
import gestores.SistemaPrestamosConcurrente;
import interfaces.Notificable;
import modelo.*;
import servicios.AlertaVencimiento;
import servicios.NotificadorConsola;
import servicios.ServicioNotificaciones;
import servicios.SistemaRecordatorios;

import java.util.Scanner;

public class Consola {
    private final GestorUsuarios gestorUsuarios;
    private final GestorRecursos gestorRecursos;
    private final AlertaVencimiento.SistemaReservas sistemaReservas;
    private final SistemaPrestamosConcurrente sistemaPrestamos;
    private final ServicioNotificaciones servicioNotificaciones;
    private final Scanner scanner;
    private SistemaRecordatorios recordatorios;

    public Consola() {
        gestorUsuarios = new GestorUsuarios();
        gestorRecursos = new GestorRecursos();
        sistemaReservas = new AlertaVencimiento.SistemaReservas();
        Notificable canal = new NotificadorConsola();
        servicioNotificaciones = new ServicioNotificaciones(canal);
        sistemaPrestamos = new SistemaPrestamosConcurrente(sistemaReservas, servicioNotificaciones);
        scanner = new Scanner(System.in);
    }

    public void iniciar() throws InterruptedException {
        // Iniciar recordatorios periódicos
        recordatorios = new SistemaRecordatorios();
        recordatorios.iniciar(sistemaPrestamos.getTodos());

        boolean salir = false;
        while (!salir) {
            System.out.println("\n========= MENÚ =========");
            System.out.println("1. Registrar usuario");
            System.out.println("2. Registrar recurso");
            System.out.println("3. Realizar préstamo");
            System.out.println("4. Devolver recurso");
            System.out.println("5. Ver reportes");
            System.out.println("6. Ver alertas de vencimiento manual");
            System.out.println("0. Salir");
            System.out.print("Seleccioná una opción: ");
            String opcion = scanner.nextLine();

            switch (opcion) {
                case "1" -> registrarUsuario();
                case "2" -> registrarRecurso();
                case "3" -> realizarPrestamo();
                case "4" -> devolverRecurso();
                case "5" -> verReportes();
                case "6" -> verAlertas();
                case "0" -> {
                    salir = true;
                    sistemaPrestamos.apagarProcesador();
                    recordatorios.detener();
                    System.out.println("👋 Cerrando sistema...");
                }
                default -> System.out.println("❌ Opción inválida.");
            }
        }
        scanner.close();
    }

    private void registrarUsuario() {
        System.out.print("ID de usuario: ");
        String id = scanner.nextLine();
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        gestorUsuarios.registrarUsuario(new Usuario(id, nombre, email));
        System.out.println("✅ Usuario registrado.");
    }

    private void registrarRecurso() {
        System.out.print("Título del recurso: ");
        String titulo = scanner.nextLine();
        System.out.print("ID del recurso: ");
        String idRecurso = scanner.nextLine();
        System.out.print("Categoría (LIBRO, REVISTA, AUDIOLIBRO): ");
        String tipo = scanner.nextLine().toUpperCase();

        RecursoBase recurso = switch (tipo) {
            case "REVISTA" -> new Revista(titulo, idRecurso);
            case "AUDIOLIBRO" -> new Audiolibro(titulo, idRecurso);
            default -> new Libro(titulo, idRecurso);
        };
        gestorRecursos.registrarRecurso(recurso);
        System.out.println("✅ Recurso registrado.");
    }

    private void realizarPrestamo() throws InterruptedException {
        System.out.print("ID del usuario: ");
        String idUsuario = scanner.nextLine();
        System.out.print("ID del recurso: ");
        String idRec = scanner.nextLine();
        Usuario u = gestorUsuarios.buscarUsuarioPorId(idUsuario);
        RecursoBase r = gestorRecursos.buscarRecursoPorId(idRec);
        if (u != null && r != null) {
            sistemaPrestamos.agregarSolicitud(new SolicitudPrestamo(u, r));
            System.out.println("✅ Solicitud enviada.");
        } else {
            System.out.println("❌ Usuario o recurso no encontrado.");
        }
        Thread.sleep(1000);
    }

    private void devolverRecurso() {
        System.out.print("ID del recurso a devolver: ");
        String idDevolver = scanner.nextLine();
        sistemaPrestamos.devolverRecurso(idDevolver);
    }

    private void verReportes() {
        GestorReportes reportes = new GestorReportes(sistemaPrestamos.getTodos());
        reportes.mostrarReporteRecursosMasPrestados();
        reportes.mostrarReporteUsuariosMasActivos();
        reportes.mostrarReportePorTipo();
        reportes.exportarRecursosMasPrestados("reporte_recursos.txt");
    }

    private void verAlertas() {
        AlertaVencimiento alerta = new AlertaVencimiento();
        alerta.verificarVencimientos(sistemaPrestamos.getTodos());
    }
}
