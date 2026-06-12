package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import data.DbConnector;

@WebServlet("/test-db")
public class TestDbServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public TestDbServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        out.println("<html><body>");
        out.println("<h2>Test de Conexión a Base de Datos</h2>");

        try {
            Connection conn = DbConnector.getInstancia().getConn();
            
            if (conn != null && !conn.isClosed()) {
                out.println("<h3 style='color: green;'>¡Éxito! Conexión establecida a la base de datos 'viandas_isabel_cocina'.</h3>");
                
                DbConnector.getInstancia().releaseConn();
            } else {
                out.println("<h3 style='color: orange;'>Fallo: La conexión es nula o se cerró inesperadamente.</h3>");
            }
            
        } catch (Exception e) {
            out.println("<h3 style='color: red;'>Error crítico durante la conexión:</h3>");
            out.println("<pre>" + e.getMessage() + "</pre>");
            out.println("<p>Revisa la consola de Eclipse para ver el StackTrace completo.</p>");
            
            e.printStackTrace(); 
        }

        out.println("</body></html>");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}