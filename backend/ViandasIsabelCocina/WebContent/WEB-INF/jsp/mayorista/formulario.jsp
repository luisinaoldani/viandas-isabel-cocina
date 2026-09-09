<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="entities.Mayorista" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Procesos Mayorista</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
      rel="stylesheet">
</head>
<body>
<% Mayorista mayorista = (Mayorista) request.getAttribute("mayorista"); %>

  <form action="MayoristaProcesar" method="post">

   <input type="hidden" name="idUsuario" value="<%= (mayorista != null) ? mayorista.getIdUsuario() : "" %>">

  <label class="form-label">Nombre:</label>
    <input type="text" class="form-control" name="nombre" value="<%= (mayorista != null) ? mayorista.getNombre() : "" %>">

    <label class="form-label">Apellido:</label>
    <input type="text" class="form-control" name="apellido" value="<%= (mayorista != null) ? mayorista.getApellido() : "" %>">

     <label class="form-label">Email:</label>
    <input type="email" class="form-control" name="email" value="<%= (mayorista != null) ? mayorista.getEmail() : "" %>">

     <label class="form-label">Password:</label>
    <input type="password" class="form-control" name="password" value="<%= (mayorista != null) ? mayorista.getPassword() : "" %>">

     <label class="form-label">Cuit:</label>
    <input type="text" class="form-control" name="cuit" value="<%= (mayorista != null) ? mayorista.getCuit() : "" %>">

     <label class="form-label">Nombre del negocio:</label>
    <input type="text" class="form-control" name="nombreNegocio" value="<%= (mayorista != null) ? mayorista.getNombreNegocio() : "" %>">

     <label class="form-label">Telefono:</label>
    <input type="text" class="form-control" name="telefono" value="<%= (mayorista != null) ? mayorista.getTelefono() : "" %>">

     <label class="form-label">Domicilio:</label>
    <input type="text" class="form-control" name="domicilio" value="<%= (mayorista != null) ? mayorista.getDomicilio() : "" %>">

 <button type="submit" class="btn btn-primary">Guardar</button>
 <a href="mayorista" class="btn btn-secondary">Cancelar</a>
  </form>

</body>
</html>
