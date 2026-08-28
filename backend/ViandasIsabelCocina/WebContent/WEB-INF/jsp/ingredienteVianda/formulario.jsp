<%@ page import="entities.IngredienteVianda" %>
<%@ page import="entities.Vianda" %>
<%@ page import="entities.Ingrediente" %>
<%@ page import="java.util.LinkedList" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Procesos Ingrediente_Vianda</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
      rel="stylesheet">
</head>
<body>
<% IngredienteVianda iv = (IngredienteVianda) request.getAttribute("ingredienteVianda"); %>
<% LinkedList<Vianda> listaViandas = (LinkedList<Vianda>) request.getAttribute("listaViandas"); %>
<% LinkedList<Ingrediente> listaIngredientes = (LinkedList<Ingrediente>) request.getAttribute("listaIngredientes"); %>

    <form action ="IngredienteViandaProcesar" method="post">

    <label class="form-label">Vianda:</label>
    <select class="form-control" name="idVianda">
    <% for (Vianda v : listaViandas) { %>
        <option value="<%= v.getIdVianda() %>" <%= iv != null && iv.getVianda().getIdVianda() == v.getIdVianda() ? "selected" : "" %>><%= v.getNombre() %></option>
    <% } %>
    </select>

    <label class="form-label">Ingrediente:</label>
    <select class="form-control" name="idIngrediente">
    <% for (Ingrediente ing : listaIngredientes) { %>
        <option value="<%= ing.getIdIngrediente() %>" <%= iv != null && iv.getIngrediente().getIdIngrediente() == ing.getIdIngrediente() ? "selected" : "" %>><%= ing.getNombre() %></option>
    <% } %>
    </select>

    <label class="form-label">Cantidad:</label>
    <input type="text" class="form-control" name="cantidad" value="<%= iv != null ? "" + iv.getCantidad() : "" %>">

    <button type="submit" class="btn btn-primary">Guardar</button>
    <a href="ingredienteVianda" class="btn btn-secondary">Cancelar</a>
    </form>
</body>
</html>