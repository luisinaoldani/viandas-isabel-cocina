package servlets;

import entities.Mayorista;
import logic.MayoristaService;
import java.io.IOException;
import java.util.LinkedList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value = {"/Mayorista", "/MAYORISTA", "/mayorista"})
public class MayoristaServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private MayoristaService service = new MayoristaService();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String action = request.getParameter("action");
		String error = request.getParameter("error");

		try {

			if (action == null) {

				LinkedList<Mayorista> lista = service.listar();
				request.setAttribute("listaMayoristas", lista);
				if (error != null) {
					request.setAttribute("error", error);
				}
				request.getRequestDispatcher("/WEB-INF/jsp/mayorista/listar.jsp").forward(request, response);

			} else if (action.equals("new")) {

				request.getRequestDispatcher("/WEB-INF/jsp/mayorista/formulario.jsp").forward(request, response);

			} else if (action.equals("edit")) {

				int idUsuario = Integer.parseInt(request.getParameter("idUsuario"));
				Mayorista mayorista = service.buscarPorId(idUsuario);
				request.setAttribute("mayorista", mayorista);
				request.getRequestDispatcher("/WEB-INF/jsp/mayorista/formulario.jsp").forward(request, response);
			}

		} catch (RuntimeException e) {

			request.setAttribute("error", e.getMessage());
			request.setAttribute("listaMayoristas", new LinkedList<Mayorista>());
			request.getRequestDispatcher("/WEB-INF/jsp/mayorista/listar.jsp").forward(request, response);
		}
	}
}
