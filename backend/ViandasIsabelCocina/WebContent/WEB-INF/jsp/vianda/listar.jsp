<%@ page import="java.util.LinkedList" %>
<%@ page import="entities.Vianda" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Viandas</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
      rel="stylesheet">
</head>
<body>
 <% LinkedList <Vianda> lista = (LinkedList<Vianda>) request.getAttribute("listaViandas"); %>
 
 <a href ="vianda?action=new" class ="btn btn-primary">Nueva Vianda</a>
 <table class = "table table-striped">
<tr>
    <th>Id Vianda </th>
    <th>Nombre </th>
    <th>Descripcion </th>
    <th>Precio Unitario </th>
    <th>Tipo </th>
    <th>Activa </th>
    <th>Acciones </th>
</tr>
    <% for (Vianda via: lista){ %>
   <tr>
   <td><%= via.getIdVianda() %> </td>
   <td><%= via.getNombre() %> </td>
   <td><%= via.getDescripcion() %> </td>
   <td><%= via.getPrecioUnitario() %> </td>
   <td><%= via.getTipo()%> </td>
   <td><%= via.isActiva()  ? "Si": "No" %> </td>
   <td>
   
  <a href="vianda?action=edit&idVianda=<%= via.getIdVianda() %>" class="btn btn-warning btn-sm">Editar</a>
   
    <form action ="ViandaProcesar" method="post" style="display:inline">
    <input type="hidden" name="accion" value="eliminar">
    <input type="hidden" name="idVianda" value="<%= via.getIdVianda() %>">
    <button type ="submit" class="btn btn-danger btn-sm">Eliminar</button>
   </form>
   
    </td>
   </tr>
 
<% } %>
 
 </table>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>