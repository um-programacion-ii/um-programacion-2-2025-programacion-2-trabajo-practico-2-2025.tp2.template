package src;

import java.util.List;

public interface Reservable {
    void reservar(Usuario usuario);
    void cancelarReserva(Usuario usuario);
    boolean estaReservado();
    List<Usuario> getListaDeEspera();
}