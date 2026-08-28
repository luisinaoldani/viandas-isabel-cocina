package entities;

public class Empresa extends Cliente {
	private String diaPedidoSemanal;
	private int cantEmpleados;
	
	public String getDiaPedidoSemanal() {
		return diaPedidoSemanal;
	}
	public void setDiaPedidoSemanal(String diaPedidoSemanal) {
		this.diaPedidoSemanal = diaPedidoSemanal;
	}
	public int getCantEmpleados() {
		return cantEmpleados;
	}
	public void setCantEmpleados(int cantEmpleados) {
		this.cantEmpleados = cantEmpleados;
	}
	
	public Empresa() {
	};
	
	public Empresa(String nombre, String apellido, String email, String password, String cuit,
			String nombreNegocio, String telefono, String domicilio, String diaPedidoSemanal, int cantEmpleados) {
		
		super(nombre, apellido, email, password, cuit, nombreNegocio, telefono, domicilio);
		this.diaPedidoSemanal =  diaPedidoSemanal;
		this.cantEmpleados = cantEmpleados;
		
	}

}
