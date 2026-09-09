package logic;

import data.DataEmpresa;
import entities.Empresa;
import java.util.LinkedList;

public class EmpresaService {

	private DataEmpresa dataEmpresa = new DataEmpresa();

	public LinkedList<Empresa> listar() {
		return dataEmpresa.getAll();
	}

	public Empresa buscarPorId(int idUsuario) {
		return dataEmpresa.getById(idUsuario);
	}

	public void guardar(String id, String nombre, String apellido, String email, String password,
			String cuit, String nombreNegocio, String telefono, String domicilio,
			String diaPedidoSemanal, int cantEmpleados) {

		if (id == null || id.isEmpty()) {

			Empresa nueva = new Empresa(nombre, apellido, email, password, "Empresa", cuit, nombreNegocio, telefono,
					domicilio, diaPedidoSemanal, cantEmpleados);
			dataEmpresa.setEmpresa(nueva);

		} else {

			int idEmp = Integer.parseInt(id);
			Empresa existente = dataEmpresa.getById(idEmp);
			existente.setNombre(nombre);
			existente.setApellido(apellido);
			existente.setEmail(email);
			existente.setPassword(password);
			existente.setCuit(cuit);
			existente.setNombreNegocio(nombreNegocio);
			existente.setTelefono(telefono);
			existente.setDomicilio(domicilio);
			existente.setDiaPedidoSemanal(diaPedidoSemanal);
			existente.setCantEmpleados(cantEmpleados);
			dataEmpresa.updateById(existente);
		}
	}

	public void eliminar(int idUsuario) {
		Empresa empresa = dataEmpresa.getById(idUsuario);
		dataEmpresa.deleteById(empresa);
	}
}
