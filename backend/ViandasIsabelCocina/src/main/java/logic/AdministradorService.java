package logic;

import data.DataAdministrador;
import entities.Administrador;
import java.util.LinkedList;

public class AdministradorService {

	private DataAdministrador dataAdministrador = new DataAdministrador();

	public LinkedList<Administrador> listar() {
		return dataAdministrador.getAll();
	}

	public Administrador buscarPorId(int idAdministrador) {
		return dataAdministrador.getById(idAdministrador);
	}

	public void guardar(String id, String nombre, String apellido, String email, String password) {

		if (id == null || id.isEmpty()) {

			Administrador nuevo = new Administrador(nombre, apellido, email, password, "Administrador");
			dataAdministrador.setAdministrador(nuevo);

		} else {

			int idAdmin = Integer.parseInt(id);
			Administrador existente = dataAdministrador.getById(idAdmin);
			existente.setNombre(nombre);
			existente.setApellido(apellido);
			existente.setEmail(email);
			existente.setPassword(password);
			existente.setRol("Administrador");
			dataAdministrador.updateById(existente);
		}
	}

	public void eliminar(int idAdministrador) {
		Administrador admin = dataAdministrador.getById(idAdministrador);
		dataAdministrador.deleteById(admin);
	}
}
