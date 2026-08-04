package servlets;

import entities.Ingrediente;
import entities.IngredienteVianda;
import entities.Vianda;
import logic.IngredienteViandaService;
import java.io.IOException;
import java.util.LinkedList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet({ "/IngredienteViandaServlet", "/ingredienteVianda", "/ingredientevianda", "/Ingredientevianda", "/INGREDIENTEVIANDA" })
public class IngredienteViandaServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private IngredienteViandaService service = new IngredienteViandaService();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action");

        if (action == null) {

            LinkedList<IngredienteVianda> lista = service.listar();
            request.setAttribute("listaIngredienteVianda", lista);
            request.getRequestDispatcher("/WEB-INF/jsp/ingredienteVianda/listar.jsp").forward(request, response);

        } else if (action.equals("new")) {

            LinkedList<Vianda> listaViandas = service.listarViandas();
            LinkedList<Ingrediente> listaIngredientes = service.listarIngredientes();
            request.setAttribute("listaViandas", listaViandas);
            request.setAttribute("listaIngredientes", listaIngredientes);
            request.getRequestDispatcher("/WEB-INF/jsp/ingredienteVianda/formulario.jsp").forward(request, response);

        } else if (action.equals("edit")) {

            String codigoIngrediente = request.getParameter("codigoIngrediente");
            int idVianda = Integer.parseInt(request.getParameter("idVianda"));

            IngredienteVianda ingredienteVianda = service.buscarPorId(codigoIngrediente, idVianda);
            request.setAttribute("ingredienteVianda", ingredienteVianda);

            LinkedList<Vianda> listaViandas = service.listarViandas();
            LinkedList<Ingrediente> listaIngredientes = service.listarIngredientes();
            request.setAttribute("listaViandas", listaViandas);
            request.setAttribute("listaIngredientes", listaIngredientes);

            request.getRequestDispatcher("/WEB-INF/jsp/ingredienteVianda/formulario.jsp").forward(request, response);
        }
    }
}