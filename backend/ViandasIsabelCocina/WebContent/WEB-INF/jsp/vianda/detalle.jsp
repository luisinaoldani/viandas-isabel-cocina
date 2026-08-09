<%@ page import="entities.Vianda" %>
<%@ page import="entities.IngredienteVianda" %>
<%@ page import="java.util.LinkedList" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Detalle de Vianda</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
      rel="stylesheet">
</head>
<body>
<div class="container mt-4">

<% Vianda vianda = (Vianda) request.getAttribute("vianda"); %>

<h2><%= vianda.getNombre() %></h2>

<table class="table">
    <tr>
        <th>Id Vianda</th>
        <td><%= vianda.getIdVianda() %></td>
    </tr>
    <tr>
        <th>Descripcion</th>
        <td><%= vianda.getDescripcion() %></td>
    </tr>
    <tr>
        <th>Precio Unitario</th>
        <td><%= vianda.getPrecioUnitario() %></td>
    </tr>
    <tr>
        <th>Tipo</th>
        <td><%= vianda.getTipo() %></td>
    </tr>
    <tr>
        <th>Activa</th>
        <td><%= vianda.isActiva() ? "Si" : "No" %></td>
    </tr>
</table>

<h4 class="mt-4">Ingredientes</h4>

<% LinkedList<IngredienteVianda> receta = vianda.getReceta(); %>

<% if (receta.isEmpty()) { %>
    <p>Esta vianda todavía no tiene ingredientes cargados.</p>
<% } else { %>
    <table class="table table-striped">
        <tr>
            <th>Codigo</th>
            <th>Nombre</th>
            <th>Cantidad</th>
            <th>Unidad</th>
        </tr>
        <% for (IngredienteVianda iv : receta) { %>
            <tr>
                <td><%= iv.getIngrediente().getCodigo() %></td>
                <td><%= iv.getIngrediente().getNombre() %></td>
                <td><%= iv.getCantidad() %></td>
                <td><%= iv.getIngrediente().getUnidadMedida() %></td>
            </tr>
        <% } %>
    </table>
<% } %>

<a href="vianda" class="btn btn-secondary">Volver al listado</a>

</div>
</body>
</html>
