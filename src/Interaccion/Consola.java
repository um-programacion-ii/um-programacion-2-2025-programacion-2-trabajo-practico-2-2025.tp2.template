package Interaccion;
import Interfaces.*;
import Recursos.*;
import Usuarios.*;
import Gestores.*;
import Servicios.*;
import Interaccion.*;
import Excepciones.*;
import Prestamos.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.Scanner;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Consola {
      public void iniciar() {
          ServicioNotificaciones notificador = new ServicioNotificacionesEmail();
          SistemaPrestamos sistemaPrestamos = new SistemaPrestamos(notificador);
          Estadisticas estadisticas = new Estadisticas(sistemaPrestamos);
          GestorRecursos gestorRecursos = new GestorRecursos(notificador);
          GestorUsuarios gestorUsuario = new GestorUsuarios(notificador);
          GestorBiblioteca gestor = new GestorBiblioteca(notificador, gestorRecursos);
          AlertaVencimiento alertaVencimiento = new AlertaVencimiento(sistemaPrestamos, notificador);
          ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
          scheduler.scheduleAtFixedRate(() -> alertaVencimiento.revisarVencimientos(), 0, 1, TimeUnit.HOURS);
          Scanner sc = new Scanner(System.in);

          Usuario DanielGomez = new Usuario("Daniel Gomez", "danielgomez@gmail.com");
          Usuario AlexisYassuff = new Usuario("Alexis Yassuff", "alexisyassuff@gmail.com");
          Usuario admin = new Usuario("Admin", "admin@admin.com");
          Usuario RicardoGomez = new Usuario("RicardoGomez", "ricardogomez@gmail.com");
          Usuario usuarioActual = new Usuario("Danilo Peña", "danilopeña@gmail.com");
          Usuario MariaLopez = new Usuario("Maria Lopez", "marialopez@gmail.com");
          Usuario JuanPerez = new Usuario("Juan Perez", "juanperez@gmail.com");

          gestor.agregarUsuario(MariaLopez.getNombre(), MariaLopez.getEmail());
          gestor.agregarUsuario(JuanPerez.getNombre(), JuanPerez.getEmail());
          gestor.agregarUsuario(DanielGomez.getNombre(), DanielGomez.getEmail());
          gestor.agregarUsuario(AlexisYassuff.getNombre(), AlexisYassuff.getEmail());
          gestor.agregarUsuario(admin.getNombre(), admin.getEmail());
          gestor.agregarUsuario(RicardoGomez.getNombre(), RicardoGomez.getEmail());

          Libro ElPrincipito = new Libro("El Principito", "Antoine de Saint-Exupéry", EstadoRecurso.Disponible, CategoriaRecurso.FISICO);
          Libro DonQuijote = new Libro("Don Quijote de la Mancha", "Miguel de Cervantes", EstadoRecurso.Disponible, CategoriaRecurso.FISICO);
          Libro RebelionGranja = new Libro("Rebelión en la granja", "George Orwell", EstadoRecurso.EnPrestamo, CategoriaRecurso.FISICO);
          Libro DivinaComedia = new Libro("La Divina Comedia", "Dante Alighieri", EstadoRecurso.Disponible, CategoriaRecurso.FISICO);
          Libro Metamorfosis = new Libro("La Metamorfosis", "Franz Kafka", EstadoRecurso.Disponible, CategoriaRecurso.VIRTUAL);
          Libro MartinFierro = new Libro("El Gaucho Martín Fierro", "José Hernández", EstadoRecurso.Disponible, CategoriaRecurso.FISICO);
          Libro CienAñosSoledad = new Libro("Cien años de soledad", "Gabriel García Márquez", EstadoRecurso.Disponible, CategoriaRecurso.FISICO);
          Libro Alquimista = new Libro("El Alquimista", "Garcia Marquez", EstadoRecurso.Disponible, CategoriaRecurso.VIRTUAL);
          Libro Ghost = new Libro("Ghost", "Luan Torres", EstadoRecurso.EnPrestamo, CategoriaRecurso.FISICO);
          gestor.agregarRecurso(Alquimista.getTitulo(), Alquimista.getAutor(), Alquimista.getEstado(), Alquimista.getCategoria());
          gestor.agregarRecurso(CienAñosSoledad.getTitulo(), CienAñosSoledad.getAutor(), CienAñosSoledad.getEstado(), CienAñosSoledad.getCategoria());
          gestor.agregarRecurso(Ghost.getTitulo(), Ghost.getAutor(), Ghost.getEstado(), Ghost.getCategoria());
          gestor.agregarRecurso(ElPrincipito.getTitulo(), ElPrincipito.getAutor(), ElPrincipito.getEstado(), ElPrincipito.getCategoria());
          gestor.agregarRecurso(DonQuijote.getTitulo(), DonQuijote.getAutor(), DonQuijote.getEstado(), DonQuijote.getCategoria());
          gestor.agregarRecurso(RebelionGranja.getTitulo(), RebelionGranja.getAutor(), RebelionGranja.getEstado(), RebelionGranja.getCategoria());
          gestor.agregarRecurso(DivinaComedia.getTitulo(), DivinaComedia.getAutor(), DivinaComedia.getEstado(), DivinaComedia.getCategoria());
          gestor.agregarRecurso(Metamorfosis.getTitulo(), Metamorfosis.getAutor(), Metamorfosis.getEstado(), Metamorfosis.getCategoria());
          gestor.agregarRecurso(MartinFierro.getTitulo(), MartinFierro.getAutor(), MartinFierro.getEstado(), MartinFierro.getCategoria());

          sistemaPrestamos.solicitarPrestamo(DanielGomez, ElPrincipito);
          sistemaPrestamos.solicitarPrestamo(MariaLopez, Metamorfosis);
          sistemaPrestamos.solicitarPrestamo(RicardoGomez, MartinFierro);


          sistemaPrestamos.reservarRecurso(JuanPerez, Ghost);
          sistemaPrestamos.reservarRecurso(AlexisYassuff, RebelionGranja);

          System.out.println("\n-------------------- Prueba manual del sistema de alertas -------------------------------");
          Libro ElDiariodeLost = new Libro("El Diario de Lost", "Anibal Moreno", EstadoRecurso.EnPrestamo, CategoriaRecurso.FISICO);
          ElDiariodeLost.setFechaDevolucion(LocalDateTime.now().minusDays(2));
          gestor.agregarRecurso(ElDiariodeLost.getTitulo(), ElDiariodeLost.getAutor(), ElDiariodeLost.getEstado(), ElDiariodeLost.getCategoria());
          Prestamo prestamoVencido = new Prestamo(AlexisYassuff, ElDiariodeLost) {
                        public LocalDateTime getFechaFin() {
                            return LocalDateTime.now().minusDays(2);
                        }
                    };
              sistemaPrestamos.getPrestamosActivos().add(prestamoVencido);
              AlertaVencimiento alertaManual = new AlertaVencimiento(sistemaPrestamos, notificador);

          while (true) {
              System.out.println("\n--- Menú Principal ---");
              System.out.println("1. Gestión de Usuarios");
              System.out.println("2. Gestión de Recursos");
              System.out.println("3. Gestión de Préstamos");
              System.out.println("0. Salir");
              System.out.print("Elija una opción: ");
              int opcion = sc.nextInt();

              switch (opcion) {
                  case 1 -> menuGestionUsuarios(sc, gestor);
                  case 2 -> menuGestionRecursos(sc, gestor);
                  case 3 -> menuGestionPrestamos(sc, gestor, sistemaPrestamos, usuarioActual, estadisticas, alertaVencimiento, gestorRecursos);
                  case 0 -> {
                      System.out.println("Saliendo...");
                      return;
                  }
                  default -> System.out.println("Opción inválida.");
              }
          }
      }

    private void menuGestionUsuarios(Scanner sc, GestorBiblioteca gestor) {
        while (true) {
            System.out.println("\n--- Gestión de Usuarios ---");
            System.out.println("1. Ver Usuarios");
            System.out.println("2. Agregar Usuario");
            System.out.println("0. Volver al Menú Principal");
            System.out.print("Elija una opción: ");
            int opcion = sc.nextInt();

            switch (opcion) {
                case 1 -> gestor.verUsuarios();
                case 2 -> {
                    sc.nextLine();
                    System.out.print("Nombre del usuario: ");
                    String nombre = sc.nextLine();
                    System.out.print("Email del usuario: ");
                    String email = sc.nextLine();
                    gestor.agregarUsuario(nombre, email);
                }
                case 0 -> {
                    return;
                }
                default -> System.out.println("Opción inválida.");
            }
        }
    }

    private void menuGestionRecursos(Scanner sc, GestorBiblioteca gestor) {
        while (true) {
            System.out.println("\n--- Gestión de Recursos ---");
            System.out.println("1. Ver Recursos");
            System.out.println("2. Buscar recurso por título");
            System.out.println("3. Filtrar por categoría");
            System.out.println("4. Ver recursos ordenados por título");
            System.out.println("5. Agregar Recurso");
            System.out.println("0. Volver al Menú Principal");
            System.out.print("Elija una opción: ");
            int opcion = sc.nextInt();

            switch (opcion) {
                case 1 -> gestor.verRecursos();
                case 2 -> {
                    sc.nextLine();
                    System.out.print("Ingrese el título a buscar: ");
                    String titulo = sc.nextLine();
                    gestor.buscarRecursoPorTitulo(titulo);
                }
                case 3 -> {
                    System.out.println("Categorías disponibles:");
                    gestor.mostrarCategoriasDisponibles();
                    System.out.print("Seleccione categoría (1 para FISICO, 2 para VIRTUAL): ");
                    sc.nextLine();
                    int categoriaFiltradoOpt = Integer.parseInt(sc.nextLine());
                    CategoriaRecurso categoria = (categoriaFiltradoOpt == 1)
                            ? CategoriaRecurso.FISICO
                            : CategoriaRecurso.VIRTUAL;
                    System.out.println("Recursos encontrados en categoría: " + categoria);
                    gestor.filtrarPorCategoria(categoria);
                }
                case 4 -> gestor.mostrarRecursosOrdenados();
                case 5 -> {
                    sc.nextLine();
                    System.out.print("Título del recurso: ");
                    String titulo = sc.nextLine();
                    System.out.print("Autor: ");
                    String autor = sc.nextLine();
                    System.out.println("Seleccione estado del recurso:");
                    System.out.println("1. Disponible");
                    System.out.println("2. En préstamo");
                    System.out.print("Opción: ");
                    int estadoOpt = Integer.parseInt(sc.nextLine());
                    EstadoRecurso estado = (estadoOpt == 1) ? EstadoRecurso.Disponible : EstadoRecurso.EnPrestamo;
                    System.out.print("Categoría: ");
                    gestor.mostrarCategoriasDisponibles();
                    System.out.println("Seleccione categoría:");
                    int categoriaOpt = Integer.parseInt(sc.nextLine());
                    CategoriaRecurso categoria = (categoriaOpt == 1) ? CategoriaRecurso.FISICO : CategoriaRecurso.VIRTUAL;
                    gestor.agregarRecurso(titulo, autor, estado, categoria);
                }
                case 0 -> {
                    return;
                }
                default -> System.out.println("Opción inválida.");
            }
        }
    }

    private void menuGestionPrestamos(Scanner sc, GestorBiblioteca gestor, SistemaPrestamos sistemaPrestamos, Usuario usuarioActual, Estadisticas estadisticas, AlertaVencimiento alertaVencimiento, GestorRecursos gestorRecursos) {
        while (true) {
            System.out.println("\n--- Gestión de Préstamos ---");
            System.out.println("1. Solicitar préstamo");
            System.out.println("2. Devolver préstamo");
            System.out.println("3. Renovar préstamo");
            System.out.println("4. Ver recursos disponibles");
            System.out.println("5. Reservar recurso");
            System.out.println("6. Ver reservas activas");
            System.out.println("7. Generar reporte de estadísticas");
            System.out.println("8. Mostrar historial de alertas");
            System.out.println("0. Volver al Menú Principal");
            System.out.print("Elija una opción: ");
            int opcion = sc.nextInt();

            switch (opcion) {
                case 1 -> {
                    sc.nextLine();
                    System.out.print("Ingrese el email del usuario: ");
                    String email = sc.nextLine();

                    Usuario usuarioEncontrado;
                                          try {
                                              usuarioEncontrado = gestor.buscarUsuarioPorEmail(email);
                                          } catch (UsuarioNoEncontradoException e) {
                                              System.out.println("Error: " + e.getMessage());
                                              break;
                                          }

                                          System.out.print("Ingrese el título del recurso: ");
                                          String titulo = sc.nextLine();
                                          RecursoBase recurso = gestor.buscarRecursoPorTitulo(titulo);
                                          if (recurso == null) {
                                              System.out.println("Recurso no encontrado.");
                                              break;
                                          }

                                          sistemaPrestamos.solicitarPrestamo(usuarioEncontrado, recurso);
                                          System.out.println("Solicitud de préstamo enviada.");
                }
                case 2 -> {
                    System.out.println("Recursos actualmente en préstamo:");
                    boolean hayEnPrestamo = false;
                    for (RecursoBase recurso : gestor.getRecursos()) {
                        if (recurso.getEstado() == EstadoRecurso.EnPrestamo) {
                            hayEnPrestamo = true;
                            System.out.println("Título: " + recurso.getTitulo() + " (ID: " + recurso.getIdentificador() + ")");
                        }
                    }
                    if (!hayEnPrestamo) {
                        System.out.println("No hay recursos en préstamo actualmente.");
                        break;
                    }
                    System.out.print("Ingrese el ID del recurso a devolver: ");
                    int recursoId = sc.nextInt();
                    sc.nextLine();
                    sistemaPrestamos.devolverRecurso(recursoId, gestor);
                }
                case 3 -> {
                    boolean hayEnPrestamo = false;

                    System.out.println("\nRecursos actualmente en préstamo:");
                    for (RecursoBase recurso : gestor.getRecursos()) {
                        if (recurso.getEstado() == EstadoRecurso.EnPrestamo) {
                            hayEnPrestamo = true;
                            System.out.println("• Título: " + recurso.getTitulo() + " (ID: " + recurso.getIdentificador() + ")");
                        }
                    }

                    if (!hayEnPrestamo) {
                        System.out.println("No hay recursos en préstamo actualmente.");
                        break;
                    }

                    System.out.print("Ingrese el ID del recurso que desea renovar: ");
                    int recursoId = sc.nextInt();
                    sc.nextLine();

                    sistemaPrestamos.renovarPrestamo(usuarioActual, recursoId, gestor);
                }
                case 4 -> {
                    AlertaDisponibilidad alertaDisp = new AlertaDisponibilidad(gestorRecursos, gestor, sistemaPrestamos);
                    alertaDisp.verificarRecursosDisponibles();
                }

                case 5 -> {
                    sc.nextLine();

                    System.out.println("Recursos actualmente en préstamo:");

                    boolean hayEnPrestamo = false;

                    for (RecursoBase recurso : gestor.getRecursos()) {
                        if (recurso.getEstado() == EstadoRecurso.EnPrestamo) {
                            hayEnPrestamo = true;
                            System.out.println("Título: " + recurso.getTitulo() + " (ID: " + recurso.getIdentificador() + ")");
                            System.out.print("¿Deseás reservar este recurso? (s/n): ");
                            String opcionReserva = sc.nextLine();

                            if (opcionReserva.equalsIgnoreCase("s")) {
                                System.out.print("Ingrese el email del usuario: ");
                                String email = sc.nextLine();

                                Usuario usuarioEncontrado;
                                try {
                                    usuarioEncontrado = gestor.buscarUsuarioPorEmail(email);
                                } catch (UsuarioNoEncontradoException e) {
                                    System.out.println("Error: " + e.getMessage());
                                    continue;
                                }

                                sistemaPrestamos.reservarRecurso(usuarioEncontrado, recurso);
                            }
                        }
                    }

                    if (!hayEnPrestamo) {
                        System.out.println("No hay recursos en préstamo actualmente.");
                    }
                }
                case 6 -> sistemaPrestamos.mostrarReservas();
                case 7 -> {
                    estadisticas.generarReporteRecursosMasPrestados();
                    estadisticas.generarReporteUsuariosMasActivos();
                    estadisticas.generarEstadisticasPorCategoria();
                    System.out.println("\n✅ Reporte generado correctamente.");
                }
                case 8 -> {
                    System.out.println("\n-------------------- Sistema de Alertas -------------------------------");
                    alertaVencimiento.revisarVencimientos();
                    alertaVencimiento.mostrarHistorial();
                }
                case 0 -> {
                    return;
                }
                default -> System.out.println("Opción inválida.");
            }
        }
    }

    }




