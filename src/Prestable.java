package src;

public interface Prestable {
    void prestar(Usuario usuario);
    void devolver();
    boolean estaPrestado();
}