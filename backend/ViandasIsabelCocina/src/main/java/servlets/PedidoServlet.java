package servlets;

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

		LinkedList<Vianda> listaViandas = service.listarViandasActivas();
		request.setAttribute("listaViandas", listaViandas);
		request.getRequestDispatcher("/WEB-INF/jsp/pedido/formulario.jsp").forward(request, response);
	}
}
