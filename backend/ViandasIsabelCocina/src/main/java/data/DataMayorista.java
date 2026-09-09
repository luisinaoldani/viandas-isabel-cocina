package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import entities.Mayorista;

public class DataMayorista {

	public LinkedList<Mayorista> getAll() {
		Statement stmt = null;
		ResultSet rs = null;
		LinkedList<Mayorista> mayoristas = new LinkedList<>();

		try {
			stmt = DbConnector.getInstancia().getConn().createStatement();
			rs = stmt.executeQuery(
					"SELECT idUsuario, nombre, apellido, email, rol, cuit, nombreNegocio, telefono, domicilio "
					+ "FROM usuario WHERE rol = 'Mayorista' ");

			if (rs != null) {
				while (rs.next()) {
					Mayorista mayorista = new Mayorista();
					mayorista.setIdUsuario(rs.getInt("idUsuario"));
					mayorista.setNombre(rs.getString("nombre"));
					mayorista.setApellido(rs.getString("apellido"));
					mayorista.setEmail(rs.getString("email"));
					mayorista.setRol(rs.getString("rol"));
					mayorista.setCuit(rs.getString("cuit"));
					mayorista.setNombreNegocio(rs.getString("nombreNegocio"));
					mayorista.setTelefono(rs.getString("telefono"));
					mayorista.setDomicilio(rs.getString("domicilio"));
					
					mayoristas.add(mayorista);
				}
			}

		} catch (SQLException e) {
			throw new RuntimeException("No se pudo obtener el listado de mayoristas.", e);

		} finally {
			try {
				if (rs != null) { rs.close(); }
				if (stmt != null) { stmt.close(); }
				DbConnector.getInstancia().releaseConn();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return mayoristas;
	}

	public Mayorista getById(int idUsuario) {
		Mayorista mayorista = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			stmt = DbConnector.getInstancia().getConn().prepareStatement(
					"SELECT idUsuario, nombre, apellido, email, rol, cuit, nombreNegocio, telefono, domicilio "
					+ "FROM usuario WHERE idUsuario = ? AND rol = 'Mayorista' ");
			stmt.setInt(1, idUsuario);
			rs = stmt.executeQuery();

			if (rs != null && rs.next()) {
				mayorista = new Mayorista();
				mayorista.setIdUsuario(rs.getInt("idUsuario"));
				mayorista.setNombre(rs.getString("nombre"));
				mayorista.setApellido(rs.getString("apellido"));
				mayorista.setEmail(rs.getString("email"));
				mayorista.setRol(rs.getString("rol"));
				mayorista.setCuit(rs.getString("cuit"));
				mayorista.setNombreNegocio(rs.getString("nombreNegocio"));
				mayorista.setTelefono(rs.getString("telefono"));
				mayorista.setDomicilio(rs.getString("domicilio"));
			}

		} catch (SQLException e) {
			throw new RuntimeException("No se pudo obtener el mayorista solicitado.", e);

		} finally {
			try {
				if (rs != null) { rs.close(); }
				if (stmt != null) { stmt.close(); }
				DbConnector.getInstancia().releaseConn();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return mayorista;
	}

	public Mayorista setMayorista(Mayorista mayorista) {
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			stmt = DbConnector.getInstancia().getConn().prepareStatement(
					"INSERT INTO usuario (nombre, apellido, email, password, rol, cuit, nombreNegocio, telefono, domicilio) "
					+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)",
					Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, mayorista.getNombre());
			stmt.setString(2, mayorista.getApellido());
			stmt.setString(3, mayorista.getEmail());
			stmt.setString(4, mayorista.getPassword());
			stmt.setString(5, mayorista.getRol());
			stmt.setString(6, mayorista.getCuit());
			stmt.setString(7, mayorista.getNombreNegocio());
			stmt.setString(8, mayorista.getTelefono());
			stmt.setString(9, mayorista.getDomicilio());

			stmt.executeUpdate();

			rs = stmt.getGeneratedKeys();
			if (rs != null && rs.next()) {
				mayorista.setIdUsuario(rs.getInt(1));
			}

		} catch (SQLException e) {
			throw new RuntimeException("No se pudo guardar el mayorista.", e);

		} finally {
			try {
				if (rs != null) { rs.close(); }
				if (stmt != null) { stmt.close(); }
				DbConnector.getInstancia().releaseConn();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return mayorista;
	}

	public void updateById(Mayorista mayorista) {
		PreparedStatement stmt = null;

		try {
			stmt = DbConnector.getInstancia().getConn().prepareStatement(
					"UPDATE usuario SET nombre = ?, apellido = ?, email = ?, password = ?, "
					+ "cuit = ?, nombreNegocio = ?, telefono = ?, domicilio = ? "
					+ "WHERE idUsuario = ?");
			stmt.setString(1, mayorista.getNombre());
			stmt.setString(2, mayorista.getApellido());
			stmt.setString(3, mayorista.getEmail());
			stmt.setString(4, mayorista.getPassword());
			stmt.setString(5, mayorista.getCuit());
			stmt.setString(6, mayorista.getNombreNegocio());
			stmt.setString(7, mayorista.getTelefono());
			stmt.setString(8, mayorista.getDomicilio());
			stmt.setInt(9, mayorista.getIdUsuario());

			stmt.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException("No se pudo actualizar el mayorista.", e);

		} finally {
			try {
				if (stmt != null) { stmt.close(); }
				DbConnector.getInstancia().releaseConn();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void deleteById(Mayorista mayorista) {
		PreparedStatement stmt = null;

		try {
			stmt = DbConnector.getInstancia().getConn().prepareStatement("DELETE FROM usuario WHERE idUsuario = ?");
			stmt.setInt(1, mayorista.getIdUsuario());
			stmt.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException("No se pudo eliminar el mayorista.", e);

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