package servlets;

import logic.AdministradorService;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value = {"/AdministradorProcesar", "/administradorprocesar", "/ADMINISTRADORPROCESAR", "/administradorProcesar", "/Administradorprocesar"})
public class AdministradorProcesar extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private AdministradorService service = new AdministradorService();

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String accion = request.getParameter("accion");

		try {

			if (accion != null && accion.equals("eliminar")) {

				int idAdministrador = Integer.parseInt(request.getParameter("idAdministrador"));
				service.eliminar(idAdministrador);

			} else {

				String id = request.getParameter("idAdministrador");
				String nombre = request.getParameter("nombre");
				String apellido = request.getParameter("apellido");
				String email = request.getParameter("email");
				String password = request.getParameter("password");

				service.guardar(id, nombre, apellido, email, password);
			}

			response.sendRedirect("administrador");

		} catch (RuntimeException e) {

			response.sendRedirect("administrador?error=" + encodar(e.getMessage()));
		}
	}

	private String encodar(String mensaje) {
		try {
			return URLEncoder.encode(mensaje, StandardCharsets.UTF_8.toString());
		} catch (UnsupportedEncodingException e) {
			return "Ocurrio un error inesperado.";
		}
	}
}
