package servlets;

import entities.Administrador;
import logic.AdministradorService;
import java.io.IOException;
import java.util.LinkedList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value = {"/Administrador", "/ADMINISTRADOR", "/administrador"})
public class AdministradorServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private AdministradorService service = new AdministradorService();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String action = request.getParameter("action");
		String error = request.getParameter("error");

		try {

			if (action == null) {

				LinkedList<Administrador> lista = service.listar();
				request.setAttribute("listaAdministradores", lista);
				if (error != null) {
					request.setAttribute("error", error);
				}
				request.getRequestDispatcher("/WEB-INF/jsp/administrador/listar.jsp").forward(request, response);

			} else if (action.equals("new")) {

				request.getRequestDispatcher("/WEB-INF/jsp/administrador/formulario.jsp").forward(request, response);

			} else if (action.equals("edit")) {

				int idAdmin = Integer.parseInt(request.getParameter("idAdministrador"));
				Administrador admin = service.buscarPorId(idAdmin);
				request.setAttribute("administrador", admin);
				request.getRequestDispatcher("/WEB-INF/jsp/administrador/formulario.jsp").forward(request, response);
			}

		} catch (RuntimeException e) {

			request.setAttribute("error", e.getMessage());
			request.setAttribute("listaAdministradores", new LinkedList<Administrador>());
			request.getRequestDispatcher("/WEB-INF/jsp/administrador/listar.jsp").forward(request, response);
		}
	}
}
