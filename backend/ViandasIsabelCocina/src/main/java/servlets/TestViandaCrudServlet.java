package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import data.DataVianda;
import entities.Vianda;

@WebServlet("/test-vianda")
public class TestViandaCrudServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public TestViandaCrudServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter out = response.getWriter();
		
		out.println("<html><body style='font-family: Arial, sans-serif; margin: 30px;'>");
		out.println("<h1>🔬 Test Secuencial del CRUD - Entidad Vianda</h1>");
		out.println("<hr>");
		
		DataVianda dv = new DataVianda();
		
		try {
			
			out.println("<h3>1. Ejecutando LECTURA inicial (getAll)...</h3>");
			LinkedList<Vianda> listaInicial = dv.getAll();
			out.println("<p>Cantidad de viandas registradas actualmente: <strong>" + listaInicial.size() + "</strong></p>");
			
		
			out.println("<h3>2. Ejecutando CREACIÓN (setVianda)...</h3>");
			Vianda nuevaVianda = new Vianda("Vianda de Prueba", "Descripción de prueba para el flujo básico", 2500.0, "Común", true);
			
			dv.setVianda(nuevaVianda); 
			out.println("<p style='color: blue;'>✔ Vianda creada de forma temporal.</p>");
			
			LinkedList<Vianda> listaPostAlta = dv.getAll();
			out.println("<p>Cantidad de viandas tras el alta: <strong>" + listaPostAlta.size() + "</strong></p>");
			
			Vianda viandaGuardada = listaPostAlta.getLast();
			out.println("<p>ID de la vianda detectada para la prueba: <strong>" + viandaGuardada.getIdVianda() + "</strong></p>");
			
			out.println("<h3>3. Ejecutando MODIFICACIÓN (updateById)...</h3>");
			viandaGuardada.setNombre("Vianda de Prueba MODIFICADA");
			viandaGuardada.setPrecioUnitario(3100.50);
			
			dv.updateById(viandaGuardada);
			out.println("<p style='color: orange;'>✔ Vianda modificada en la base de datos.</p>");
			
			LinkedList<Vianda> listaPostUpdate = dv.getAll();
			Vianda viandaVerificada = null;
			for(Vianda v : listaPostUpdate) {
				if(v.getIdVianda() == viandaGuardada.getIdVianda()) {
					viandaVerificada = v;
					break;
				}
			}
			
			if(viandaVerificada != null) {
				out.println("<p>Verificación de datos en BD:</p>");
				out.println("<ul>");
				out.println("<li>Nuevo Nombre: <strong>" + viandaVerificada.getNombre() + "</strong></li>");
				out.println("<li>Nuevo Precio: <strong>$" + viandaVerificada.getPrecioUnitario() + "</strong></li>");
				out.println("</ul>");
			}
			

			out.println("<h3>4. Ejecutando ELIMINACIÓN (deleteById)...</h3>");
			dv.deleteById(viandaGuardada);
			out.println("<p style='color: purple;'>✔ Vianda de prueba eliminada para limpiar la BD.</p>");
			

			LinkedList<Vianda> listaFinal = dv.getAll();
			out.println("<p>Cantidad de viandas al finalizar el test: <strong>" + listaFinal.size() + "</strong></p>");
			
			out.println("<hr>");
			out.println("<h2 style='color: green;'>🎉 ¡TEST COMPLETADO CON ÉXITO!</h2>");
			out.println("<p>La capa de persistencia básica para las viandas funciona perfectamente.</p>");
			
		} catch (Exception e) {
			out.println("<hr>");
			out.println("<h2 style='color: red;'>❌ El test falló en alguna operación:</h2>");
			out.println("<p><strong>Mensaje de error:</strong> " + e.getMessage() + "</p>");
			out.println("<pre style='background: #f4f4f4; padding: 10px; border: 1px solid #ddd;'>");
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