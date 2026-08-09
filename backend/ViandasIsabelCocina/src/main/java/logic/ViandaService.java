package logic;

import data.DataIngredienteVianda;
import data.DataVianda;
import entities.IngredienteVianda;
import entities.Vianda;
import java.util.LinkedList;

public class ViandaService {

    private DataVianda dataVianda = new DataVianda();
    private DataIngredienteVianda dataIngredienteVianda = new DataIngredienteVianda();

    public LinkedList<Vianda> listar() {
        return dataVianda.getAll();
    }

    public Vianda buscarPorId(int idVianda) {
        return dataVianda.getById(idVianda);
    }

    public void guardar(String id, String nombre, String descripcion, Double precioUnitario, String tipo, boolean activa) {

        if (id == null || id.isEmpty()) {

            Vianda nueva = new Vianda(nombre, descripcion, precioUnitario, tipo, activa);
            dataVianda.setVianda(nueva);

        } else {

            int idV = Integer.parseInt(id);
            Vianda existe = dataVianda.getById(idV);
            existe.setNombre(nombre);
            existe.setDescripcion(descripcion);
            existe.setPrecioUnitario(precioUnitario);
            existe.setTipo(tipo);
            existe.setActiva(activa);
            dataVianda.updateById(existe);
        }
    }

    public void eliminar(int idVianda) {
        Vianda vianda = dataVianda.getById(idVianda);
        dataVianda.deleteById(vianda);
    }

    public Vianda detalle(int idVianda) {
        Vianda vianda = dataVianda.getById(idVianda);
        LinkedList<IngredienteVianda> receta = dataIngredienteVianda.getByVianda(idVianda);
        vianda.setReceta(receta);
        return vianda;
    }
}