package entities;

public class Administrador extends Usuario {

    private String rol;

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
    
    public Administrador() {
	};

    public Administrador(String nombre, String apellido, String email, String password, String rol) {
        super(nombre, apellido, email, password);
        this.rol = rol;
    }
}
