package src;

import java.util.ArrayList;
import java.util.List;

public class Libro implements RecursoDigital, Prestable, Reservable, Localizable {
    private String titulo;
    private String id;
    private String autor;
    private String isbn;
    private boolean prestado = false;
    private boolean reservado = false;
    private List<Usuario> listaDeEspera = new ArrayList<>();
    private String ubicacion; // Nuevo atributo para la ubicación

    public Libro(String titulo, String id, String autor, String isbn, String ubicacion) {
        this.titulo = titulo;
        this.id = id;
        this.autor = autor;
        this.isbn = isbn;
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
        System.out.println("Libro: " + titulo + " (ID: " + id + ")");
        System.out.println("  Autor: " + autor);
        System.out.println("  ISBN: " + isbn);
        System.out.println("  Ubicación: " + ubicacion);
        System.out.println("  Prestado: " + (prestado ? "Sí" : "No"));
        System.out.println("  Reservado: " + (reservado ? "Sí" : "No"));
        if (!listaDeEspera.isEmpty()) {
            System.out.println("  Lista de espera: " + listaDeEspera.stream().map(Usuario::getNombre).toList());
        }
    }

    @Override
    public void prestar(Usuario usuario) {
        this.prestado = true;
        this.reservado = false; // Al prestar, se libera cualquier reserva
        System.out.println("Libro '" + getTitulo() + "' prestado a " + usuario.getNombre() + ".");
    }

    @Override
    public void devolver() {
        this.prestado = false;
        System.out.println("Libro '" + getTitulo() + "' devuelto.");
        // Aquí podríamos implementar lógica para notificar al siguiente en la lista de espera
    }

    @Override
    public boolean estaPrestado() {
        return prestado;
    }

    @Override
    public void reservar(Usuario usuario) {
        if (!prestado && !reservado) {
            this.reservado = true;
            this.listaDeEspera.add(usuario);
            System.out.println("Libro '" + getTitulo() + "' reservado por " + usuario.getNombre() + ".");
        } else if (prestado) {
            this.listaDeEspera.add(usuario);
            System.out.println("Libro '" + getTitulo() + "' añadido a la lista de espera para " + usuario.getNombre() + ".");
        } else {
            System.out.println("El libro '" + getTitulo() + "' ya está reservado.");
        }
    }

    @Override
    public void cancelarReserva(Usuario usuario) {
        if (listaDeEspera.remove(usuario)) {
            if (listaDeEspera.isEmpty()) {
                this.reservado = false;
            }
            System.out.println("Reserva para el libro '" + getTitulo() + "' cancelada por " + usuario.getNombre() + ".");
        } else {
            System.out.println("El usuario '" + usuario.getNombre() + "' no tenía una reserva para este libro.");
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

    // Setter para la ubicación (podría ser útil al agregar el libro)
    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }
}