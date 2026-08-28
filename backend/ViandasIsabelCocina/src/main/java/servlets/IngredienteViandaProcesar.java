package servlets;

import logic.IngredienteViandaService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet({ "/IngredienteViandaProcesar", "/IngredienteViandaprocesar", "/Ingredienteviandaprocesar", "/ingredienteviandaprocesar", "/ingredienteViandaProcesar", "/INGREDIENTEVIANDAPROCESAR", "/IngredienteviandaProcesar" })
public class IngredienteViandaProcesar extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private IngredienteViandaService service = new IngredienteViandaService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String accion = request.getParameter("accion");

        if (accion != null && accion.equals("eliminar")) {

            int idVianda = Integer.parseInt(request.getParameter("idVianda"));
            int idIngrediente = Integer.parseInt(request.getParameter("idIngrediente"));
            service.eliminar(idIngrediente, idVianda);

        } else {

        	int idIngrediente = Integer.parseInt(request.getParameter("idIngrediente"));
            int idVianda = Integer.parseInt(request.getParameter("idVianda"));
            Double cantidad = Double.parseDouble(request.getParameter("cantidad"));

            service.guardar(idIngrediente, idVianda, cantidad);
        }

        response.sendRedirect("ingredienteVianda");
    }
}
