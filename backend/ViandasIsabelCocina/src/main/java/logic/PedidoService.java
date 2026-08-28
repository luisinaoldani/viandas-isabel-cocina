package logic;

import data.DataPedido;
import entities.Pedido;
import entities.Vianda;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.Map;

public class PedidoService {

	private DataPedido dataPedido = new DataPedido();
	private ViandaService viandaService = new ViandaService();

	public LinkedList<Vianda> listarViandasActivas() {
		LinkedList<Vianda> activas = new LinkedList<>();
		for (Vianda v : viandaService.listar()) {
			if (v.isActiva()) {
				activas.add(v);
			}
		}
		return activas;
	}

	public LinkedList<Pedido> listar() {
		return dataPedido.getAll();
	}

	public Pedido buscarPorId(int numero) {
		return dataPedido.getById(numero);
	}

	public Pedido crearPedido(LocalDate fechaEntrega, Map<Integer, Integer> cantidadesPorVianda) {

		Pedido pedido = new Pedido(fechaEntrega);

		for (Map.Entry<Integer, Integer> entry : cantidadesPorVianda.entrySet()) {
			int idVianda = entry.getKey();
			int cantidad = entry.getValue();

			if (cantidad > 0) {
				Vianda vianda = viandaService.buscarPorId(idVianda);
				pedido.agregarDetalle(vianda, cantidad);
			}
		}

		if (pedido.getDetalles().isEmpty()) {
			throw new IllegalArgumentException("Debe seleccionar al menos una vianda con cantidad mayor a 0.");
		}

		return dataPedido.setPedido(pedido);
	}

	public Pedido actualizarPedido(int numero, LocalDate fechaEntrega, Map<Integer, Integer> cantidadesPorVianda) {

		Pedido pedido = dataPedido.getById(numero);

		if (pedido == null) {
			throw new IllegalArgumentException("El pedido no existe.");
		}
		if (!"PENDIENTE".equals(pedido.getEstado())) {
			throw new IllegalArgumentException("Solo se pueden modificar pedidos PENDIENTES.");
		}
		if (fechaEntrega == null) {
			throw new IllegalArgumentException("La fecha de entrega es obligatoria");
		}

		pedido.setFechaEntrega(fechaEntrega);
		pedido.setDetalles(new LinkedList<>());

		for (Map.Entry<Integer, Integer> entry : cantidadesPorVianda.entrySet()) {
			int idVianda = entry.getKey();
			int cantidad = entry.getValue();

			if (cantidad > 0) {
				Vianda vianda = viandaService.buscarPorId(idVianda);
				pedido.agregarDetalle(vianda, cantidad);
			}
		}

		if (pedido.getDetalles().isEmpty()) {
			throw new IllegalArgumentException("Debe seleccionar al menos una vianda con cantidad mayor a 0.");
		}

		dataPedido.update(pedido);
		return pedido;
	}

	public void cancelarPedido(int numero) {
		dataPedido.cancelar(numero);
	}

}