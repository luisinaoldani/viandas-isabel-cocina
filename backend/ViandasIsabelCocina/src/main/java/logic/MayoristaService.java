package logic;

import data.DataMayorista;
import entities.Mayorista;
import java.util.LinkedList;

public class MayoristaService {

	private DataMayorista dataMayorista = new DataMayorista();

	public LinkedList<Mayorista> listar() {
		return dataMayorista.getAll();
	}

	public Mayorista buscarPorId(int idUsuario) {
		return dataMayorista.getById(idUsuario);
	}

	public void guardar(String id, String nombre, String apellido, String email, String password,
			String cuit, String nombreNegocio, String telefono, String domicilio) {

		if (id == null || id.isEmpty()) {

			Mayorista nuevo = new Mayorista(nombre, apellido, email, password, "Mayorista", cuit, nombreNegocio, telefono, domicilio);
			dataMayorista.setMayorista(nuevo);

		} else {

			int idMay = Integer.parseInt(id);
			Mayorista existente = dataMayorista.getById(idMay);
			existente.setNombre(nombre);
			existente.setApellido(apellido);
			existente.setEmail(email);
			existente.setPassword(password);
			existente.setCuit(cuit);
			existente.setNombreNegocio(nombreNegocio);
			existente.setTelefono(telefono);
			existente.setDomicilio(domicilio);
			dataMayorista.updateById(existente);
		}
	}

	public void eliminar(int idUsuario) {
		Mayorista mayorista = dataMayorista.getById(idUsuario);
		dataMayorista.deleteById(mayorista);
	}
}
