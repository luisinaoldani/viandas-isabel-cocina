package servlets;

import entities.Vianda;
import logic.ViandaService;
import java.io.IOException;
import java.util.LinkedList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value = {"/Vianda", "/VIANDA", "/vianda"})
public class ViandaServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private ViandaService service = new ViandaService();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action");

        if (action == null) {

            LinkedList<Vianda> lista = service.listar();
            request.setAttribute("listaViandas", lista);
            request.getRequestDispatcher("/WEB-INF/jsp/vianda/listar.jsp").forward(request, response);

        } else if (action.equals("new")) {

            request.getRequestDispatcher("/WEB-INF/jsp/vianda/formulario.jsp").forward(request, response);

        } else if (action.equals("edit")) {

            int idVianda = Integer.parseInt(request.getParameter("idVianda"));
            Vianda vianda = service.buscarPorId(idVianda);
            request.setAttribute("vianda", vianda);
            request.getRequestDispatcher("/WEB-INF/jsp/vianda/formulario.jsp").forward(request, response);
            
        } else if (action.equals("detalle")) {
        	
        	int idVianda = Integer.parseInt(request.getParameter("idVianda"));
            Vianda vianda = service.detalle(idVianda);
            request.setAttribute("vianda", vianda);
            request.getRequestDispatcher("/WEB-INF/jsp/vianda/detalle.jsp").forward(request, response);
            
        }
    }
}
