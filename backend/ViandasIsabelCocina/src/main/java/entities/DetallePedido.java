package entities;

public class DetallePedido {
	private int cantidad;
	private Vianda vianda;

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public Vianda getVianda() {
		return vianda;
	}

	public void setVianda(Vianda vianda) {
		this.vianda = vianda;
	}
	
	public Double getSubtotal() {
		Double subtotal = 0.0;
		subtotal += this.getCantidad() * this.getVianda().getPrecioUnitario();
		return subtotal;
	}
	
	public DetallePedido() {
	}
	
	public DetallePedido(Vianda vianda, int cantidad) {
		this.vianda = vianda;
		this.cantidad = cantidad;
	}

}
