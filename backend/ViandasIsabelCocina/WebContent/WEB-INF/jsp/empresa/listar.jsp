<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.util.LinkedList" %>
<%@ page import="entities.Empresa" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Empresas</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
      rel="stylesheet">
</head>
<body>
 <% LinkedList<Empresa> lista = (LinkedList<Empresa>) request.getAttribute("listaEmpresas"); %>
 <% String error = (String) request.getAttribute("error"); %>
 <% if (error != null) { %>
    <div class="alert alert-danger" role="alert"><%= error %></div>
 <% } %>

 <a href="empresa?action=new" class="btn btn-primary">Nueva Empresa</a>
 <table class="table table-striped">
<tr>
    <th>Id</th>
    <th>Nombre</th>
    <th>Apellido</th>
    <th>Email</th>
    <th>Negocio</th>
    <th>Día de pedido</th>
    <th>Cant. Empleados</th>
    <th>Acciones</th>
</tr>
    <% for (Empresa e : lista) { %>
   <tr>
   <td><%= e.getIdUsuario() %></td>
   <td><%= e.getNombre() %></td>
   <td><%= e.getApellido() %></td>
   <td><%= e.getEmail() %></td>
   <td><%= e.getNombreNegocio() %></td>
   <td><%= e.getDiaPedidoSemanal() %></td>
   <td><%= e.getCantEmpleados() %></td>
   <td>

  <a href="empresa?action=edit&idUsuario=<%= e.getIdUsuario() %>" class="btn btn-warning btn-sm">Editar</a>

     <form action="EmpresaProcesar" method="post" style="display:inline"
          onsubmit="return confirm('¿Estás seguro que querés eliminar la empresa &quot;<%= e.getNombreNegocio() %>&quot;?');">
    <input type="hidden" name="accion" value="eliminar">
    <input type="hidden" name="idUsuario" value="<%= e.getIdUsuario() %>">
    <button type="submit" class="btn btn-danger btn-sm">Eliminar</button>
   </form>

    </td>
   </tr>

<% } %>

 </table>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
