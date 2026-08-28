package logic;

import data.DataIngrediente;
import entities.Ingrediente;
import java.util.LinkedList;


public class IngredienteService {
	
	private DataIngrediente dataIngrediente = new DataIngrediente();
	
	public LinkedList<Ingrediente> listar(){
		return dataIngrediente.getAll();
	}

	public Ingrediente buscarPorId(int idIngrediente) {
		return dataIngrediente.getById(idIngrediente);
	}
	
	public void guardar(int idIngrediente, String codigo, String nombre, Double stock, String unidadMedida) {
		Ingrediente existente = dataIngrediente.getById(idIngrediente);
		
		if (existente == null) {
			Ingrediente nuevo = new Ingrediente(codigo, nombre, stock, unidadMedida);
			dataIngrediente.setIngrediente(nuevo);
		} else {
			existente.setCodigo(codigo);
			existente.setNombre(nombre);
			existente.setStock(stock);
			existente.setUnidadMedida(unidadMedida);
			dataIngrediente.updateByCodigo(existente);
		}
	}
	
	public void eliminar(int idIngrediente) {
		Ingrediente ingrediente = dataIngrediente.getById(idIngrediente);
		dataIngrediente.deleteByCodigo(ingrediente);
	}
}
