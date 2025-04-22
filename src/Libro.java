package src;

public class Libro implements RecursoDigital{
     private String titulo;
     private String id;
     private String autor;
     private String isbn;

     public Libro(String titulo, String id, String autor, String isbn) {
         this.titulo = titulo;
         this.id = id;
         this.autor = autor;
         this.isbn = isbn;

     }
     @Override
    public String getTitulo(){
         return titulo;
     }
     @Override
    public String getId(){
         return id;
     }
     @Override
    public void mostrarDetalles(){
         System.out.println("Libro: " + titulo + "(ID:"+ id +")");
         System.out.println("Autor: " + autor);
         System.out.println("ISBN: " + isbn);
     }
}
