package entities;

import java.util.Date;
import java.util.LinkedList;

public class Pedido {
	private int numero;
	private Date fechaRealizado;
	private Date fechaEntrega;
	private Date fechaCancelacion;
	private String estado;

	private LinkedList<DetallePedido> detalles = new LinkedList<>();

	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public Date getFechaRealizado() {
		return fechaRealizado;
	}
	public void setFechaRealizado(Date fechaRealizado) {
		this.fechaRealizado = fechaRealizado;
	}
	public Date getFechaEntrega() {
		return fechaEntrega;
	}
	public void setFechaEntrega(Date fechaEntrega) {
		this.fechaEntrega = fechaEntrega;
	}
	public Date getFechaCancelacion() {
		return fechaCancelacion;
	}
	public void setFechaCancelacion(Date fechaCancelacion) {
		this.fechaCancelacion = fechaCancelacion;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
	}

	public LinkedList<DetallePedido> getDetalles() {
		return detalles;
	}
	public void setDetalles(LinkedList<DetallePedido> detalles) {
		this.detalles = detalles;
	}

	// Atributo derivado, se calcula sumando los subtotales de cada detalle
	public Double getPrecioTotal() {
		Double total = 0.0;
		for (DetallePedido d : detalles) {
			total += d.getSubtotal();
		}
		return total;
	}

	public void agregarDetalle(Vianda vianda, int cantidad) {
		DetallePedido nuevo = new DetallePedido(vianda, cantidad);
		this.detalles.add(nuevo);
	}

	public Pedido() {
		this.detalles = new LinkedList<>();
	}

	public Pedido(Date fechaEntrega) {
	    if (fechaEntrega == null) {
	        throw new IllegalArgumentException("La fecha de entrega es obligatoria");
	    }
	    this.detalles = new LinkedList<>();
	    this.fechaRealizado = new Date();
	    this.fechaEntrega = fechaEntrega;
	    this.estado = "PENDIENTE";
	}
}