package servlets;

import logic.ViandaService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value = {"/ViandaProcesar", "/viandaprocesar", "/VIANDAPROCESAR", "/viandaProcesar", "/Viandaprocesar"})
public class ViandaProcesar extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private ViandaService service = new ViandaService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String accion = request.getParameter("accion");

        if (accion != null && accion.equals("eliminar")) {

            int id = Integer.parseInt(request.getParameter("idVianda"));
            service.eliminar(id);

        } else {

            String id = request.getParameter("idVianda");
            String nombre = request.getParameter("nombre");
            String descripcion = request.getParameter("descripcion");
            Double precioUnitario = Double.parseDouble(request.getParameter("precioUnitario"));
            String tipo = request.getParameter("tipo");
            boolean activa = Boolean.parseBoolean(request.getParameter("activa"));

            service.guardar(id, nombre, descripcion, precioUnitario, tipo, activa);
        }

        response.sendRedirect("vianda");
    }
}