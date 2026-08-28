<%@ page import="java.util.LinkedList" %>
<%@ page import="entities.Pedido" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Pedidos</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
      rel="stylesheet">
</head>
<body>
<div class="container mt-4">

<% LinkedList<Pedido> listaPedidos = (LinkedList<Pedido>) request.getAttribute("listaPedidos"); %>

<a href="pedido?action=new" class="btn btn-primary mb-3">Nuevo Pedido</a>

<table class="table table-striped">
<tr>
    <th>Numero</th>
    <th>Fecha Realizado</th>
    <th>Fecha Entrega</th>
    <th>Estado</th>
    <th>Acciones</th>
</tr>
<% for (Pedido p : listaPedidos) { %>
    <tr>
        <td><%= p.getNumero() %></td>
        <td><%= p.getFechaRealizado() %></td>
        <td><%= p.getFechaEntrega() %></td>
        <td><%= p.getEstado() %></td>
        <td>
            <a href="pedido?action=detalle&numero=<%= p.getNumero() %>" class="btn btn-info btn-sm">Ver detalle</a>

            <% if ("PENDIENTE".equals(p.getEstado())) { %>
                <a href="pedido?action=edit&numero=<%= p.getNumero() %>" class="btn btn-warning btn-sm">Editar</a>

                <form action="PedidoProcesar" method="post" style="display:inline"
                      onsubmit="return confirm('&iquest;Est&aacute;s seguro que quer&eacute;s cancelar el pedido N&deg; <%= p.getNumero() %>?');">
                    <input type="hidden" name="accion" value="cancelar">
                    <input type="hidden" name="numero" value="<%= p.getNumero() %>">
                    <button type="submit" class="btn btn-danger btn-sm">Cancelar</button>
                </form>
            <% } %>
        </td>
    </tr>
<% } %>
</table>

</div>
</body>
</html>
