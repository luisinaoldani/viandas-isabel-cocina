package servlets;

import logic.IngredienteService;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value = {"/IngredienteProcesar", "/ingredienteprocesar", "/ingredientProcesar", "/Ingredienteprocesar", "/INGREDIENTEPROCESAR"})
public class IngredienteProcesarServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private IngredienteService service = new IngredienteService();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String accion = request.getParameter("accion");

        if (accion != null && accion.equals("eliminar")) {

        	int idIngrediente = Integer.parseInt(request.getParameter("idIngrediente"));
            service.eliminar(idIngrediente);

        } else {
        	
        	int idIngrediente = Integer.parseInt(request.getParameter("idIngrediente"));
            String codigo = request.getParameter("codigo");
            String nombre = request.getParameter("nombre");
            Double stock = Double.parseDouble(request.getParameter("stock"));
            String unidadMedida = request.getParameter("unidadMedida");

            service.guardar(idIngrediente, codigo, nombre, stock, unidadMedida);
        }

        response.sendRedirect("ingrediente");
    }
}