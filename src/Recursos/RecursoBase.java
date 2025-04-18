package Recursos;
import Interfaces.*;
import Recursos.*;
import Servicios.*;
import Interaccion.*;
import Usuarios.*;
import Interfaces.Prestable;
import Interfaces.RecursoDigital;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class RecursoBase implements RecursoDigital, Prestable {
    static int contadorRecursos = 1;
    private int idRecursoBase;
    private String titulo;
    private String autor;
    private EstadoRecurso estado;
    private List<RecursoBase> Recursos = new ArrayList<>();
    private LocalDateTime fechaDevolucion;

    public RecursoBase(String titulo, String autor, EstadoRecurso estado) {
        this.idRecursoBase = contadorRecursos;
        contadorRecursos++;
        this.titulo = titulo;
        this.autor = autor;
        this.estado = estado;
    }


    public void agregarRecursos(){

    }
    public void setFechaDevolucion(LocalDateTime fecha) {
        this.fechaDevolucion = fecha;
    }
    public List<RecursoBase> getRecursos() {
        return Recursos;
    }

    public void setRecursos(List<RecursoBase> recursos) {
        Recursos = recursos;
    }

    public void setEstado(EstadoRecurso estado) {
        this.estado = estado;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getIdRecursoBase() {
        return idRecursoBase;
    }

    public void setIdRecursoBase(int idRecursoBase) {
        this.idRecursoBase = idRecursoBase;
    }

    public static int getContadorRecursos() {
        return contadorRecursos;
    }

    public static void setContadorRecursos(int contadorRecursos) {
        RecursoBase.contadorRecursos = contadorRecursos;
    }

    @Override
    public String toString() {
        return
                "ID de Recurso: " + idRecursoBase +
                        ", Titulo: '" + titulo + '\'' +
                        ", Autor: '" +   autor +'\'' +
                        ", Estado: '" +  estado+ '\'';

    }


    @Override
    public void mostrarInformacion() {
        System.out.println("Id Recurso Base " + idRecursoBase);
        System.out.println("Titulo " + titulo);
        System.out.println("Autor " + autor);
        System.out.println("Estado " + getEstado());
    }

    public RecursoBase() {
    }

    @Override
    public String getIdentificador() {
        return String.valueOf(idRecursoBase);
    }

    @Override
    public EstadoRecurso getEstado() {
        return estado;
    }

    @Override
    public void actualizarEstado(EstadoRecurso nuevoEstado) {
        this.estado = nuevoEstado;
    }

    @Override
    public LocalDateTime getFechaDevolucion() {
        return fechaDevolucion;
    }

    @Override
    public void prestar(Usuario usuario) {
        if (estaDisponible()) {
            setEstado(EstadoRecurso.EnPrestamo);
            this.fechaDevolucion = LocalDateTime.now().plusDays(14);
            System.out.println("Recursos.Libro prestado a " + usuario.getNombre());
        } else {
            System.out.println("No se puede prestar: estado actual = " + getEstado());
        }
    }

    @Override
    public boolean estaDisponible() {
        return getEstado() == EstadoRecurso.Disponible;
    }
}
