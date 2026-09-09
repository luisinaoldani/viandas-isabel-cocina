package entities;

public class Administrador extends Usuario {

    public Administrador() {
	}

    public Administrador(String nombre, String apellido, String email, String password, String rol) {
        super(nombre, apellido, email, password, rol);
    }
}
