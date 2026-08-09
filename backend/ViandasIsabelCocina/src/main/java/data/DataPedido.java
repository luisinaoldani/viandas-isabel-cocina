package data;

import entities.DetallePedido;
import entities.Pedido;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DataPedido {

	private DataDetallePedido dataDetallePedido = new DataDetallePedido();

	public Pedido setPedido(Pedido pedido) {
		Connection conn = DbConnector.getInstancia().getConn();
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			conn.setAutoCommit(false);

			stmt = conn.prepareStatement(
					"INSERT INTO pedido (fechaRealizado, fechaEntrega, fechaCancelacion, estado) VALUES (?, ?, ?, ?)",
					Statement.RETURN_GENERATED_KEYS);

			stmt.setDate(1, pedido.getFechaRealizado() != null ? Date.valueOf(pedido.getFechaRealizado()) : null);
			stmt.setDate(2, pedido.getFechaEntrega() != null ? Date.valueOf(pedido.getFechaEntrega()) : null);
			stmt.setDate(3, pedido.getFechaCancelacion() != null ? Date.valueOf(pedido.getFechaCancelacion()) : null);
			stmt.setString(4, pedido.getEstado());

			stmt.executeUpdate();

			rs = stmt.getGeneratedKeys();
			if (rs != null && rs.next()) {
				int numero = rs.getInt(1);
				pedido.setNumero(numero);

				for (DetallePedido detalle : pedido.getDetalles()) {
					dataDetallePedido.setDetallePedido(conn, numero, detalle);
				}
			}

			conn.commit();

		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
			e.printStackTrace();

		} finally {
			try {
				if (rs != null) { rs.close(); }
				if (stmt != null) { stmt.close(); }
				conn.setAutoCommit(true);
				DbConnector.getInstancia().releaseConn();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return pedido;
	}

}