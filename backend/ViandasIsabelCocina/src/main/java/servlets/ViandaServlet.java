package servlets;

import data.DataVianda;
import entities.Vianda;
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

@WebServlet(value = {"/Vianda", "/VIANDA", "/vianda"})
public class ViandaServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action");
        DataVianda dv = new DataVianda();

        if (action == null) {

            LinkedList lista = dv.getAll();
            request.setAttribute("listaViandas", (Object) lista);
            RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/vianda/listar.jsp");
            rd.forward((ServletRequest) request, (ServletResponse) response);

        } else if (action.equals("new")) {

            RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/vianda/formulario.jsp");
            rd.forward((ServletRequest) request, (ServletResponse) response);

        } else if (action.equals("edit")) {

            String idVianda = request.getParameter("idVianda");
            Vianda vianda = dv.getById(Integer.parseInt(idVianda));
            request.setAttribute("vianda", (Object) vianda);
            RequestDispatcher rd = request.getRequestDispatcher("/WEB-INF/jsp/vianda/formulario.jsp");
            rd.forward((ServletRequest) request, (ServletResponse) response);
        }
    }
}