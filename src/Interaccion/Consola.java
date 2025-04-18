package Interaccion;
import Interfaces.*;
import Recursos.*;
import Gestores.*;
import Servicios.*;
import Interaccion.*;
import Usuarios.*;



import java.util.Scanner;
public class Consola {
      public void iniciar() {
    RecursoBase recursoBase = new RecursoBase("Marquez", "Danilo Peña", EstadoRecurso.EnPrestamo);
    recursoBase.mostrarInformacion();
    recursoBase.actualizarEstado(EstadoRecurso.Disponible);
    recursoBase.mostrarInformacion();

    ServicioNotificaciones notificador = new ServicioNotificacionesEmail();
    GestorUsuarios gestorUsuario = new GestorUsuarios(notificador);

    Usuario AlexisYassuff = new Usuario("Alexis Yassuff", "alexisyassuff@gmail.com");
    gestorUsuario.addUsuario(AlexisYassuff.getNombre(), AlexisYassuff.getEmail());
    Usuario DanielGomez = new Usuario("Daniel Gomez", "danielgomez@gmail.com");
    gestorUsuario.addUsuario(DanielGomez.getNombre(), DanielGomez.getEmail());

          System.out.println("----------------Revision de Interfaces.Prestable--------------------");
          Libro libro = new Libro("Cien años de soledad", "Gabriel García Márquez", EstadoRecurso.Disponible);

          System.out.println("¿Está disponible?: " + libro.estaDisponible());

          Usuario usuario = new Usuario("Alexis Yassuff", "alexisyassuff@gmail.com");

          libro.prestar(usuario);

          System.out.println("Estado actual del libro: " + libro.getEstado());
          System.out.println("Fecha de devolución: " + libro.getFechaDevolucion());
          System.out.println("¿Está disponible ahora?: " + libro.estaDisponible());

          System.out.println("----------------Revision de Interfaces.Renovable--------------------");
          libro.renovar();
          System.out.println("Nueva fecha de devolución: " + libro.getFechaDevolucion());



    Scanner lectura = new Scanner(System.in);
    System.out.println("1. Ver Usuarios");
    System.out.println("2. Ver Recursos");
    System.out.println("3. Ver Prestamos");
    int opcionRecurso = lectura.nextInt();

     if (opcionRecurso == 1){
         System.out.println("Lista de Usuarios");
         gestorUsuario.mostrarLista();
    }

//    else if (opcionRecurso == 2) {
    }

      /*/
    else if (opcionRecurso ==3 ) {
        System.out.println("Introduzca el id del Recursos.Audiolibro a consultar");
        Scanner opcion3 = new Scanner(System.in);
        int idAudiolibroSeleccionado = opcion3.nextInt();
    } /*/

    }




