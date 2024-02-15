package ec.edu.ups.ppw64.demo64.business;

import java.util.List;

import ec.edu.ups.ppw64.demo64.dao.DetalleCarritoDAO;
import ec.edu.ups.ppw64.demo64.model.DetalleCarrito;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

@Stateless
public class GestionDetallesCarrito {

	@Inject
	private DetalleCarritoDAO daoDetalleCarrito;
	
	public void guardarDetalleCarrito(DetalleCarrito DetalleCarrito) {
		DetalleCarrito car = daoDetalleCarrito.read(DetalleCarrito.getCodigo());
		if (car != null) {
			daoDetalleCarrito.update(DetalleCarrito);
		} else {
			daoDetalleCarrito.insert(DetalleCarrito);
		}
	}
	
	public DetalleCarrito getDetalleCarritoByUsuario(int usuarioCodigo, int detalleCodigo) {
        return daoDetalleCarrito.findDetalleByUsuarioAndDetalle(usuarioCodigo, detalleCodigo);
    }

	public void actualizarDetalleCarrito(DetalleCarrito DetalleCarrito) throws Exception {
		DetalleCarrito car = daoDetalleCarrito.read(DetalleCarrito.getCodigo());
		if (car != null) {
			daoDetalleCarrito.update(DetalleCarrito);
		} else {
			throw new Exception("Cliente no existe");
		}
	}
	
	public void borrarDetalleCarrito(int codigo) {
		daoDetalleCarrito.remove(codigo);
	}

	public List<DetalleCarrito> getDetalleCarritos() {
		return daoDetalleCarrito.getAll();
	}
	
	public float calcularTotal(int carrito) {
		return daoDetalleCarrito.calcularTotal(carrito);
	}

}
