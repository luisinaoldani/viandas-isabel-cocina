package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import entities.Empresa;

public class DataEmpresa {

	public LinkedList<Empresa> getAll() {
		Statement stmt = null;
		ResultSet rs = null;
		LinkedList<Empresa> empresas = new LinkedList<>();

		try {
			stmt = DbConnector.getInstancia().getConn().createStatement();
			rs = stmt.executeQuery(
					"SELECT idUsuario, nombre, apellido, email, rol, cuit, nombreNegocio, telefono, domicilio, "
					+ "diaPedidoSemanal, cantEmpleados "
					+ "FROM usuario WHERE rol = 'Empresa' ");

			if (rs != null) {
				while (rs.next()) {
					Empresa empresa = new Empresa();
					empresa.setIdUsuario(rs.getInt("idUsuario"));
					empresa.setNombre(rs.getString("nombre"));
					empresa.setApellido(rs.getString("apellido"));
					empresa.setEmail(rs.getString("email"));
					empresa.setRol(rs.getString("rol"));
					empresa.setCuit(rs.getString("cuit"));
					empresa.setNombreNegocio(rs.getString("nombreNegocio"));
					empresa.setTelefono(rs.getString("telefono"));
					empresa.setDomicilio(rs.getString("domicilio"));
					empresa.setDiaPedidoSemanal(rs.getString("diaPedidoSemanal"));
					empresa.setCantEmpleados(rs.getInt("cantEmpleados"));

					empresas.add(empresa);
				}
			}

		} catch (SQLException e) {
			throw new RuntimeException("No se pudo obtener el listado de empresas.", e);

		} finally {
			try {
				if (rs != null) { rs.close(); }
				if (stmt != null) { stmt.close(); }
				DbConnector.getInstancia().releaseConn();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return empresas;
	}

	public Empresa getById(int idUsuario) {
		Empresa empresa = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			stmt = DbConnector.getInstancia().getConn().prepareStatement(
					"SELECT idUsuario, nombre, apellido, email, rol, cuit, nombreNegocio, telefono, domicilio, "
					+ "diaPedidoSemanal, cantEmpleados "
					+ "FROM usuario WHERE idUsuario = ? AND rol = 'Empresa' ");
			stmt.setInt(1, idUsuario);
			rs = stmt.executeQuery();

			if (rs != null && rs.next()) {
				empresa = new Empresa();
				empresa.setIdUsuario(rs.getInt("idUsuario"));
				empresa.setNombre(rs.getString("nombre"));
				empresa.setApellido(rs.getString("apellido"));
				empresa.setEmail(rs.getString("email"));
				empresa.setRol(rs.getString("rol"));
				empresa.setCuit(rs.getString("cuit"));
				empresa.setNombreNegocio(rs.getString("nombreNegocio"));
				empresa.setTelefono(rs.getString("telefono"));
				empresa.setDomicilio(rs.getString("domicilio"));
				empresa.setDiaPedidoSemanal(rs.getString("diaPedidoSemanal"));
				empresa.setCantEmpleados(rs.getInt("cantEmpleados"));
			}

		} catch (SQLException e) {
			throw new RuntimeException("No se pudo obtener la empresa solicitada.", e);

		} finally {
			try {
				if (rs != null) { rs.close(); }
				if (stmt != null) { stmt.close(); }
				DbConnector.getInstancia().releaseConn();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return empresa;
	}

	public Empresa setEmpresa(Empresa empresa) {
		PreparedStatement stmt = null;
		ResultSet rs = null;

		try {
			stmt = DbConnector.getInstancia().getConn().prepareStatement(
					"INSERT INTO usuario (nombre, apellido, email, password, rol, cuit, nombreNegocio, telefono, domicilio, diaPedidoSemanal, cantEmpleados) "
					+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
					Statement.RETURN_GENERATED_KEYS);
			stmt.setString(1, empresa.getNombre());
			stmt.setString(2, empresa.getApellido());
			stmt.setString(3, empresa.getEmail());
			stmt.setString(4, empresa.getPassword());
			stmt.setString(5, empresa.getRol());
			stmt.setString(6, empresa.getCuit());
			stmt.setString(7, empresa.getNombreNegocio());
			stmt.setString(8, empresa.getTelefono());
			stmt.setString(9, empresa.getDomicilio());
			stmt.setString(10, empresa.getDiaPedidoSemanal());
			stmt.setInt(11, empresa.getCantEmpleados());

			stmt.executeUpdate();

			rs = stmt.getGeneratedKeys();
			if (rs != null && rs.next()) {
				empresa.setIdUsuario(rs.getInt(1));
			}

		} catch (SQLException e) {
			throw new RuntimeException("No se pudo guardar la empresa.", e);

		} finally {
			try {
				if (rs != null) { rs.close(); }
				if (stmt != null) { stmt.close(); }
				DbConnector.getInstancia().releaseConn();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return empresa;
	}

	public void updateById(Empresa empresa) {
		PreparedStatement stmt = null;

		try {
			stmt = DbConnector.getInstancia().getConn().prepareStatement(
					"UPDATE usuario SET nombre = ?, apellido = ?, email = ?, password = ?, "
					+ "cuit = ?, nombreNegocio = ?, telefono = ?, domicilio = ?, diaPedidoSemanal = ?, cantEmpleados = ? "
					+ "WHERE idUsuario = ?");
			stmt.setString(1, empresa.getNombre());
			stmt.setString(2, empresa.getApellido());
			stmt.setString(3, empresa.getEmail());
			stmt.setString(4, empresa.getPassword());
			stmt.setString(5, empresa.getCuit());
			stmt.setString(6, empresa.getNombreNegocio());
			stmt.setString(7, empresa.getTelefono());
			stmt.setString(8, empresa.getDomicilio());
			stmt.setString(9, empresa.getDiaPedidoSemanal());
			stmt.setInt(10, empresa.getCantEmpleados());
			stmt.setInt(11, empresa.getIdUsuario());

			stmt.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException("No se pudo actualizar la empresa.", e);

		} finally {
			try {
				if (stmt != null) { stmt.close(); }
				DbConnector.getInstancia().releaseConn();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public void deleteById(Empresa empresa) {
		PreparedStatement stmt = null;

		try {
			stmt = DbConnector.getInstancia().getConn().prepareStatement("DELETE FROM usuario WHERE idUsuario = ?");
			stmt.setInt(1, empresa.getIdUsuario());
			stmt.executeUpdate();

		} catch (SQLException e) {
			throw new RuntimeException("No se pudo eliminar la empresa.", e);

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
