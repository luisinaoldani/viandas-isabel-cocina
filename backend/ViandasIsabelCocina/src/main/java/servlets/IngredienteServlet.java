package servlets;

import data.DataIngrediente;
import entities.Ingrediente;
import java.io.IOException;
import java.util.LinkedList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value = {"/ingrediente", "/INGREDIENTE", "/Ingrediente"})
public class IngredienteServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action");
        DataIngrediente di = new DataIngrediente();

        if (action == null) {

            LinkedList lista = di.getAll();
            request.setAttribute("listaIngredientes", (Object) lista);
            RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/ingrediente/listar.jsp");
            rd.forward((ServletRequest) request, (ServletResponse) response);

        } else if (action.equals("new")) {

            RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/ingrediente/formulario.jsp");
            rd.forward((ServletRequest) request, (ServletResponse) response);

        } else if (action.equals("edit")) {

            String codigo = request.getParameter("codigo");
            Ingrediente ingrediente = di.getById(codigo);
            request.setAttribute("ingrediente", (Object) ingrediente);
            RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/ingrediente/formulario.jsp");
            rd.forward((ServletRequest) request, (ServletResponse) response);
        }
    }
}