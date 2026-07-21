package servlets;

import data.DataIngrediente;
import entities.Ingrediente;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(value = {"/IngredienteProcesar", "/ingredienteprocesar", "/ingredientProcesar", "/Ingredienteprocesar", "/INGREDIENTEPROCESAR"})
public class IngredienteProcesarServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        DataIngrediente di = new DataIngrediente();
        String accion = request.getParameter("accion");

        if (accion != null && accion.equals("eliminar")) {

            String codigo = request.getParameter("codigo");
            Ingrediente ingrediente = di.getById(codigo);
            di.deleteByCodigo(ingrediente);

        } else {

            String codigo = request.getParameter("codigo");
            String nombre = request.getParameter("nombre");
            Double stock = Double.parseDouble(request.getParameter("stock"));
            String unidadMedida = request.getParameter("unidadMedida");

            Ingrediente existente = di.getById(codigo);

            if (existente == null) {

                Ingrediente nuevo = new Ingrediente(codigo, nombre, stock, unidadMedida);
                di.setIngrediente(nuevo);

            } else {

                existente.setNombre(nombre);
                existente.setStock(stock);
                existente.setUnidadMedida(unidadMedida);
                di.updateByCodigo(existente);
            }
        }

        response.sendRedirect("ingrediente");
    }
}