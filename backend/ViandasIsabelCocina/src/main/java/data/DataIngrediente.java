package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import entities.*;

public class DataIngrediente {
	 
	public LinkedList <Ingrediente> getAll(){
		Statement stmt = null;
		ResultSet rs = null;
		LinkedList <Ingrediente> ingrediente = new LinkedList<>();
		
		try {
			stmt = DbConnector.getInstancia().getConn().createStatement();
			rs = stmt.executeQuery("SELECT idIngrediente, codigo, nombre, stock, unidadMedida FROM ingrediente");
			
			if(rs != null) {
				while(rs.next()) {
					Ingrediente i = new Ingrediente();
					i.setIdIngrediente(rs.getInt("idIngrediente"));
					i.setCodigo(rs.getString("codigo"));
					i.setNombre(rs.getString("nombre"));
					i.setStock(rs.getDouble("stock"));
					i.setUnidadMedida(rs.getString("unidadMedida"));
					
					//agrega un ingrediente :)
					
					ingrediente.add(i);
				}
		    }
		
	} catch (SQLException e) {
		e.printStackTrace();	
		
	} finally {
		try {
			if(rs != null) {rs.close();}
			if(stmt != null) {stmt.close();}
			DbConnector.getInstancia().releaseConn();
		} catch (SQLException e) {
			e.printStackTrace();
		}
			
		}
		return ingrediente;
	}


public Ingrediente getById( int idIngrediente ) {
	Ingrediente i = null;
	PreparedStatement stmt = null;
	ResultSet rs = null;
	
	try {
		stmt = DbConnector.getInstancia().getConn().prepareStatement("SELECT idIngrediente, codigo, nombre, stock, unidadMedida FROM ingrediente where id = ? ");
		stmt.setInt(1, idIngrediente);
		rs = stmt.executeQuery();
		
		if(rs != null && rs.next()) {
			i = new Ingrediente();
			i.setIdIngrediente(rs.getInt("idIngrediente"));
			i.setCodigo(rs.getString("codigo"));
			i.setStock(rs.getDouble("stock"));
			i.setNombre(rs.getString("nombre"));
			i.setUnidadMedida(rs.getString("unidadMedida"));
			
		}
	} catch (SQLException e) {
		e.printStackTrace();
		
	} finally {
		try {
			if(rs != null) {rs.close();}
			if(stmt != null) {stmt.close();}
			DbConnector.getInstancia().releaseConn();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	return i;
}

public Ingrediente setIngrediente(Ingrediente ingrediente) {
	PreparedStatement stmt = null;
	ResultSet rs = null;
	try {
		stmt = DbConnector.getInstancia().getConn().prepareStatement("INSERT INTO ingrediente (codigo, nombre, stock, unidadMedida) VALUES (?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
		stmt.setString(1, ingrediente.getCodigo());
		stmt.setString(2, ingrediente.getNombre());
		stmt.setDouble(3, ingrediente.getStock());
		stmt.setString(4, ingrediente.getUnidadMedida());
		
		stmt.executeUpdate();
		
		rs= stmt.getGeneratedKeys();
		if (rs != null && rs.next()) {
			int nuevoId = rs.getInt(1);
			ingrediente.setIdIngrediente(nuevoId);
		}
	} catch (SQLException e) {
		e.printStackTrace();
	} finally {
		try {
			if(rs != null) {rs.close();}
			if(stmt != null) {stmt.close();}
			DbConnector.getInstancia().releaseConn();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	return ingrediente;
}

public void deleteByCodigo(Ingrediente ingrediente) {
	PreparedStatement stmtDeleteIngrediente = null;
	try {
		stmtDeleteIngrediente = DbConnector.getInstancia().getConn().prepareStatement("DELETE FROM ingrediente WHERE idIngrediente = ?");
		stmtDeleteIngrediente.setInt(1, ingrediente.getIdIngrediente());
		stmtDeleteIngrediente.executeUpdate();
		
	} catch (SQLException e) {
		e.printStackTrace();
	} finally {
		try {
			if (stmtDeleteIngrediente != null) { stmtDeleteIngrediente.close();}
			DbConnector.getInstancia().releaseConn();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}

public void updateByCodigo(Ingrediente ingrediente) {
	PreparedStatement stmtUpdate = null;
	String sentencia = "UPDATE ingrediente SET codigo = ?,"
			+ "nombre = ?,"
			+ "stock = ?,"
			+ "unidadMedida = ?"
			+ "WHERE idIngrediente = ?";
	 try {
	        stmtUpdate = DbConnector.getInstancia().getConn().prepareStatement(sentencia);
	        stmtUpdate.setString(1, ingrediente.getCodigo());
	        stmtUpdate.setString(2, ingrediente.getNombre());
	        stmtUpdate.setDouble(3, ingrediente.getStock());
	        stmtUpdate.setString(4, ingrediente.getUnidadMedida());
	        stmtUpdate.setInt(5, ingrediente.getIdIngrediente());
	        
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

}


