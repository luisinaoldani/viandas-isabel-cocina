package data;

import entities.DetallePedido;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DataDetallePedido {

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
}