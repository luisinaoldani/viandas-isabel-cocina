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

    public IngredienteVianda buscarPorId(int idIngrediente, int idVianda) {
        return dataIngredienteVianda.getById(idIngrediente, idVianda);
    }

    public LinkedList<Vianda> listarViandas() {
        return dataVianda.getAll();
    }

    public LinkedList<Ingrediente> listarIngredientes() {
        return dataIngrediente.getAll();
    }

    public void guardar(int idIngrediente, int idVianda, Double cantidad) {

        IngredienteVianda existente = dataIngredienteVianda.getById(idIngrediente, idVianda);

        if (existente == null) {

            Vianda vianda = dataVianda.getById(idVianda);
            Ingrediente ingrediente = dataIngrediente.getById(idIngrediente);
            IngredienteVianda nuevo = new IngredienteVianda(vianda, ingrediente, cantidad);
            dataIngredienteVianda.setIngredienteVianda(nuevo);

        } else {
            dataIngredienteVianda.update(idIngrediente, idVianda, cantidad);
        }
    }

    public void eliminar(int idIngrediente, int idVianda) {
        dataIngredienteVianda.delete(idIngrediente, idVianda);
    }
}