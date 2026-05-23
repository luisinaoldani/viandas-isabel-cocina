package entities;

import java.util.LinkedList;

public class Vianda {
	private int idVianda;
	private String nombre;
	private String descripcion;
	private float precioUnitario;
	private String tipo;
	private boolean activa;
	
	private LinkedList <IngredienteVianda> receta = new LinkedList<>();
	
	public int getIdVianda() {
		return idVianda;
	}
	public void setIdVianda(int idVianda) {
		this.idVianda = idVianda;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public float getPrecioUnitario() {
		return precioUnitario;
	}
	public void setPrecioUnitario(float precioUnitario) {
		this.precioUnitario = precioUnitario;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public boolean isActiva() {
		return activa;
	}
	public void setActiva(boolean activa) {
		this.activa = activa;
	}
	
	public LinkedList<IngredienteVianda> getReceta() {
		return receta;
	}
	public void setReceta(LinkedList<IngredienteVianda> receta) {
		this.receta = receta;
	}
	
	public Vianda() {
		this.receta = new LinkedList<>();
	}
	
	public Vianda(String nombre, String descripcion, float precioUnitario, String tipo, boolean activa) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precioUnitario = precioUnitario;
        this.tipo = tipo;
        this.activa = activa;
        this.receta = new LinkedList<>();
    }
	
	public void agregarIngrediente(Ingrediente ingrediente, float cantidad) {
        IngredienteVianda nuevoItem = new IngredienteVianda(this, ingrediente, cantidad);
        this.receta.add(nuevoItem);
    }
}
