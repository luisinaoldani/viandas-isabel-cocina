<%@ page import="java.util.LinkedList" %>
<%@ page import="entities.IngredienteVianda" %>
<%@ page import="entities.Vianda" %>
<%@ page import="entities.Ingrediente" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>IngredienteVianda</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
      rel="stylesheet">
</head>
<body>
   <% LinkedList<IngredienteVianda> lista = (LinkedList<IngredienteVianda>) request.getAttribute("listaIngredienteVianda"); %>
  <a href="ingredienteVianda?action=new" class="btn btn-primary">Nuevo Ingrediente_Vianda</a>
  <table class = "table table-striped">
  <tr>
     <th>Vianda</th>
     <th>Ingrediente</th>
     <th>Cantidad</th>
     <th>Acciones</th>
  </tr>
  <% for (IngredienteVianda iv : lista) { %>
  <tr>
  <td><%= iv.getVianda().getNombre() %></td>
  <td><%= iv.getIngrediente().getNombre() %></td>
  <td><%= iv.getCantidad() %></td>
  <td>

  <a href ="ingredienteVianda?action=edit&codigoIngrediente=<%= iv.getIngrediente().getCodigo() %>&idVianda=<%= iv.getVianda().getIdVianda() %>" class ="btn btn-warning btn-sm">Editar</a>

  <form action ="IngredienteViandaProcesar" method="post" style="display:inline">
    <input type="hidden" name="accion" value="eliminar">
    <input type="hidden" name="codigoIngrediente" value="<%= iv.getIngrediente().getCodigo() %>">
    <input type="hidden" name="idVianda" value="<%= iv.getVianda().getIdVianda() %>">
    <button type ="submit" class="btn btn-danger btn-sm">Eliminar</button>
   </form>

   </td>
   </tr>
<% } %>

  </table>
    
   
</body>
</html>