<%@ page import="java.util.LinkedList" %>
<%@ page import="entities.Ingrediente" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Ingredientes</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
      rel="stylesheet">
</head>
<body>
  <% LinkedList<Ingrediente> lista = (LinkedList<Ingrediente>) request.getAttribute("listaIngredientes"); %>
  <a href="ingrediente?action=new" class="btn btn-primary">Nuevo ingrediente</a>
  <table class = "table table-striped">
  <tr>
     <th>Codigo</th>
     <th>Nombre</th>
     <th>Stock</th>
     <th>Unidad</th>
     <th>Acciones</th>
  </tr>
  <% for (Ingrediente ing : lista) { %>
  <tr>
  <td><%= ing.getCodigo() %></td>
  <td><%= ing.getNombre() %></td>
  <td><%= ing.getStock() %></td>
  <td><%= ing.getUnidadMedida() %></td>
  <td>
  
  <a href ="ingrediente?action=edit&codigo=<%= ing.getCodigo() %>" class ="btn btn-warning btn-sm">Editar</a>
  
  <form action ="IngredienteProcesar" method="post" style="display:inline">
    <input type="hidden" name="accion" value="eliminar">
    <input type="hidden" name="codigo" value="<%= ing.getCodigo() %>">
    <button type ="submit" class="btn btn-danger btn-sm">Eliminar</button>
   </form>
   
   </td>
   </tr>
<% } %>
     
  </table>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>