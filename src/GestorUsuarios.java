package src;

import java.util.HashMap;
import java.util.Map;

public class GestorUsuarios {
    private Map<String, Usuario> usuarios;

    public GestorUsuarios() {
        this.usuarios = new HashMap<>();
    }

    public void agregarUsuario(Usuario usuario) {
        this.usuarios.put(usuario.getId(), usuario);
    }

    public Usuario obtenerUsuario(String id) {
        return usuarios.get(id);
    }

    public void eliminarUsuario(String id) {
        this.usuarios.remove(id);
    }
}