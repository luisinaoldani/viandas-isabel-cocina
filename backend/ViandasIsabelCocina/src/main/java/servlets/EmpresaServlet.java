package servlets;

import entities.Empresa;
import logic.EmpresaService;
import java.io.IOException;
import java.util.LinkedList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value = {"/Empresa", "/EMPRESA", "/empresa"})
public class EmpresaServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private EmpresaService service = new EmpresaService();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String action = request.getParameter("action");
		String error = request.getParameter("error");

		try {

			if (action == null) {

				LinkedList<Empresa> lista = service.listar();
				request.setAttribute("listaEmpresas", lista);
				if (error != null) {
					request.setAttribute("error", error);
				}
				request.getRequestDispatcher("/WEB-INF/jsp/empresa/listar.jsp").forward(request, response);

			} else if (action.equals("new")) {

				request.getRequestDispatcher("/WEB-INF/jsp/empresa/formulario.jsp").forward(request, response);

			} else if (action.equals("edit")) {

				int idUsuario = Integer.parseInt(request.getParameter("idUsuario"));
				Empresa empresa = service.buscarPorId(idUsuario);
				request.setAttribute("empresa", empresa);
				request.getRequestDispatcher("/WEB-INF/jsp/empresa/formulario.jsp").forward(request, response);
			}

		} catch (RuntimeException e) {

			request.setAttribute("error", e.getMessage());
			request.setAttribute("listaEmpresas", new LinkedList<Empresa>());
			request.getRequestDispatcher("/WEB-INF/jsp/empresa/listar.jsp").forward(request, response);
		}
	}
}
