package logic;

import data.DataIngrediente;
import data.DataIngredienteVianda;
import data.DataVianda;
import entities.Ingrediente;
import entities.IngredienteVianda;
import entities.Vianda;
import java.util.LinkedList;

public class IngredienteViandaService {

    private DataIngredienteVianda dataIngredienteVianda = new DataIngredienteVianda();
    private DataIngrediente dataIngrediente = new DataIngrediente();
    private DataVianda dataVianda = new DataVianda();

    public LinkedList<IngredienteVianda> listar() {
        return dataIngredienteVianda.getAll();
    }

    public IngredienteVianda buscarPorId(String codigoIngrediente, int idVianda) {
        return dataIngredienteVianda.getById(codigoIngrediente, idVianda);
    }

    public LinkedList<Vianda> listarViandas() {
        return dataVianda.getAll();
    }

    public LinkedList<Ingrediente> listarIngredientes() {
        return dataIngrediente.getAll();
    }

    public void guardar(String codigoIngrediente, int idVianda, Double cantidad) {

        IngredienteVianda existente = dataIngredienteVianda.getById(codigoIngrediente, idVianda);

        if (existente == null) {

            Vianda vianda = dataVianda.getById(idVianda);
            Ingrediente ingrediente = dataIngrediente.getById(codigoIngrediente);
            IngredienteVianda nuevo = new IngredienteVianda(vianda, ingrediente, cantidad);
            dataIngredienteVianda.setIngredienteVianda(nuevo);

        } else {
            dataIngredienteVianda.update(codigoIngrediente, idVianda, cantidad);
        }
    }

    public void eliminar(String codigoIngrediente, int idVianda) {
        dataIngredienteVianda.delete(codigoIngrediente, idVianda);
    }
}