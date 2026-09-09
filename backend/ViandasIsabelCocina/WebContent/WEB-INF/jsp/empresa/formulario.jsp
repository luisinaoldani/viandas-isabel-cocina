<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="entities.Empresa" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Procesos Empresa</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
      rel="stylesheet">
</head>
<body>
<% Empresa empresa = (Empresa) request.getAttribute("empresa"); %>

  <form action="EmpresaProcesar" method="post">

   <input type="hidden" name="idUsuario" value="<%= (empresa != null) ? empresa.getIdUsuario() : "" %>">

  <label class="form-label">Nombre:</label>
    <input type="text" class="form-control" name="nombre" value="<%= (empresa != null) ? empresa.getNombre() : "" %>">

    <label class="form-label">Apellido:</label>
    <input type="text" class="form-control" name="apellido" value="<%= (empresa != null) ? empresa.getApellido() : "" %>">

     <label class="form-label">Email:</label>
    <input type="email" class="form-control" name="email" value="<%= (empresa != null) ? empresa.getEmail() : "" %>">

     <label class="form-label">Password:</label>
    <input type="password" class="form-control" name="password" value="<%= (empresa != null) ? empresa.getPassword() : "" %>">

     <label class="form-label">Cuit:</label>
    <input type="text" class="form-control" name="cuit" value="<%= (empresa != null) ? empresa.getCuit() : "" %>">

     <label class="form-label">Nombre del negocio:</label>
    <input type="text" class="form-control" name="nombreNegocio" value="<%= (empresa != null) ? empresa.getNombreNegocio() : "" %>">

     <label class="form-label">Telefono:</label>
    <input type="text" class="form-control" name="telefono" value="<%= (empresa != null) ? empresa.getTelefono() : "" %>">

     <label class="form-label">Domicilio:</label>
    <input type="text" class="form-control" name="domicilio" value="<%= (empresa != null) ? empresa.getDomicilio() : "" %>">

     <label class="form-label">Día de pedido semanal:</label>
    <select class="form-control" name="diaPedidoSemanal">
        <% String[] dias = {"Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado", "Domingo"}; %>
        <% for (String dia : dias) { %>
            <option value="<%= dia %>" <%= (empresa != null && dia.equals(empresa.getDiaPedidoSemanal())) ? "selected" : "" %>><%= dia %></option>
        <% } %>
    </select>

     <label class="form-label">Cantidad de empleados:</label>
    <input type="number" min="0" class="form-control" name="cantEmpleados" value="<%= (empresa != null) ? "" + empresa.getCantEmpleados() : "" %>">

 <button type="submit" class="btn btn-primary">Guardar</button>
 <a href="empresa" class="btn btn-secondary">Cancelar</a>
  </form>

</body>
</html>
