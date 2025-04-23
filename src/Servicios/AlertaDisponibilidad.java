package Servicios;
import Gestores.*;
import Interfaces.*;
import Recursos.*;
import Prestamos.*;
import Usuarios.*;
import Servicios.*;
import Interaccion.*;
import Excepciones.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

public class AlertaDisponibilidad {
    private final GestorRecursos gestorRecursos;
    private final GestorBiblioteca gestorBiblioteca;
    private final SistemaPrestamos sistemaPrestamos;

    public AlertaDisponibilidad(GestorRecursos gestorRecursos, GestorBiblioteca gestorBiblioteca, SistemaPrestamos sistemaPrestamos) {
        this.gestorRecursos = gestorRecursos;
        this.gestorBiblioteca = gestorBiblioteca;
        this.sistemaPrestamos = sistemaPrestamos;
    }

    public void verificarRecursosDisponibles() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Recursos disponibles:");

        for (RecursoBase r : gestorRecursos.getRecursos()) {
            if (r.getEstado() == EstadoRecurso.Disponible) {
                System.out.println("Recurso disponible: " + r.getTitulo());
                System.out.print("¿Deseás tomarlo prestado ahora? (s/n): ");
                String opcion = scanner.nextLine();

                if (opcion.equalsIgnoreCase("s")) {
                    System.out.print("Ingrese el email del usuario: ");
                    String email = scanner.nextLine();

                    Usuario usuarioEncontrado;
                    try {
                        usuarioEncontrado = gestorBiblioteca.buscarUsuarioPorEmail(email);
                    } catch (UsuarioNoEncontradoException e) {
                        System.out.println("Error: " + e.getMessage());
                        continue;
                    }
                    sistemaPrestamos.solicitarPrestamo(usuarioEncontrado, r);
                    System.out.println("Solicitud de préstamo enviada para '" + r.getTitulo() + "' al usuario " + usuarioEncontrado.getNombre());
                }
            }
        }
    }

}
