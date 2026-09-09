<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.LinkedList" %>
<%@ page import="entities.Mayorista" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Mayoristas</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
      rel="stylesheet">
</head>
<body>
 <% LinkedList<Mayorista> lista = (LinkedList<Mayorista>) request.getAttribute("listaMayoristas"); %>
 <% String error = (String) request.getAttribute("error"); %>
 <% if (error != null) { %>
    <div class="alert alert-danger" role="alert"><%= error %></div>
 <% } %>

 <a href="mayorista?action=new" class="btn btn-primary">Nuevo Mayorista</a>
 <table class="table table-striped">
<tr>
    <th>Id</th>
    <th>Nombre</th>
    <th>Apellido</th>
    <th>Email</th>
    <th>Rol</th>
    <th>Cuit</th>
    <th>Negocio</th>
    <th>Acciones</th>
</tr>
    <% for (Mayorista m : lista) { %>
   <tr>
   <td><%= m.getIdUsuario() %></td>
   <td><%= m.getNombre() %></td>
   <td><%= m.getApellido() %></td>
   <td><%= m.getEmail() %></td>
   <td><%= m.getRol() %></td>
   <td><%= m.getCuit() %></td>
   <td><%= m.getNombreNegocio() %></td>
   <td>

  <a href="mayorista?action=edit&idUsuario=<%= m.getIdUsuario() %>" class="btn btn-warning btn-sm">Editar</a>

     <form action="MayoristaProcesar" method="post" style="display:inline"
          onsubmit="return confirm('¿Estás seguro que querés eliminar al mayorista &quot;<%= m.getNombre() %> <%= m.getApellido() %>&quot;?');">
    <input type="hidden" name="accion" value="eliminar">
    <input type="hidden" name="idUsuario" value="<%= m.getIdUsuario() %>">
    <button type="submit" class="btn btn-danger btn-sm">Eliminar</button>
   </form>

    </td>
   </tr>

<% } %>

 </table>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
