package data;

import entities.DetallePedido;
import entities.Vianda;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;


public class DataDetallePedido {
	
	public LinkedList<DetallePedido> getByPedido(int numeroPedido) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		LinkedList<DetallePedido> detallesPedido = new LinkedList<>();

		try {
			stmt = DbConnector.getInstancia().getConn().prepareStatement(
					"SELECT dp.cantidad, v.idVianda, v.nombre, v.descripcion, v.precioUnitario, v.tipo, v.activa "
					+ "FROM detalle_pedido dp "
					+ "JOIN vianda v ON v.idVianda = dp.idVianda "
					+ "WHERE dp.numero = ?");
			stmt.setInt(1, numeroPedido);
			rs = stmt.executeQuery();

			while (rs.next()) {
				Vianda v = new Vianda();
				v.setIdVianda(rs.getInt("idVianda"));
				v.setNombre(rs.getString("nombre"));
				v.setDescripcion(rs.getString("descripcion"));
				v.setPrecioUnitario(rs.getDouble("precioUnitario"));
				v.setTipo(rs.getString("tipo"));
				v.setActiva(rs.getBoolean("activa"));

				DetallePedido d = new DetallePedido(v, rs.getInt("cantidad"));
				detallesPedido.add(d);
			}

		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			try {
				if (rs != null) { rs.close(); }
				if (stmt != null) { stmt.close(); }
				DbConnector.getInstancia().releaseConn();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return detallesPedido;
	}

	public void setDetallePedido(Connection conn, int numeroPedido, DetallePedido d) throws SQLException {
		PreparedStatement stmt = null;
		
		try {
			stmt = conn.prepareStatement(
					"INSERT INTO detalle_pedido (numero, idVianda, cantidad) VALUES (?, ?, ?)");
			stmt.setInt(1, numeroPedido);
			stmt.setInt(2, d.getVianda().getIdVianda());
			stmt.setInt(3, d.getCantidad());
			stmt.executeUpdate();

		} finally {
			if (stmt != null) {
				stmt.close();
			}
		}
	}


	public void deleteByPedido(Connection conn, int numeroPedido) throws SQLException {
		PreparedStatement stmt = null;
		
		try {
			stmt = conn.prepareStatement("DELETE FROM detalle_pedido WHERE numero = ?");
			stmt.setInt(1, numeroPedido);
			stmt.executeUpdate();

		} finally {
			if (stmt != null) {
				stmt.close();
			}
		}
	}
	
}