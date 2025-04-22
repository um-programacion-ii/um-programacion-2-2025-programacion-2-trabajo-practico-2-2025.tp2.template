package src;

import java.util.ArrayList;
import java.util.List;

public class Revista implements RecursoDigital, Reservable, Localizable {
    private String titulo;
    private String id;
    private String numero;
    private String issn;
    private boolean reservado = false;
    private List<Usuario> listaDeEspera = new ArrayList<>();
    private String ubicacion;

    public Revista(String titulo, String id, String numero, String issn, String ubicacion) {
        this.titulo = titulo;
        this.id = id;
        this.numero = numero;
        this.issn = issn;
        this.ubicacion = ubicacion;
    }

    @Override
    public String getTitulo() {
        return titulo;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void mostrarDetalles() {
        System.out.println("Revista: " + titulo + " (ID: " + id + ")");
        System.out.println("  Número: " + numero);
        System.out.println("  ISSN: " + issn);
        System.out.println("  Ubicación: " + ubicacion);
        System.out.println("  Reservado: " + (reservado ? "Sí" : "No"));
        if (!listaDeEspera.isEmpty()) {
            System.out.println("  Lista de espera: " + listaDeEspera.stream().map(Usuario::getNombre).toList());
        }
    }

    @Override
    public void reservar(Usuario usuario) {
        if (!reservado) {
            this.reservado = true;
            this.listaDeEspera.add(usuario);
            System.out.println("Revista '" + getTitulo() + "' reservada por " + usuario.getNombre() + ".");
        } else {
            this.listaDeEspera.add(usuario);
            System.out.println("Revista '" + getTitulo() + "' añadida a la lista de espera para " + usuario.getNombre() + ".");
        }
    }

    @Override
    public void cancelarReserva(Usuario usuario) {
        if (listaDeEspera.remove(usuario)) {
            if (listaDeEspera.isEmpty()) {
                this.reservado = false;
            }
            System.out.println("Reserva para la revista '" + getTitulo() + "' cancelada por " + usuario.getNombre() + ".");
        } else {
            System.out.println("El usuario '" + usuario.getNombre() + "' no tenía una reserva para esta revista.");
        }
    }

    @Override
    public boolean estaReservado() {
        return reservado || !listaDeEspera.isEmpty();
    }

    @Override
    public List<Usuario> getListaDeEspera() {
        return listaDeEspera;
    }

    @Override
    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }
}