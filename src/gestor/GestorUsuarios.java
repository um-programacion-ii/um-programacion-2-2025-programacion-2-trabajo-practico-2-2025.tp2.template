package gestor;

import modelo.usuario.Usuario;
import java.util.HashMap;
import java.util.Map;

public class GestorUsuarios {
    private Map<String, Usuario> usuarios = new HashMap<>();

    public void registrarUsuario(Usuario usuario) {
        usuarios.put(usuario.getId(), usuario);
    }

    public Usuario buscarUsuario(String id) {
        return usuarios.get(id);
    }

    public void listarUsuarios() {
        usuarios.values().forEach(System.out::println);
    }
}
