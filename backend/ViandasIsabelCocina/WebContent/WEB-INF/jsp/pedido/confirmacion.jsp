<%@ page import="entities.Pedido" %>
<%@ page import="entities.DetallePedido" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Pedido Confirmado</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
      rel="stylesheet">
</head>
<body>
<div class="container mt-4">

<% Pedido pedido = (Pedido) request.getAttribute("pedido"); %>

<div class="alert alert-success">
    Pedido N&deg; <%= pedido.getNumero() %> confirmado con &eacute;xito.
</div>

<p>Fecha de entrega: <%= pedido.getFechaEntrega() %></p>
<p>Estado: <%= pedido.getEstado() %></p>

<table class="table">
    <thead>
        <tr>
            <th>Vianda</th>
            <th>Cantidad</th>
            <th>Subtotal</th>
        </tr>
    </thead>
    <tbody>
    <% for (DetallePedido d : pedido.getDetalles()) { %>
        <tr>
            <td><%= d.getVianda().getNombre() %></td>
            <td><%= d.getCantidad() %></td>
            <td><%= d.getSubtotal() %></td>
        </tr>
    <% } %>
    </tbody>
</table>

<p><strong>Total: <%= pedido.getPrecioTotal() %></strong></p>

</div>
</body>
</html>
