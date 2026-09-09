package servlets;

import logic.EmpresaService;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value = {"/EmpresaProcesar", "/empresaprocesar", "/EMPRESAPROCESAR", "/empresaProcesar", "/Empresaprocesar"})
public class EmpresaProcesar extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private EmpresaService service = new EmpresaService();

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String accion = request.getParameter("accion");

		try {

			if (accion != null && accion.equals("eliminar")) {

				int idUsuario = Integer.parseInt(request.getParameter("idUsuario"));
				service.eliminar(idUsuario);

			} else {

				String id = request.getParameter("idUsuario");
				String nombre = request.getParameter("nombre");
				String apellido = request.getParameter("apellido");
				String email = request.getParameter("email");
				String password = request.getParameter("password");
				String cuit = request.getParameter("cuit");
				String nombreNegocio = request.getParameter("nombreNegocio");
				String telefono = request.getParameter("telefono");
				String domicilio = request.getParameter("domicilio");
				String diaPedidoSemanal = request.getParameter("diaPedidoSemanal");
				int cantEmpleados = Integer.parseInt(request.getParameter("cantEmpleados"));

				service.guardar(id, nombre, apellido, email, password, cuit, nombreNegocio, telefono, domicilio,
						diaPedidoSemanal, cantEmpleados);
			}

			response.sendRedirect("empresa");

		} catch (RuntimeException e) {

			response.sendRedirect("empresa?error=" + encodar(e.getMessage()));
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
