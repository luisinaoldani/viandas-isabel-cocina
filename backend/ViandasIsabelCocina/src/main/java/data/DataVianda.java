package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import entities.*;

public class DataVianda {
	
	public LinkedList<Vianda> getAll(){
		Statement stmt = null;
		ResultSet rs = null;
		LinkedList<Vianda> viandas = new LinkedList<>();
		
		try {
			stmt = DbConnector.getInstancia().getConn().createStatement();
			rs = stmt.executeQuery("SELECT idVianda, nombre, descripcion, precioUnitario, tipo, activa FROM vianda");
			
			if(rs != null) {
				while(rs.next()) {
					Vianda v = new Vianda();
					v.setIdVianda(rs.getInt("idVianda"));
					v.setNombre(rs.getString("nombre"));
					v.setDescripcion(rs.getString("descripcion"));
					v.setPrecioUnitario(rs.getDouble("precioUnitario"));
					v.setTipo(rs.getString("tipo"));
					v.setActiva(rs.getBoolean("activa"));
					
					
					viandas.add(v);
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
		
		return viandas;
	}

	
	public Vianda getById(int idVianda) {
		Vianda v = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		
		try {
			stmt = DbConnector.getInstancia().getConn().prepareStatement(
					"SELECT idVianda, nombre, descripcion, precioUnitario, tipo, activa FROM vianda where idVianda = ?");
			stmt.setInt(1, idVianda);
			rs = stmt.executeQuery();
			
			if(rs != null && rs.next()) {
				v = new Vianda();
				v.setIdVianda(rs.getInt("idVianda"));
				v.setNombre(rs.getString("nombre"));
				v.setDescripcion(rs.getString("descripcion"));
				v.setPrecioUnitario(rs.getDouble("precioUnitario"));
				v.setTipo(rs.getString("tipo"));
				v.setActiva(rs.getBoolean("activa"));
				
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
		
		return v;
	}
	
	public Vianda setVianda(Vianda vianda) {
	    PreparedStatement stmt = null;
	    ResultSet rs = null;
	    try {
	        stmt = DbConnector.getInstancia().getConn().prepareStatement(
	            "INSERT INTO vianda (nombre, descripcion, precioUnitario, tipo, activa) VALUES (?, ?, ?, ?, ?)", 
	            Statement.RETURN_GENERATED_KEYS
	        );
	        stmt.setString(1, vianda.getNombre());
	        stmt.setString(2, vianda.getDescripcion());
	        stmt.setDouble(3, vianda.getPrecioUnitario());
	        stmt.setString(4, vianda.getTipo());
	        stmt.setBoolean(5, vianda.isActiva());

	        stmt.executeUpdate();

	        rs = stmt.getGeneratedKeys();
	        if (rs != null && rs.next()) {
	            int nuevoId = rs.getInt(1);
	            vianda.setIdVianda(nuevoId);
	            
	        }

	    } catch (SQLException e) {
	        e.printStackTrace();
	        
	    } finally {
	        try {
	        	if(rs != null) {rs.close();}
	            if (stmt != null) {stmt.close();}
	            DbConnector.getInstancia().releaseConn();
	            
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	    
	    return vianda;
	}
	

	public void deleteById(Vianda vianda) {
	    PreparedStatement stmtDeleteVianda = null;
	    try {

	        stmtDeleteVianda = DbConnector.getInstancia().getConn().prepareStatement("DELETE FROM vianda WHERE idVianda = ?");
	        stmtDeleteVianda.setInt(1, vianda.getIdVianda());
	        stmtDeleteVianda.executeUpdate();

	    } catch (SQLException e) {
	        e.printStackTrace();
	    } finally {
	        try {
	            if (stmtDeleteVianda != null) { stmtDeleteVianda.close(); }
	            DbConnector.getInstancia().releaseConn();
	        } catch (SQLException e) {
	            e.printStackTrace();   
	        }
	    }
	}
	
	public void updateById(Vianda vianda) {
		PreparedStatement stmtUpdate = null;
		PreparedStatement stmtDelete = null;
		String sentencia = "UPDATE vianda SET nombre = ?,"
				+ "descripcion = ?,"
				+ "precioUnitario = ?,"
				+ "tipo = ?,"
				+ "activa = ? "
				+ "WHERE idVianda = ? ";
		 try {
		        stmtUpdate = DbConnector.getInstancia().getConn().prepareStatement(sentencia);
		        stmtUpdate.setString(1, vianda.getNombre());
		        stmtUpdate.setString(2, vianda.getDescripcion());
		        stmtUpdate.setDouble(3, vianda.getPrecioUnitario());
		        stmtUpdate.setString(4, vianda.getTipo());
		        stmtUpdate.setBoolean(5, vianda.isActiva());
		        stmtUpdate.setInt(6, vianda.getIdVianda());
		        
		        stmtUpdate.executeUpdate();
		        
		        
		    } catch (SQLException e) {
		        e.printStackTrace();
		    } finally {
		        try {
		            if (stmtUpdate != null) { stmtUpdate.close(); }
		            if (stmtDelete != null) { stmtDelete.close(); }
		            DbConnector.getInstancia().releaseConn();
		        } catch (SQLException e) {
		            e.printStackTrace();
		        }
		    }
		
	}


}