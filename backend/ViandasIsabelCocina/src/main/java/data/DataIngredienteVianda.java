package data;

import entities.Ingrediente;
import entities.IngredienteVianda;
import entities.Vianda;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.LinkedList;

public class DataIngredienteVianda {

	public LinkedList<IngredienteVianda> getAll() {
		Statement stmt = null;
		ResultSet rs = null;
		LinkedList<IngredienteVianda> ingredienteVianda = new LinkedList<>();

		try {
			stmt = DbConnector.getInstancia().getConn().createStatement();
			rs = stmt.executeQuery("SELECT iv.codigoIngrediente, iv.idVianda, iv.cantidad, "
					+ "i.nombre AS nombreIngrediente, i.stock, i.unidadMedida, "
					+ "v.nombre AS nombreVianda, v.descripcion, v.precioUnitario, v.tipo, v.activa "
					+ "FROM ingrediente_vianda iv, ingrediente i, vianda v "
					+ "WHERE iv.codigoIngrediente = i.codigo "
					+ "AND iv.idVianda = v.idVianda ");

			if (rs != null) {
				while (rs.next()) {
					Ingrediente i = new Ingrediente();
					i.setCodigo(rs.getString("codigoIngrediente"));
					i.setNombre(rs.getString("nombreIngrediente"));
					i.setStock(rs.getDouble("stock"));
					i.setUnidadMedida(rs.getString("unidadMedida"));

					Vianda v = new Vianda();
					v.setIdVianda(rs.getInt("idVianda"));
					v.setNombre(rs.getString("nombreVianda"));
					v.setDescripcion(rs.getString("descripcion"));
					v.setPrecioUnitario(rs.getDouble("precioUnitario"));
					v.setTipo(rs.getString("tipo"));
					v.setActiva(rs.getBoolean("activa"));

					IngredienteVianda iv = new IngredienteVianda();
					iv.setIngrediente(i);
					iv.setVianda(v);
					iv.setCantidad(rs.getDouble("cantidad"));

					ingredienteVianda.add(iv);
				}
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
		return ingredienteVianda;
	}


	public IngredienteVianda getById(String codigoIngrediente, int idVianda) {
		IngredienteVianda iv = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			stmt = DbConnector.getInstancia().getConn().prepareStatement(
					"SELECT iv.codigoIngrediente, iv.idVianda, iv.cantidad, i.nombre AS nombreIngrediente, "
					+ "i.stock, i.unidadMedida, v.nombre AS nombreVianda, v.descripcion, v.precioUnitario, "
					+ "v.tipo, v.activa FROM ingrediente_vianda iv, ingrediente i, vianda v "
					+ "WHERE iv.codigoIngrediente = i.codigo AND iv.idVianda = v.idVianda "
					+ "AND iv.codigoIngrediente = ? AND iv.idVianda = ? ");
			stmt.setString(1, codigoIngrediente);
			stmt.setInt(2, idVianda);
			rs = stmt.executeQuery();

			if (rs != null && rs.next()) {
				Ingrediente i = new Ingrediente();
				i.setCodigo(rs.getString("codigoIngrediente"));
				i.setNombre(rs.getString("nombreIngrediente"));
				i.setStock(rs.getDouble("stock"));
				i.setUnidadMedida(rs.getString("unidadMedida"));

				Vianda v = new Vianda();
				v.setIdVianda(rs.getInt("idVianda"));
				v.setNombre(rs.getString("nombreVianda"));
				v.setDescripcion(rs.getString("descripcion"));
				v.setPrecioUnitario(rs.getDouble("precioUnitario"));
				v.setTipo(rs.getString("tipo"));
				v.setActiva(rs.getBoolean("activa"));

				iv = new IngredienteVianda();
				iv.setIngrediente(i);
				iv.setVianda(v);
				iv.setCantidad(rs.getDouble("cantidad"));
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

		return iv;
	}

	public IngredienteVianda setIngredienteVianda(IngredienteVianda ingredienteVianda) {
		PreparedStatement stmt = null;
		try {
			stmt = DbConnector.getInstancia().getConn().prepareStatement(
					"INSERT INTO ingrediente_vianda (codigoIngrediente, idVianda, cantidad) VALUES (?, ?, ?)");
			stmt.setString(1, ingredienteVianda.getIngrediente().getCodigo());
			stmt.setInt(2, ingredienteVianda.getVianda().getIdVianda());
			stmt.setDouble(3, ingredienteVianda.getCantidad());

			stmt.executeUpdate();

		} catch (SQLIntegrityConstraintViolationException e) {
			System.out.println("Ya existe esa relación ingrediente-vianda.");
			ingredienteVianda = null;

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

		return ingredienteVianda;
	}

	public void delete(String codigoIngrediente, int idVianda) {
		PreparedStatement stmtDeleteIngredienteVianda = null;
		try {
			stmtDeleteIngredienteVianda = DbConnector.getInstancia().getConn().prepareStatement(
					"DELETE FROM ingrediente_vianda WHERE codigoIngrediente = ? AND idVianda = ?");
			stmtDeleteIngredienteVianda.setString(1, codigoIngrediente);
			stmtDeleteIngredienteVianda.setInt(2, idVianda);
			stmtDeleteIngredienteVianda.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmtDeleteIngredienteVianda != null) { stmtDeleteIngredienteVianda.close(); }
				DbConnector.getInstancia().releaseConn();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void update(String codigoIngrediente, int idVianda, double cantidad) {
		PreparedStatement stmtUpdate = null;
		String sentencia = "UPDATE ingrediente_vianda SET cantidad = ? "
				+ "WHERE codigoIngrediente = ? AND idVianda = ? ";
		try {
			stmtUpdate = DbConnector.getInstancia().getConn().prepareStatement(sentencia);
			stmtUpdate.setDouble(1, cantidad);
			stmtUpdate.setString(2, codigoIngrediente);
			stmtUpdate.setInt(3, idVianda);

			stmtUpdate.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmtUpdate != null) { stmtUpdate.close(); }
				DbConnector.getInstancia().releaseConn();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public LinkedList<IngredienteVianda> getByVianda(int idVianda) {
		PreparedStatement stmt = null;
		ResultSet rs = null;
		LinkedList<IngredienteVianda> lista = new LinkedList<>();

		try {
			stmt = DbConnector.getInstancia().getConn().prepareStatement(
					"SELECT iv.codigoIngrediente, iv.cantidad, "
					+ "i.nombre AS nombreIngrediente, i.stock, i.unidadMedida "
					+ "FROM ingrediente_vianda iv, ingrediente i "
					+ "WHERE iv.codigoIngrediente = i.codigo "
					+ "AND iv.idVianda = ? ");
			stmt.setInt(1, idVianda);
			rs = stmt.executeQuery();

			if (rs != null) {
				while (rs.next()) {
					Ingrediente i = new Ingrediente();
					i.setCodigo(rs.getString("codigoIngrediente"));
					i.setNombre(rs.getString("nombreIngrediente"));
					i.setStock(rs.getDouble("stock"));
					i.setUnidadMedida(rs.getString("unidadMedida"));

					IngredienteVianda iv = new IngredienteVianda();
					iv.setIngrediente(i);
					iv.setCantidad(rs.getDouble("cantidad"));

					lista.add(iv);
				}
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
		return lista;
	}
}