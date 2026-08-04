package servlets;

import entities.Ingrediente;
import logic.IngredienteService;
import java.io.IOException;
import java.util.LinkedList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value = {"/ingrediente", "/INGREDIENTE", "/Ingrediente"})
public class IngredienteServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private IngredienteService service = new IngredienteService();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action");

        if (action == null) {

            LinkedList<Ingrediente> lista = service.listar();
            request.setAttribute("listaIngredientes", lista);
            request.getRequestDispatcher("/WEB-INF/jsp/ingrediente/listar.jsp").forward(request, response);

        } else if (action.equals("new")) {

            request.getRequestDispatcher("/WEB-INF/jsp/ingrediente/formulario.jsp").forward(request, response);

        } else if (action.equals("edit")) {

            String codigo = request.getParameter("codigo");
            Ingrediente ingrediente = service.buscarPorCodigo(codigo);
            request.setAttribute("ingrediente", ingrediente);
            request.getRequestDispatcher("/WEB-INF/jsp/ingrediente/formulario.jsp").forward(request, response);
        }
    }
}