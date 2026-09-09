package data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import entities.Administrador;

public class DataAdministrador {

	public LinkedList<Administrador> getAll() {
		Statement stmt = null;
		ResultSet rs = null;
		LinkedList<Administrador> administradores = new LinkedList<>();

		try {
			stmt = DbConnector.getInstancia().getConn().createStatement();
			rs = stmt.executeQuery(
					"SELECT idUsuario, nombre, apellido, email, rol FROM usuario");

			if (rs != null) {
				while (rs.next()) {
					Administrador admin = new Administrador();
					admin.setIdUsuario(rs.getInt("idUsuario"));
					admin.setNombre(rs.getString("nombre"));
					admin.setApellido(rs.getString("apellido"));
					admin.setEmail(rs.getString("email"));
					admin.setRol(rs.getString("rol"));

					administradores.add(admin);
				}
			}

		} catch (SQLException e) {
			throw new RuntimeException("No se pudo obtener el listado de administradores.", e);

		} finally {
			try {
				if (rs != null) { rs.close(); }
				if (stmt != null) { stmt.close(); }
				DbConnector.getInstancia().releaseConn();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return administradores;
	}

	public Administrador getById(int idUsuario) {
		Administrador admin = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			stmt = DbConnector.getInstancia().getConn().prepareStatement(
					"SELECT idUsuario, nombre, apellido, email, rol "
					+ "FROM usuario "
					+ "WHERE idUsuario = ?");
			stmt.setInt(1, idUsuario);
			rs = stmt.executeQuery();

			if (rs != null && rs.next()) {
				admin = new Administrador();
				admin.setIdUsuario(rs.getInt("idUsuario"));
				admin.setNombre(rs.getString("nombre"));
				admin.setApellido(rs.getString("apellido"));
				admin.setEmail(rs.getString("email"));
				admin.setRol(rs.getString("rol"));

			}

		} catch (SQLException e) {
			throw new RuntimeException("No se pudo obtener el administrador solicitado.", e);

		} finally {
			try {
				if (rs != null) { rs.close(); }
				if (stmt != null) { stmt.close(); }
				DbConnector.getInstancia().releaseConn();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return admin;
	}

	public Administrador setAdministrador(Administrador admin) {
		Connection conn = DbConnector.getInstancia().getConn();
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			stmt = conn.prepareStatement(
					"INSERT INTO usuario (nombre, apellido, email, password, rol) VALUES (?, ?, ?, ?, ?)",
					Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, admin.getNombre());
			stmt.setString(2, admin.getApellido());
			stmt.setString(3, admin.getEmail());
			stmt.setString(4, admin.getPassword());
			stmt.setString(5, admin.getRol());
			
			stmt.executeUpdate();

			rs = stmt.getGeneratedKeys();
			if (rs != null && rs.next()) {
				int nuevoId = rs.getInt(1);
				admin.setIdUsuario(nuevoId);
			}

		} catch (SQLException e) {
			throw new RuntimeException("No se pudo guardar el administrador.", e);
		} finally {
			try {
				if (rs != null) { rs.close(); }
				if (stmt != null) { stmt.close(); }
				DbConnector.getInstancia().releaseConn();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return admin;
	}

	public void updateById(Administrador admin) {
		Connection conn = DbConnector.getInstancia().getConn();
		PreparedStatement stmt = null;

		try {
			conn.setAutoCommit(false);

			stmt = conn.prepareStatement(
					"UPDATE usuario SET nombre = ?, apellido = ?, email = ?, password = ?, rol = ? WHERE idUsuario = ?");
			stmt.setString(1, admin.getNombre());
			stmt.setString(2, admin.getApellido());
			stmt.setString(3, admin.getEmail());
			stmt.setString(4, admin.getPassword());
			stmt.setString(5, admin.getRol());
			stmt.setInt(6, admin.getIdUsuario());
			
			
			stmt.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException("No se pudo actualizar el administrador.", e);
		} finally {
			try {
				if (stmt != null) { stmt.close(); }
				DbConnector.getInstancia().releaseConn();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void deleteById(Administrador admin) {
		PreparedStatement stmt = null;

		try {
			stmt = DbConnector.getInstancia().getConn().prepareStatement("DELETE FROM usuario WHERE idUsuario = ?");
			stmt.setInt(1, admin.getIdUsuario());
			stmt.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException("No se pudo eliminar el administrador.", e);
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
