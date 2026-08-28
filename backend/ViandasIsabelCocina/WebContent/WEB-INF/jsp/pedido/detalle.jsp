<%@ page import="entities.Pedido" %>
<%@ page import="entities.DetallePedido" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Detalle de Pedido</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
      rel="stylesheet">
</head>
<body>
<div class="container mt-4">

<% Pedido pedido = (Pedido) request.getAttribute("pedido"); %>

<h2>Pedido N&deg; <%= pedido.getNumero() %></h2>

<table class="table">
    <tr>
        <th>Fecha Realizado</th>
        <td><%= pedido.getFechaRealizado() %></td>
    </tr>
    <tr>
        <th>Fecha Entrega</th>
        <td><%= pedido.getFechaEntrega() %></td>
    </tr>
    <tr>
        <th>Fecha Cancelacion</th>
        <td><%= pedido.getFechaCancelacion() != null ? pedido.getFechaCancelacion() : "-" %></td>
    </tr>
    <tr>
        <th>Estado</th>
        <td><%= pedido.getEstado() %></td>
    </tr>
</table>

<h4 class="mt-4">Detalle</h4>

<table class="table table-striped">
    <tr>
        <th>Vianda</th>
        <th>Cantidad</th>
        <th>Subtotal</th>
    </tr>
    <% for (DetallePedido d : pedido.getDetalles()) { %>
        <tr>
            <td><%= d.getVianda().getNombre() %></td>
            <td><%= d.getCantidad() %></td>
            <td><%= d.getSubtotal() %></td>
        </tr>
    <% } %>
</table>

<p><strong>Total: <%= pedido.getPrecioTotal() %></strong></p>

<% if ("PENDIENTE".equals(pedido.getEstado())) { %>
    <a href="pedido?action=edit&numero=<%= pedido.getNumero() %>" class="btn btn-warning">Editar</a>
<% } %>
<a href="pedido" class="btn btn-secondary">Volver al listado</a>

</div>
</body>
</html>
