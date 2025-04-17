public class GestorRecursos {
    public static void gestionPrestamosRenovacion(int valorSeleccionado){
       if (valorSeleccionado == 1) {
        Prestable libroSeleccionado = new Revista();
        libroSeleccionado.mostrarSiEsPrestable();
    } else if (valorSeleccionado == 2) {
        Renovable libroSeleccionado = new AudioLibro();
        libroSeleccionado.mostrarSiEsRenovable();
    }
       else if (valorSeleccionado == 3) {
        Prestable libroSeleccionado = new Comics();
        libroSeleccionado.mostrarSiEsPrestable();
    } else {
           System.out.println("No se selecciono ninguna opcion valida");
       }
}}
