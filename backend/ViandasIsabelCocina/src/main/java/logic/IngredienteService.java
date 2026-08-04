package logic;

import data.DataIngrediente;
import entities.Ingrediente;
import java.util.LinkedList;


public class IngredienteService {
	
	private DataIngrediente dataIngrediente = new DataIngrediente();
	
	public LinkedList<Ingrediente> listar(){
		return dataIngrediente.getAll();
	}

	public Ingrediente buscarPorCodigo(String codigo) {
		return dataIngrediente.getById(codigo);
	}
	
	public void guardar(String codigo, String nombre, Double stock, String unidadMedida) {
		Ingrediente existente = dataIngrediente.getById(codigo);
		
		if (existente == null) {
			Ingrediente nuevo = new Ingrediente(codigo, nombre, stock, unidadMedida);
			dataIngrediente.setIngrediente(nuevo);
		} else {
			existente.setNombre(nombre);
			existente.setStock(stock);
			existente.setUnidadMedida(unidadMedida);
			dataIngrediente.updateByCodigo(existente);
		}
	}
	
	public void eliminar(String codigo) {
		Ingrediente ingrediente = dataIngrediente.getById(codigo);
		dataIngrediente.deleteByCodigo(ingrediente);
	}
}
