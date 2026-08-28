<%@ page import="entities.Vianda" %>
<%@ page import="java.util.LinkedList" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Procesos Viandas</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
      rel="stylesheet">
</head>
<body>
<% Vianda via = (Vianda) request.getAttribute("vianda"); %>

  <form action = "ViandaProcesar" method ="post">
  
   <input type="hidden" name="idVianda" value="<%= (via != null) ? via.getIdVianda() : "" %>">
  
  <label class="form-label">Nombre:</label>
    <input type="text" class="form-control" name="nombre" value="<%= (via != null) ? via.getNombre() : "" %>">
    
    <label class="form-label">Descripcion:</label>
    <input type="text" class="form-control" name="descripcion" value="<%= (via != null) ? via.getDescripcion() : "" %>">
    
     <label class="form-label">Precio Unitario:</label>
    <input type="text" class="form-control" name="precioUnitario" value="<%= (via != null) ? ("" + via.getPrecioUnitario()) : "" %>">
  
       <label class="form-label">Tipo:</label>
    <input type="text" class="form-control" name="tipo" value="<%= (via != null) ?   via.getTipo() : "" %>">
    
        <label class="form-label">Activa:</label>
<select class="form-control" name="activa">
    <option value="true" <%= (via != null && via.isActiva()) ? "selected" : "" %>>Sí</option>
    <option value="false" <%= (via != null && !via.isActiva()) ? "selected" : "" %>>No</option>
</select>

 <button type="submit" class="btn btn-primary">Guardar</button>
 <a href="vianda" class="btn btn-secondary">Cancelar</a>
  </form>

</body>
</html>