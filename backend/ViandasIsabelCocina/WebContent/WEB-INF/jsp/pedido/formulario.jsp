<%@ page import="entities.Vianda" %>
<%@ page import="java.util.LinkedList" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Realizar Pedido</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
      rel="stylesheet">
</head>
<body>
<div class="container mt-4">
<h2>Realizar Pedido</h2>

<% LinkedList<Vianda> listaViandas = (LinkedList<Vianda>) request.getAttribute("listaViandas"); %>

<form action="PedidoProcesar" method="post">

    <div class="mb-3">
        <label class="form-label">Fecha de entrega:</label>
        <input type="date" class="form-control" name="fechaEntrega" required>
    </div>

    <table class="table">
        <thead>
            <tr>
                <th>Vianda</th>
                <th>Descripcion</th>
                <th>Tipo</th>
                <th>Precio unitario</th>
                <th>Cantidad</th>
            </tr>
        </thead>
        <tbody>
        <% for (Vianda v : listaViandas) { %>
            <tr>
                <td><%= v.getNombre() %></td>
                <td><%= v.getDescripcion() %></td>
                <td><%= v.getTipo() %></td>
                <td><%= v.getPrecioUnitario() %></td>
                <td>
                    <input type="number" min="0" class="form-control"
                           name="cantidad_<%= v.getIdVianda() %>" value="0">
                </td>
            </tr>
        <% } %>
        </tbody>
    </table>

    <button type="submit" class="btn btn-primary">Confirmar Pedido</button>
</form>
</div>
</body>
</html>
