package entities;

public class IngredienteVianda {
	private Vianda vianda;
    private Ingrediente ingrediente;
    private float cantidad;
    
	public Vianda getVianda() {
		return vianda;
	}
	public void setVianda(Vianda vianda) {
		this.vianda = vianda;
	}
	public Ingrediente getIngrediente() {
		return ingrediente;
	}
	public void setIngrediente(Ingrediente ingrediente) {
		this.ingrediente = ingrediente;
	}
	public float getCantidad() {
		return cantidad;
	}
	public void setCantidad(float cantidad) {
		this.cantidad = cantidad;
	}

	public IngredienteVianda() {
	}
	
	public IngredienteVianda(Vianda vianda, Ingrediente ingrediente, float cantidad) {
        this.vianda = vianda;
        this.ingrediente = ingrediente;
        this.cantidad = cantidad;
	}
}

