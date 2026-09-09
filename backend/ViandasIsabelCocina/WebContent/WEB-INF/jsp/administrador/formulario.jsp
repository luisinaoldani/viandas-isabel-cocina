<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="entities.Administrador" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Procesos Administrador</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
      rel="stylesheet">
</head>
<body>
<% Administrador admin = (Administrador) request.getAttribute("administrador"); %>

  <form action="AdministradorProcesar" method="post">

   <input type="hidden" name="idAdministrador" value="<%= (admin != null) ? admin.getIdUsuario() : "" %>">

  	<label class="form-label">Nombre:</label>
    <input type="text" class="form-control" name="nombre" value="<%= (admin != null) ? admin.getNombre() : "" %>">

    <label class="form-label">Apellido:</label>
    <input type="text" class="form-control" name="apellido" value="<%= (admin != null) ? admin.getApellido() : "" %>">

    <label class="form-label">Email:</label>
    <input type="email" class="form-control" name="email" value="<%= (admin != null) ? admin.getEmail() : "" %>">

    <label class="form-label">Password:</label>
    <input type="password" class="form-control" name="password" value="<%= (admin != null) ? admin.getPassword() : "" %>">

 <button type="submit" class="btn btn-primary">Guardar</button>
 <a href="administrador" class="btn btn-secondary">Cancelar</a>
  </form>

</body>
</html>
