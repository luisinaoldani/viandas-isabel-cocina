package servlets;

import data.DataVianda;
import entities.Vianda;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value = {"/ViandaProcesar", "/viandaprocesar", "/VIANDAPROCESAR", "/viandaProcesar", "/Viandaprocesar"})
public class ViandaProcesar extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        DataVianda dv = new DataVianda();
        String accion = request.getParameter("accion");

        if (accion != null && accion.equals("eliminar")) {

            int id = Integer.parseInt(request.getParameter("idVianda"));
            Vianda vianda = dv.getById(id);
            dv.deleteById(vianda);

        } else {

            String id = request.getParameter("idVianda");
            String nombre = request.getParameter("nombre");
            String descripcion = request.getParameter("descripcion");
            Double precioUnitario = Double.parseDouble(request.getParameter("precioUnitario"));
            String tipo = request.getParameter("tipo");
            boolean activa = Boolean.parseBoolean(request.getParameter("activa"));

            if (id == null || id.isEmpty()) {

                Vianda nueva = new Vianda(nombre, descripcion, precioUnitario, tipo, activa);
                dv.setVianda(nueva);

            } else {

                int idV = Integer.parseInt(id);
                Vianda existe = dv.getById(idV);
                existe.setNombre(nombre);
                existe.setDescripcion(descripcion);
                existe.setPrecioUnitario(precioUnitario);
                existe.setTipo(tipo);
                existe.setActiva(activa);
                dv.updateById(existe);
            }
        }

        response.sendRedirect("vianda");
    }
}