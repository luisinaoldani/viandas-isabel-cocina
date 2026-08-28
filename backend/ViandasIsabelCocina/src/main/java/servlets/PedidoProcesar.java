package servlets;

import entities.Pedido;
import logic.PedidoService;
import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value = {"/PedidoProcesar", "/pedidoprocesar", "/PEDIDOPROCESAR", "/pedidoProcesar"})
public class PedidoProcesar extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private PedidoService service = new PedidoService();

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String accion = request.getParameter("accion");

		if (accion != null && accion.equals("cancelar")) {

			int numero = Integer.parseInt(request.getParameter("numero"));
			service.cancelarPedido(numero);
			response.sendRedirect("pedido");

		} else if (accion != null && accion.equals("actualizar")) {

			int numero = Integer.parseInt(request.getParameter("numero"));
			LocalDate fechaEntrega = LocalDate.parse(request.getParameter("fechaEntrega"));
			Map<Integer, Integer> cantidades = leerCantidades(request);

			service.actualizarPedido(numero, fechaEntrega, cantidades);
			response.sendRedirect("pedido?action=detalle&numero=" + numero);

		} else {

			LocalDate fechaEntrega = LocalDate.parse(request.getParameter("fechaEntrega"));
			Map<Integer, Integer> cantidades = leerCantidades(request);

			Pedido pedido = service.crearPedido(fechaEntrega, cantidades);
			request.setAttribute("pedido", pedido);
			request.getRequestDispatcher("/WEB-INF/jsp/pedido/confirmacion.jsp").forward(request, response);
		}
	}

	private Map<Integer, Integer> leerCantidades(HttpServletRequest request) {
		Map<Integer, Integer> cantidades = new HashMap<>();
		for (String nombreParametro : request.getParameterMap().keySet()) {
			if (nombreParametro.startsWith("cantidad_")) {
				String valor = request.getParameter(nombreParametro);
				if (valor != null && !valor.trim().isEmpty()) {
					int idVianda = Integer.parseInt(nombreParametro.substring("cantidad_".length()));
					int cantidad = Integer.parseInt(valor.trim());
					cantidades.put(idVianda, cantidad);
				}
			}
		}
		return cantidades;
	}

}