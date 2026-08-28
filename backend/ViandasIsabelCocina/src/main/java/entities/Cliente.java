package entities;

public abstract class Cliente extends Usuario {
	private String cuit;
	private String nombreNegocio;
	private String telefono;
	private String domicilio;
	
	public String getCuit() {
		return cuit;
	}
	public void setCuit(String cuit) {
		this.cuit = cuit;
	}
	public String getNombreNegocio() {
		return nombreNegocio;
	}
	public void setNombreNegocio(String nombreNegocio) {
		this.nombreNegocio = nombreNegocio;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getDomicilio() {
		return domicilio;
	}
	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}
	
	public Cliente() {
	}
	
	public Cliente(String nombre, String apellido, String email, String password, String cuit,
			String nombreNegocio, String telefono, String domicilio) {
		
        super(nombre, apellido, email, password);
        this.cuit = cuit;
        this.nombreNegocio = nombreNegocio;
        this.telefono = telefono;
        this.domicilio = domicilio;
        
    }
}
