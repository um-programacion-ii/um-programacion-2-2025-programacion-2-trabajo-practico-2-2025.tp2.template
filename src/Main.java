import gestor.GestorUsuarios;
import modelo.usuario.Usuario;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        Usuario usuario = new Usuario("1","Emiliano","emi@");
        // Imprimir datos del usuario
        System.out.println("=== Información del Usuario ===");
        System.out.println("Nombre: " + usuario.getNombre());
        System.out.println("ID: " + usuario.getId());
        System.out.println("Email: " + usuario.getEmail());
        //probando gestor
        GestorUsuarios gestorUsuarios = new GestorUsuarios();
        gestorUsuarios.registrarUsuario(usuario);
        System.out.println("Usuario registrado: " + gestorUsuarios.buscarUsuarioPorId("1"));
    }
}