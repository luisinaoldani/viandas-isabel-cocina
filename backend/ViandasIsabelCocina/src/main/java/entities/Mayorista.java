package entities;

public class Mayorista extends Cliente{
	
	public Mayorista() {
	}
	
	public Mayorista(String nombre, String apellido, String email, String password, String rol, String cuit,
			String nombreNegocio, String telefono, String domicilio) {
		
		super(nombre, apellido, email, password, rol, cuit, nombreNegocio, telefono, domicilio);
		
	}

}
