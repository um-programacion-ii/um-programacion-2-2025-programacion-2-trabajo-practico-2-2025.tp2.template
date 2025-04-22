package src;

import java.util.ArrayList;
import java.util.List;

public class Audiolibro implements RecursoDigital, Prestable, Reservable, Localizable {
    private String titulo;
    private String id;
    private String narrador;
    private String duracion;
    private boolean prestado = false;
    private boolean reservado = false;
    private List<Usuario> listaDeEspera = new ArrayList<>();
    private String ubicacion;
    private ServicioNotificaciones servicioNotificaciones; // Dependencia abstracta

    public Audiolibro(String titulo, String id, String narrador, String duracion, String ubicacion, ServicioNotificaciones servicioNotificaciones) {
        this.titulo = titulo;
        this.id = id;
        this.narrador = narrador;
        this.duracion = duracion;
        this.ubicacion = ubicacion;
        this.servicioNotificaciones = servicioNotificaciones; // Inyección por constructor
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
        System.out.println("Audiolibro: " + titulo + " (ID: " + id + ")");
        System.out.println("  Narrador: " + narrador);
        System.out.println("  Duración: " + duracion);
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
        this.reservado = false;
        System.out.println("Audiolibro '" + getTitulo() + "' prestado a " + usuario.getNombre() + ".");
    }

    @Override
    public void devolver() {
        this.prestado = false;
        System.out.println("Audiolibro '" + getTitulo() + "' devuelto.");
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
            System.out.println("Audiolibro '" + getTitulo() + "' reservado por " + usuario.getNombre() + ".");
            if (servicioNotificaciones != null) {
                servicioNotificaciones.enviarNotificaciones(usuario, "El audiolibro '" + getTitulo() + "' ha sido reservado exitosamente.");
            }
        } else if (prestado) {
            this.listaDeEspera.add(usuario);
            System.out.println("Audiolibro '" + getTitulo() + "' añadido a la lista de espera para " + usuario.getNombre() + ".");
        } else {
            System.out.println("El audiolibro '" + getTitulo() + "' ya está reservado.");
        }
    }

    @Override
    public void cancelarReserva(Usuario usuario) {
        if (listaDeEspera.remove(usuario)) {
            if (listaDeEspera.isEmpty()) {
                this.reservado = false;
            }
            System.out.println("Reserva para el audiolibro '" + getTitulo() + "' cancelada por " + usuario.getNombre() + ".");
        } else {
            System.out.println("El usuario '" + usuario.getNombre() + "' no tenía una reserva para este audiolibro.");
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