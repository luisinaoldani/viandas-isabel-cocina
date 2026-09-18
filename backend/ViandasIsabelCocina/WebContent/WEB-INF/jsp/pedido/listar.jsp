<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
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
<% String estadoFiltro = (String) request.getAttribute("estadoFiltro"); %>
<% String error = (String) request.getAttribute("error"); %>
<% if (error != null) { %>
    <div class="alert alert-danger" role="alert"><%= error %></div>
<% } %>

<a href="pedido?action=new" class="btn btn-primary mb-3">Nuevo Pedido</a>

<% if ("CONFIRMADO".equals(estadoFiltro)) { %>
    <a href="pedido" class="btn btn-outline-secondary mb-3">Ver todos los pedidos</a>
<% } else { %>
    <a href="pedido?estado=CONFIRMADO" class="btn btn-outline-success mb-3">Ver pedidos confirmados</a>
<% } %>

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

            <% if ("PENDIENTE_CONFIRMACION".equals(p.getEstado())) { %>
                <a href="pedido?action=edit&numero=<%= p.getNumero() %>" class="btn btn-warning btn-sm">Editar</a>

                <form action="PedidoProcesar" method="post" style="display:inline">
                    <input type="hidden" name="accion" value="confirmar">
                    <input type="hidden" name="numero" value="<%= p.getNumero() %>">
                    <button type="submit" class="btn btn-success btn-sm">Confirmar</button>
                </form>
            <% } %>
            
            <% if ("PENDIENTE_CONFIRMACION".equals(p.getEstado()) || "CONFIRMADO".equals(p.getEstado())) { %>
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
