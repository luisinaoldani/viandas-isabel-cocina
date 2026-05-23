package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import entities.Vianda;
import entities.Ingrediente;
import entities.IngredienteVianda;

public class DataVianda {
	public LinkedList<Vianda> getAll(){
		Statement stmt = null;
		ResultSet rs = null;
		LinkedList<Vianda> vianda = new LinkedList<>();
		
		try {
			stmt = DbConnector.getInstancia().getConn().createStatement();
			rs = stmt.executeQuery("SELECT idVianda, nombre, descripcion, precioUnitario, tipo, activa FROM vianda");
			
			if(rs != null) {
				while(rs.next()) {
					Vianda v = new Vianda();
					v.setIdVianda(rs.getInt("idVianda"));
					v.setNombre(rs.getString("nombre"));
					v.setDescripcion(rs.getString("descripcion"));
					v.setPrecioUnitario(rs.getFloat("precioUnitario"));
					v.setTipo(rs.getString("tipo"));
					v.setActiva(rs.getBoolean("activa"));
					
					this.addReceta(v);
					
					vianda.add(v);
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
		
		return vianda;
	}
	
	private void addReceta(Vianda v) {
	    PreparedStatement stmt = null;
	    ResultSet rs = null;
	    
	    String sentencia = "SELECT iv.cantidad, i.codigo, i.nombre, i.stock, i.unidadMedida " +
	                 "FROM Ingrediente_vianda iv " +
	                 "INNER JOIN Ingrediente i ON iv.idIngrediente = i.codigo " +
	                 "WHERE iv.idVianda = ?";
	                 
	    try {
	        stmt = DbConnector.getInstancia().getConn().prepareStatement(sentencia);
	        stmt.setInt(1, v.getIdVianda());
	        rs = stmt.executeQuery();
	        
	        if(rs != null) {
	            while (rs.next()) {
	                Ingrediente ing = new Ingrediente();
	                ing.setCodigo(rs.getInt("codigo")); 
	                ing.setNombre(rs.getString("nombre"));
	                ing.setStock(rs.getFloat("stock"));
	                ing.setUnidadMedida(rs.getString("unidadMedida"));
	                
	                float cantidad = rs.getFloat("cantidad");
	                
	                v.agregarIngrediente(ing, cantidad);
	            }
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        
	    } finally {
	    	try {
				if(rs != null) {rs.close();}
				if(stmt != null) {stmt.close();}

			} catch (SQLException e) {
				e.printStackTrace();
			}
	    }
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
				v.setPrecioUnitario(rs.getFloat("precioUnitario"));
				v.setTipo(rs.getString("tipo"));
				v.setActiva(rs.getBoolean("activa"));
				
				this.addReceta(v);
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
	        stmt.setFloat(3, vianda.getPrecioUnitario());
	        stmt.setString(4, vianda.getTipo());
	        stmt.setBoolean(5, vianda.isActiva());

	        stmt.executeUpdate();

	        rs = stmt.getGeneratedKeys();
	        if (rs != null && rs.next()) {
	            int nuevoId = rs.getInt(1);
	            vianda.setIdVianda(nuevoId);
	            
	            this.saveReceta(vianda);
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
	
	private void saveReceta(Vianda v) {
	
	    PreparedStatement stmt = null;
	    try {
	        String sentencia = "INSERT INTO Ingrediente_vianda (idVianda, idIngrediente, cantidad) VALUES (?, ?, ?)";
	        stmt = DbConnector.getInstancia().getConn().prepareStatement(sentencia);

	        for (int i = 0; i < v.getReceta().size(); i++) {
	            IngredienteVianda item = v.getReceta().get(i);
	            
	            stmt.setInt(1, v.getIdVianda()); 
	            stmt.setInt(2, item.getIngrediente().getCodigo()); 
	            stmt.setFloat(3, item.getCantidad());
	            stmt.executeUpdate(); 
	        }
	        
	    } catch (SQLException e) {
	        e.printStackTrace();
	        
	    } finally {
	        try {
	            if (stmt != null) { stmt.close(); }
	            
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	}
	
	public void deleteById(Vianda vianda) {
		PreparedStatement stmt = null;
		 try {
		        stmt = DbConnector.getInstancia().getConn().prepareStatement("DELETE FROM vianda WHERE idVianda = ?");
		        stmt.setInt(1, vianda.getIdVianda());
		        
		        stmt.executeUpdate();

		 } catch (SQLException e) {
			 e.printStackTrace();
		        
		 } finally {
			 try {
		         if (stmt != null) {stmt.close();}
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
				+ "activa = ?"
				+ "WHERE id = ?";
		 try {
		        stmtUpdate = DbConnector.getInstancia().getConn().prepareStatement(sentencia);
		        stmtUpdate.setString(1, vianda.getNombre());
		        stmtUpdate.setString(2, vianda.getDescripcion());
		        stmtUpdate.setFloat(3, vianda.getPrecioUnitario());
		        stmtUpdate.setString(4, vianda.getTipo());
		        stmtUpdate.setBoolean(5, vianda.isActiva());
		        
		        stmtUpdate.executeUpdate();
		        
		        stmtDelete = DbConnector.getInstancia().getConn().prepareStatement("DELETE FROM Ingrediente_vianda WHERE idVianda = ?");
		        stmtDelete.setInt(1, vianda.getIdVianda());
		        
		        stmtDelete.executeUpdate();
		       
		        this.saveReceta(vianda);

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