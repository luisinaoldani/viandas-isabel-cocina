package data;

import java.sql.*;

public class DbConnector {

	private static DbConnector instancia;
	private int conectados = 0;
	private Connection conn = null;
	
	private DbConnector() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			throw new RuntimeException("No se encontró el driver de la base de datos.", e);
		}
	}
	
	public static DbConnector getInstancia() {
		if (instancia == null) {
			instancia = new DbConnector();
		}
		return instancia;
	}
	
	public Connection getConn() {
		try {
			if(conn == null || conn.isClosed()) {
				conn = DriverManager.getConnection("jdbc:mysql://localhost/viandas","vic_user","tpjava2026");
				conectados = 0;
			}
		} catch (SQLException e) {
			throw new RuntimeException("No se pudo conectar con la base de datos.", e);
		}
		
		conectados++;
		return conn;
	}
	
	public void releaseConn() {
		conectados--;
		
		try {
			if (conectados <= 0 && conn != null) {
				conn.close();
				conn = null;
			}
		} catch (SQLException e) {
			 throw new RuntimeException("No se pudo cerrar la conexión con la base de datos.", e);
		}
	}

}
