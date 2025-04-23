package Interaccion;
import Interfaces.*;
import Recursos.*;
import Usuarios.*;
import Gestores.*;
import Servicios.*;
import Interaccion.*;
import Excepciones.*;
import Prestamos.*;



import java.util.Scanner;
public class Consola {
      public void iniciar() {


    ServicioNotificaciones notificador = new ServicioNotificacionesEmail();
    GestorUsuarios gestorUsuario = new GestorUsuarios(notificador);
    Usuario DanielGomez = new Usuario("Daniel Gomez", "danielgomez@gmail.com");
    gestorUsuario.addUsuario(DanielGomez.getNombre(), DanielGomez.getEmail());
    gestorUsuario.mostrarLista();
    System.out.println("----------------Revision de Interfaces.Prestable--------------------");
    Libro libro = new Libro("Cien años de soledad", "Gabriel García Márquez", EstadoRecurso.Disponible, CategoriaRecurso.FISICO);
    System.out.println("Está disponible ahora?: " + libro.estaDisponible());
    Usuario usuario = new Usuario("Alexis Yassuff", "alexisyassuff@gmail.com");
    libro.prestar(usuario);
    System.out.println("Estado actual del libro: " + libro.getEstado());
    System.out.println("Fecha de devolución: " + libro.getFechaDevolucion());
    System.out.println("Está disponible ahora?: " + libro.estaDisponible());
    System.out.println("----------------Revision de Interfaces.Renovable--------------------");
    libro.renovar();
    System.out.println("Nueva fecha de devolución: " + libro.getFechaDevolucion());
    System.out.println("----------------------------------------------------------------------");

    System.out.println("----------------Agrego Usuarios-----------------------------------");
    GestorBiblioteca gestor = new GestorBiblioteca(notificador);
    Usuario admin = new Usuario("Alexis Yassuff", "alexisyassuff@gmail.com");
    gestor.agregarUsuario(admin.getNombre(), admin.getEmail());
    Usuario RicardoGomez = new Usuario("RicardoGomez", "ricardogomez@gmail.com");
    gestor.agregarUsuario(RicardoGomez.getNombre(), RicardoGomez.getEmail());

    System.out.println("----------------Agrego Recursos-----------------------------------");
    Libro Alquimista = new Libro("El Alquimista", "Garcia Marquez", EstadoRecurso.Disponible, CategoriaRecurso.FISICO);
    gestor.agregarRecurso(Alquimista.getTitulo(), Alquimista.getAutor(), Alquimista.getEstado(), Alquimista.getCategoria());

    //Libro CienAñosDeSoledad = new Libro("Cien años de soledad", "Gabriel García Márquez", EstadoRecurso.Disponible, CategoriaRecurso.VIRTUAL);
    gestor.agregarRecurso("Cien años de soledad", "Gabriel García Márquez", EstadoRecurso.Disponible, CategoriaRecurso.VIRTUAL);
    RecursoBase recursoReal = gestor.buscarRecursoPorTitulo("Cien años de soledad");


    Libro Ghost = new Libro("Ghost", "Luan Torres", EstadoRecurso.EnPrestamo, CategoriaRecurso.FISICO);
    gestor.agregarRecurso(Ghost.getTitulo(), Ghost.getAutor(), Ghost.getEstado(), Ghost.getCategoria());
    SistemaPrestamos sistemaPrestamos = new SistemaPrestamos(notificador);
    System.out.println("///////////////////////////////////////////////////////////////");
    sistemaPrestamos.solicitarPrestamo(RicardoGomez, recursoReal);
    //sistemaPrestamos.solicitarPrestamo(RicardoGomez, CienAñosDeSoledad);
    //System.out.println("Prestamos activos: " + sistemaPrestamos.getPrestamosActivos().size());
    //sistemaPrestamos.devolverRecurso(Integer.parseInt(recursoReal.getIdentificador()));
    System.out.println("Prestamos activos: " + sistemaPrestamos.getPrestamosActivos().size());
    Scanner sc = new Scanner(System.in);
    Usuario usuarioActual = new Usuario("Danilo Peña", "danilopeña@gmail.com");

          while (true) {
              System.out.println("\n--- Menú de Biblioteca ---");
              System.out.println("1. Ver Usuarios");
              System.out.println("2. Ver Recursos");
              System.out.println("3. Buscar recurso por título");
              System.out.println("4. Filtrar por categoría");
              System.out.println("5. Ver recursos ordenados por título");
              System.out.println("6. Agregar Usuario");
              System.out.println("7. Agregar Recurso");
              System.out.println("8. Solicitar préstamo");
              System.out.println("9. Devolver Prestamo");
              System.out.println("10. Renovar Prestamo");
              System.out.println("11 - Ver reservas activas");
              System.out.println("0. Salir");
              System.out.print("Elija una opción: ");
              int opcion = sc.nextInt();

              switch (opcion) {
                  case 1 -> gestor.verUsuarios();
                  case 2 -> gestor.verRecursos();
                  case 3 -> {
                      sc.nextLine();
                      System.out.print("Ingrese el título a buscar: ");
                      String titulo = sc.nextLine();
                      gestor.buscarRecursoPorTitulo(titulo);

                  }
                  case 4 -> {
                      System.out.println("Categorías disponibles:");
                      gestor.mostrarCategoriasDisponibles();
                      System.out.print("Seleccione categoría (1 para FISICO, 2 para VIRTUAL): ");
                      String limpieza = sc.nextLine();

                      int categoriaFiltradoOpt = Integer.parseInt(sc.nextLine());
                      CategoriaRecurso categoria = (categoriaFiltradoOpt == 1)
                              ? CategoriaRecurso.FISICO
                              : CategoriaRecurso.VIRTUAL;
                      System.out.println("Recursos encontrados en categoría: " + categoria);
                      gestor.filtrarPorCategoria(categoria);
                  }


                  case 5 -> gestor.mostrarRecursosOrdenados();
                  case 6 -> {
                      sc.nextLine();
                      System.out.print("Nombre del usuario: ");
                      String nombre = sc.nextLine();
                      System.out.print("Email del usuario: ");
                      String email = sc.nextLine();
                      gestor.agregarUsuario(nombre, email);
                  }
                  case 7 -> {
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
                      System.out.println("Seleccione categoria:");
                      int categoriaOpt = Integer.parseInt(sc.nextLine());
                      CategoriaRecurso categoria = (categoriaOpt == 1) ? CategoriaRecurso.FISICO : CategoriaRecurso.VIRTUAL;
                      gestor.agregarRecurso(titulo, autor, estado, categoria);
                  }
                  case 8 -> {
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
                  case 9 -> {
                      System.out.print("Ingrese el ID del recurso a devolver: ");
                      int id = sc.nextInt(); sc.nextLine();
                      System.out.println("Prestamos activos:");
                      for (Prestamo p : sistemaPrestamos.getPrestamosActivos()) {
                          System.out.println("→ ID: " + p.getRecurso().getIdentificador() + " | Título: " + p.getRecurso().getTitulo());
                      }
                      sistemaPrestamos.devolverRecurso(id);
                  }
                  case 10 -> {
                      System.out.print("Ingrese el ID del recurso a reservar: ");
                      int id = sc.nextInt(); sc.nextLine();
                      RecursoBase recurso = gestor.buscarRecursoPorId(id);
                      if (recurso != null) {
                          sistemaPrestamos.reservarRecurso(usuarioActual, recurso);
                      } else {
                          System.out.println("Recurso no encontrado.");
                      }
                  }
                  case 11 -> {
                      sistemaPrestamos.mostrarReservas();
                  }
                  case 0 -> {
                      System.out.println("Saliendo...");
                      return;
                  }
                  default -> System.out.println("Opción inválida.");
              }
          }
      }
    }




