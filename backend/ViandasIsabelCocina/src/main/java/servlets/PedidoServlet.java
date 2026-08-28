package servlets;

import entities.Pedido;
import entities.Vianda;
import logic.PedidoService;
import java.io.IOException;
import java.util.LinkedList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value = {"/Pedido", "/PEDIDO", "/pedido"})
public class PedidoServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private PedidoService service = new PedidoService();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String action = request.getParameter("action");

		if (action == null) {

			LinkedList<Pedido> listaPedidos = service.listar();
			request.setAttribute("listaPedidos", listaPedidos);
			request.getRequestDispatcher("/WEB-INF/jsp/pedido/listar.jsp").forward(request, response);

		} else if (action.equals("new")) {

			LinkedList<Vianda> listaViandas = service.listarViandasActivas();
			request.setAttribute("listaViandas", listaViandas);
			request.getRequestDispatcher("/WEB-INF/jsp/pedido/formulario.jsp").forward(request, response);

		} else if (action.equals("edit")) {

			int numero = Integer.parseInt(request.getParameter("numero"));
			Pedido pedido = service.buscarPorId(numero);
			LinkedList<Vianda> listaViandas = service.listarViandasActivas();
			request.setAttribute("pedido", pedido);
			request.setAttribute("listaViandas", listaViandas);
			request.getRequestDispatcher("/WEB-INF/jsp/pedido/formulario.jsp").forward(request, response);

		} else if (action.equals("detalle")) {

			int numero = Integer.parseInt(request.getParameter("numero"));
			Pedido pedido = service.buscarPorId(numero);
			request.setAttribute("pedido", pedido);
			request.getRequestDispatcher("/WEB-INF/jsp/pedido/detalle.jsp").forward(request, response);

		}
	}
}