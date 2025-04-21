package src;

import java.util.HashMap;
import java.util.Map;

public class GestorUsuarios {
    private Map<Integer, Usuario> usuarios;

    public GestorUsuarios() {
        this.usuarios = new HashMap<>();

    }
    public void agregarUsuario(Usuario usuario) {
        usuarios.put(usuario.id, usuario);

    }
    public Usuario obtenerUsuario(int id) {
        return usuarios.get(id);
    }
    public void actualizarUsuario(Usuario usuario) {
        if (usuarios.containsKey(usuario.id)) {
            usuarios.put(usuario.id, usuario);
        }
    }

    public void eliminarUsuario(int id) {
        usuarios.remove(id);
    }

}
