package entities;

public class Ingrediente {
	private int codigo;
	private String nombre;
	private Double stock;
	private String unidadMedida;
	
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Double getStock() {
		return stock;
	}
	public void setStock(Double stock) {
		this.stock = stock;
	}
	public String getUnidadMedida() {
		return unidadMedida;
	}
	public void setUnidadMedida(String unidadMedida) {
		this.unidadMedida = unidadMedida;
	}
	
	public Ingrediente(){	
	}
	
	public Ingrediente(int codigo, String nombre, Double stock, String unidadMedida) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.stock = stock;
        this.unidadMedida = unidadMedida;
    }
	
}
