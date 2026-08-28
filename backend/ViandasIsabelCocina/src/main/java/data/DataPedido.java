package data;

import entities.DetallePedido;
import entities.Pedido;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

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

	public LinkedList<Pedido> getAll() {
		LinkedList<Pedido> pedidos = new LinkedList<>();
		Statement stmt = null;
		ResultSet rs = null;

		try {
			stmt = DbConnector.getInstancia().getConn().createStatement();
			rs = stmt.executeQuery(
					"SELECT numero, fechaRealizado, fechaEntrega, fechaCancelacion, estado FROM pedido ORDER BY numero DESC");

			while (rs.next()) {
				Pedido p = new Pedido();
				p.setNumero(rs.getInt("numero"));
				p.setFechaRealizado(rs.getDate("fechaRealizado") != null ? rs.getDate("fechaRealizado").toLocalDate() : null);
				p.setFechaEntrega(rs.getDate("fechaEntrega") != null ? rs.getDate("fechaEntrega").toLocalDate() : null);
				p.setFechaCancelacion(rs.getDate("fechaCancelacion") != null ? rs.getDate("fechaCancelacion").toLocalDate() : null);
				p.setEstado(rs.getString("estado"));
				pedidos.add(p);
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

		return pedidos;
	}

	public Pedido getById(int numero) {
		Pedido p = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			stmt = DbConnector.getInstancia().getConn().prepareStatement(
					"SELECT numero, fechaRealizado, fechaEntrega, fechaCancelacion, estado FROM pedido WHERE numero = ?");
			stmt.setInt(1, numero);
			rs = stmt.executeQuery();

			if (rs != null && rs.next()) {
				p = new Pedido();
				p.setNumero(rs.getInt("numero"));
				p.setFechaRealizado(rs.getDate("fechaRealizado") != null ? rs.getDate("fechaRealizado").toLocalDate() : null);
				p.setFechaEntrega(rs.getDate("fechaEntrega") != null ? rs.getDate("fechaEntrega").toLocalDate() : null);
				p.setFechaCancelacion(rs.getDate("fechaCancelacion") != null ? rs.getDate("fechaCancelacion").toLocalDate() : null);
				p.setEstado(rs.getString("estado"));
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

		if (p != null) {
			p.setDetalles(dataDetallePedido.getByPedido(numero));
		}

		return p;
	}

	public void update(Pedido pedido) {
		Connection conn = DbConnector.getInstancia().getConn();
		PreparedStatement stmtUpdate = null;

		try {
			conn.setAutoCommit(false);

			stmtUpdate = conn.prepareStatement("UPDATE pedido SET fechaEntrega = ? WHERE numero = ?");
			stmtUpdate.setDate(1, Date.valueOf(pedido.getFechaEntrega()));
			stmtUpdate.setInt(2, pedido.getNumero());
			stmtUpdate.executeUpdate();

			dataDetallePedido.deleteByPedido(conn, pedido.getNumero());

			for (DetallePedido detalle : pedido.getDetalles()) {
				dataDetallePedido.setDetallePedido(conn, pedido.getNumero(), detalle);
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
				if (stmtUpdate != null) { stmtUpdate.close(); }
				conn.setAutoCommit(true);
				DbConnector.getInstancia().releaseConn();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	

	public void cancelar(int numero) {
		PreparedStatement stmt = null;

		try {
			stmt = DbConnector.getInstancia().getConn().prepareStatement(
					"UPDATE pedido SET estado = 'CANCELADO', fechaCancelacion = ? WHERE numero = ? AND estado = 'PENDIENTE'");
			stmt.setDate(1, Date.valueOf(java.time.LocalDate.now()));
			stmt.setInt(2, numero);
			stmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();

		} finally {
			try {
				if (stmt != null) { stmt.close(); }
				DbConnector.getInstancia().releaseConn();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}