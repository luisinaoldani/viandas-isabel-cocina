package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import data.DataIngrediente;
import entities.Ingrediente;

@WebServlet("/test-ingrediente")
public class TestIngredienteCrudServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        out.println("<html><body style='font-family: Arial, sans-serif; margin: 30px;'>");
        out.println("<h1>🔬 Test Secuencial del CRUD - Entidad Ingrediente</h1>");
        out.println("<hr>");

        DataIngrediente di = new DataIngrediente();

        try {
            out.println("<h3>1. Ejecutando LECTURA inicial (getAll)...</h3>");
            LinkedList<Ingrediente> listaInicial = di.getAll();
            out.println("<p>Cantidad de ingredientes registrados actualmente: <strong>" + listaInicial.size() + "</strong></p>");

            out.println("<h3>2. Ejecutando CREACIÓN (setIngrediente)...</h3>");
            Ingrediente nuevo = new Ingrediente("TEST01", "Ingrediente de Prueba", 100.0, "kg");
            di.setIngrediente(nuevo);
            out.println("<p style='color: blue;'>✔ Ingrediente creado.</p>");

            LinkedList<Ingrediente> listaPostAlta = di.getAll();
            out.println("<p>Cantidad tras el alta: <strong>" + listaPostAlta.size() + "</strong></p>");

            out.println("<h3>3. Ejecutando LECTURA por codigo (getById)...</h3>");
            Ingrediente guardado = di.getById("TEST01");
            out.println("<p>Ingrediente encontrado: <strong>" + guardado.getNombre() + "</strong></p>");

            out.println("<h3>4. Ejecutando MODIFICACIÓN (updateByCodigo)...</h3>");
            guardado.setNombre("Ingrediente MODIFICADO");
            guardado.setStock(200.0);
            di.updateByCodigo(guardado);
            out.println("<p style='color: orange;'>✔ Ingrediente modificado.</p>");

            out.println("<h3>5. Ejecutando ELIMINACIÓN (deleteByCodigo)...</h3>");
            di.deleteByCodigo(guardado);
            out.println("<p style='color: purple;'>✔ Ingrediente eliminado.</p>");

            LinkedList<Ingrediente> listaFinal = di.getAll();
            out.println("<p>Cantidad al finalizar: <strong>" + listaFinal.size() + "</strong></p>");

            out.println("<hr>");
            out.println("<h2 style='color: green;'>🎉 ¡TEST COMPLETADO CON ÉXITO!</h2>");

        } catch (Exception e) {
            out.println("<hr>");
            out.println("<h2 style='color: red;'>❌ El test falló:</h2>");
            out.println("<p>" + e.getMessage() + "</p>");
            out.println("<pre style='background: #f4f4f4; padding: 10px;'>");
            e.printStackTrace(out);
            out.println("</pre>");
            e.printStackTrace();
        }

        out.println("</body></html>");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}