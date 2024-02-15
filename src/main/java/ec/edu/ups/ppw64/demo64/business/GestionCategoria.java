package ec.edu.ups.ppw64.demo64.business;

import java.util.List;

import ec.edu.ups.ppw64.demo64.dao.CategoriaDAO;
import ec.edu.ups.ppw64.demo64.model.Categoria;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

@Stateless
public class GestionCategoria {

	@Inject
	private CategoriaDAO daoCategoria;

	public void guardarCategoria(Categoria categoria) {
		Categoria cli = daoCategoria.read(categoria.getCodigo());
		if (cli != null) {
			daoCategoria.update(categoria);
		} else {
			daoCategoria.insert(categoria);
		}
	}

	public void actualizarCategoria(Categoria categoria) throws Exception {
		Categoria pro = daoCategoria.read(categoria.getCodigo());
		if (pro != null) {
			daoCategoria.update(categoria);
		} else {
			throw new Exception("Cliente no existe");
		}
	}

	public void borrarCategoria(int codigo) {
		daoCategoria.remove(codigo);
	}

	public List<Categoria> getCategorias() {
		return daoCategoria.getAll();
	}
	
	public Categoria getCategoria(int codigo){
		return daoCategoria.getCategoriaById(codigo);
	}
}
