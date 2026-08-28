<%@ page import="entities.Vianda" %>
<%@ page import="entities.Pedido" %>
<%@ page import="entities.DetallePedido" %>
<%@ page import="java.util.LinkedList" %>
<%@ page import="java.util.HashMap" %>
<%@ page import="java.util.Map" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Pedido</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
      rel="stylesheet">
</head>
<body>
<div class="container mt-4">

<% LinkedList<Vianda> listaViandas = (LinkedList<Vianda>) request.getAttribute("listaViandas"); %>
<% Pedido pedido = (Pedido) request.getAttribute("pedido"); %>

<% Map<Integer, Integer> cantidadesActuales = new HashMap<>();
   if (pedido != null) {
       for (DetallePedido d : pedido.getDetalles()) {
           cantidadesActuales.put(d.getVianda().getIdVianda(), d.getCantidad());
       }
   }
%>

<h2><%= pedido != null ? "Editar Pedido N&deg; " + pedido.getNumero() : "Realizar Pedido" %></h2>

<form action="PedidoProcesar" method="post">

<% if (pedido != null) { %>
    <input type="hidden" name="accion" value="actualizar">
    <input type="hidden" name="numero" value="<%= pedido.getNumero() %>">
<% } %>

    <div class="mb-3">
        <label class="form-label">Fecha de entrega:</label>
        <input type="date" class="form-control" name="fechaEntrega"
               value="<%= pedido != null ? pedido.getFechaEntrega() : "" %>" required>
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
        <% for (Vianda v : listaViandas) {
               Integer cantidadActual = cantidadesActuales.get(v.getIdVianda());
        %>
            <tr>
                <td><%= v.getNombre() %></td>
                <td><%= v.getDescripcion() %></td>
                <td><%= v.getTipo() %></td>
                <td><%= v.getPrecioUnitario() %></td>
                <td>
                    <input type="number" min="0" class="form-control"
                           name="cantidad_<%= v.getIdVianda() %>"
                           value="<%= cantidadActual != null ? cantidadActual : 0 %>">
                </td>
            </tr>
        <% } %>
        </tbody>
    </table>

    <button type="submit" class="btn btn-primary">
        <%= pedido != null ? "Guardar Cambios" : "Confirmar Pedido" %>
    </button>
    <a href="pedido" class="btn btn-secondary">Cancelar</a>
</form>
</div>
</body>
</html>
