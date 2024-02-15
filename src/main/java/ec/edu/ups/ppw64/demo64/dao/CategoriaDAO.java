package ec.edu.ups.ppw64.demo64.dao;

import java.util.List;

import ec.edu.ups.ppw64.demo64.model.Categoria;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

@Stateless
public class CategoriaDAO {

	@PersistenceContext
	private EntityManager em;
	
	public void insert(Categoria categoria) {
		em.persist(categoria);
	}
	
	public void update(Categoria categoria) {
		em.merge(categoria);
	}
	
	public void remove(int codigo) {
		Categoria Categoria = em.find(Categoria.class, codigo);
		em.remove(Categoria);
	}
	
	public Categoria read(int codigo) {
		Categoria Categoria = em.find(Categoria.class, codigo);
		return Categoria;
	}
	
	public List<Categoria> getAll(){
		String jpql = "SELECT c FROM Categoria c";
		Query q = em.createQuery(jpql, Categoria.class);
		return q.getResultList();
	}
	
	public Categoria getCategoriaById(int codigo){
		try {
			String jpql = "SELECT c FROM Categoria c WHERE c.codigo =: codigo";
			Query q = em.createQuery(jpql, Categoria.class);
			q.setParameter("codigo", codigo);
			List<Categoria> cat = q.getResultList();
		    return cat.isEmpty() ? null : cat.get(0); 
		} catch (Exception e) {
			return null;
		}
	}

}
