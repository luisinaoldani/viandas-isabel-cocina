package entities;

public class Mayorista extends Cliente{
	
	public Mayorista() {
	};
	
	public Mayorista(String nombre, String apellido, String email, String password, String cuit,
			String nombreNegocio, String telefono, String domicilio) {
		
		super(nombre, apellido, email, password, cuit, nombreNegocio, telefono, domicilio);
		
	}

}
