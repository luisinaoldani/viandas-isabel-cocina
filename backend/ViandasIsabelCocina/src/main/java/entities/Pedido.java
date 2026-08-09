package entities;

import java.time.LocalDate;
import java.util.LinkedList;

public class Pedido {
	private int numero;
	private LocalDate fechaRealizado;
	private LocalDate fechaEntrega;
	private LocalDate fechaCancelacion;
	private String estado;

	private LinkedList<DetallePedido> detalles = new LinkedList<>();

	public int getNumero() {
		return numero;
	}
	public void setNumero(int numero) {
		this.numero = numero;
	}
	public LocalDate getFechaRealizado() {
		return fechaRealizado;
	}
	public void setFechaRealizado(LocalDate fechaRealizado) {
		this.fechaRealizado = fechaRealizado;
	}
	public LocalDate getFechaEntrega() {
		return fechaEntrega;
	}
	public void setFechaEntrega(LocalDate fechaEntrega) {
		this.fechaEntrega = fechaEntrega;
	}
	public LocalDate getFechaCancelacion() {
		return fechaCancelacion;
	}
	public void setFechaCancelacion(LocalDate fechaCancelacion) {
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

	public Pedido(LocalDate fechaEntrega) {
	    if (fechaEntrega == null) {
	        throw new IllegalArgumentException("La fecha de entrega es obligatoria");
	    }
	    this.detalles = new LinkedList<>();
	    this.fechaRealizado = LocalDate.now();
	    this.fechaEntrega = fechaEntrega;
	    this.estado = "PENDIENTE";
	}
}