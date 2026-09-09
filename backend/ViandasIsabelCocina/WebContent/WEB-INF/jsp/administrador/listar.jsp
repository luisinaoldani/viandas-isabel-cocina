<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.LinkedList" %>
<%@ page import="entities.Administrador" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Administradores</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
      rel="stylesheet">
</head>
<body>
 <% LinkedList<Administrador> lista = (LinkedList<Administrador>) request.getAttribute("listaAdministradores"); %>
 <% String error = (String) request.getAttribute("error"); %>
 <% if (error != null) { %>
    <div class="alert alert-danger" role="alert"><%= error %></div>
 <% } %>

 <a href="administrador?action=new" class="btn btn-primary">Nuevo Administrador</a>
 <table class="table table-striped">
<tr>
    <th>ID</th>
    <th>Nombre</th>
    <th>Apellido</th>
    <th>Email</th>
    <th>Rol</th>
    <th>Acciones</th>
</tr>
    <% for (Administrador a : lista) { %>
   <tr>
   <td><%= a.getIdUsuario() %></td>
   <td><%= a.getNombre() %></td>
   <td><%= a.getApellido() %></td>
   <td><%= a.getEmail() %></td>
   <td><%= a.getRol() %></td>
   <td>

  <a href="administrador?action=edit&idAdministrador=<%= a.getIdUsuario() %>" class="btn btn-warning btn-sm">Editar</a>

     <form action="AdministradorProcesar" method="post" style="display:inline"
          onsubmit="return confirm('¿Estás seguro que querés eliminar al administrador &quot;<%= a.getNombre() %> <%= a.getApellido() %>&quot;?');">
    <input type="hidden" name="accion" value="eliminar">
    <input type="hidden" name="idAdministrador" value="<%= a.getIdUsuario() %>">
    <button type="submit" class="btn btn-danger btn-sm">Eliminar</button>
   </form>

    </td>
   </tr>

<% } %>

 </table>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
