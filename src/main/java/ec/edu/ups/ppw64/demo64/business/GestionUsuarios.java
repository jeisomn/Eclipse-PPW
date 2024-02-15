package ec.edu.ups.ppw64.demo64.business;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import ec.edu.ups.ppw64.demo64.dao.CarritoDAO;
import ec.edu.ups.ppw64.demo64.dao.UsuarioDAO;
import ec.edu.ups.ppw64.demo64.model.Carrito;
import ec.edu.ups.ppw64.demo64.model.Credenciales;
import ec.edu.ups.ppw64.demo64.model.Usuario;
import ec.edu.ups.ppw64.demo64.model.DetalleCarrito;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

@Stateless
public class GestionUsuarios {
	
	@Inject
	private UsuarioDAO daoUsuario;
	
	@Inject
	private CarritoDAO daoCarrito;

	public void guardarCuentas(Usuario Usuario) {
		Usuario cue = daoUsuario.read(Usuario.getCodigo());
		if (cue != null) {
			daoUsuario.update(Usuario);
		} else {
			daoUsuario.insert(Usuario);
		}
	}

	public void actualizarCuenta(Usuario Usuario) throws Exception {
		Usuario cue = daoUsuario.read(Usuario.getCodigo());
		if (cue != null) {
			daoUsuario.update(Usuario);
		} else {
			throw new Exception("Usuario no existe");
		}
	}

	public void borrarCuenta(int codigo) {

		daoUsuario.remove(codigo);
	}

	public List<Usuario> getCuentas() {
		return daoUsuario.getAll();
	}
	
	public Usuario getCuentaByCodigo(int codigo) {
		return daoUsuario.getCuentaPorCodigo(codigo);
	}
	
	public Carrito getCarritoByCodigo(int codigo) {
		return daoCarrito.getCarritoById(codigo);
	}

	public boolean validarCredenciales(Credenciales credenciales) {
		return daoUsuario.validarCredenciales(credenciales);
	}

	public Usuario obtenerUsuarioLogueado() {
		return daoUsuario.obtenerUsuarioLogueado();
	}
}
