<%@ page import="entities.Ingrediente" %>
<%@ page import="java.util.LinkedList" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Procesos Ingredientes</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
      rel="stylesheet">
</head>
<body>
<% Ingrediente ing = (Ingrediente) request.getAttribute("ingrediente"); %>

    <form action ="IngredienteProcesar" method="post">

    <label class="form-label">Nombre:</label>
    <input type="text" class="form-control" name="nombre" value="<%= ing != null ? ing.getNombre() : "" %>">

    <label class="form-label">Código:</label>
    <input type="text" class="form-control" name="codigo" value="<%= ing != null ? ing.getCodigo() : "" %>">

    <label class="form-label">Stock:</label>
    <input type="text" class="form-control" name="stock" value="<%= ing != null ? "" + ing.getStock() : "" %>">

    <label class="form-label">Unidad de medida:</label>
    <input type="text" class="form-control" name="unidadMedida" value="<%= ing != null ? ing.getUnidadMedida() : "" %>">

    <button type="submit" class="btn btn-primary">Guardar</button>
    </form>
</body>
</html>