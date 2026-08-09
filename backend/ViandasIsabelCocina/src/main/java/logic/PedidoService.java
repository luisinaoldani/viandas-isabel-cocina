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

}