package Interaccion;
import Interfaces.*;
import Recursos.*;
import Gestores.*;
import Servicios.*;
import Interaccion.*;
import Usuarios.*;
import Excepciones.*;



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
    System.out.println("¿Está disponible?: " + libro.estaDisponible());
    Usuario usuario = new Usuario("Alexis Yassuff", "alexisyassuff@gmail.com");
    libro.prestar(usuario);
    System.out.println("Estado actual del libro: " + libro.getEstado());
    System.out.println("Fecha de devolución: " + libro.getFechaDevolucion());
    System.out.println("¿Está disponible ahora?: " + libro.estaDisponible());
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
    Libro CienAñosDeSoledad = new Libro("Cien años de soledad", "Gabriel García Márquez", EstadoRecurso.Disponible, CategoriaRecurso.FISICO);
    gestor.agregarRecurso(CienAñosDeSoledad.getTitulo(), CienAñosDeSoledad.getAutor(), CienAñosDeSoledad.getEstado(), CienAñosDeSoledad.getCategoria());

    Scanner sc = new Scanner(System.in);

          while (true) {
              System.out.println("\n--- Menú de Biblioteca ---");
              System.out.println("1. Ver Usuarios");
              System.out.println("2. Ver Recursos");
              System.out.println("3. Buscar recurso por título");
              System.out.println("4. Filtrar por categoría");
              System.out.println("5. Ver recursos ordenados por título");
              System.out.println("6. Agregar Usuario");
              System.out.println("7. Agregar Recurso");
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
                      String basura = sc.nextLine();

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
                  case 0 -> {
                      System.out.println("Saliendo...");
                      return;
                  }
                  default -> System.out.println("Opción inválida.");
              }
          }
      }
    }




