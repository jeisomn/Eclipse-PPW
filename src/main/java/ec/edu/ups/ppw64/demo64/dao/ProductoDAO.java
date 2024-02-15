package ec.edu.ups.ppw64.demo64.dao;

import java.util.List;

import ec.edu.ups.ppw64.demo64.model.Producto;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

@Stateless
public class ProductoDAO {

	@PersistenceContext
	private EntityManager em;
	
	public void insert(Producto producto) {
		em.persist(producto);
	}
	
	public void update(Producto producto) {
		em.merge(producto);
	}
	
	public void remove(int codigo) {
		Producto producto = em.find(Producto.class, codigo);
		em.remove(producto);
	}
	
	public Producto read(int codigo) {
		Producto producto = em.find(Producto.class, codigo);
		return producto;
	}
	
	public List<Producto> getAll(){
		String jpql = "SELECT p FROM Producto p";
		Query q = em.createQuery(jpql, Producto.class);
		return q.getResultList();
	}
	
	public List<Producto> getProductosPorCategoria(int codigoCategoria) {
        // Crear una consulta JPQL para obtener los productos de una categoría específica
        String jpql = "SELECT p FROM Producto p WHERE p.categoria.codigo = :codigoCategoria";
        TypedQuery<Producto> query = em.createQuery(jpql, Producto.class);
        query.setParameter("codigoCategoria", codigoCategoria);
        return query.getResultList();
    }
	
	public Producto getProductoById(int codigo) {
		try {
	        String jpql = "SELECT p FROM Producto p WHERE p.codigo = :codigo";
	        Query q = em.createQuery(jpql, Producto.class);
	        q.setParameter("codigo", codigo);
	        return (Producto) q.getSingleResult();
	    } catch (NoResultException e) {
	        return null; 
	    }
	}
	
	public List<Producto> getProductoDescripcion(int codigo) {
        String jpql = "SELECT p FROM Producto p WHERE p.codigo = :codigo";
        return em.createQuery(jpql, Producto.class)
                 .setParameter("codigo", codigo) 
                 .getResultList();
    }
	
	public List<Producto> buscarPorNombre(String nombre) {
	    String jpql = "SELECT p FROM Producto p WHERE LOWER(p.nombre) LIKE LOWER(:nombre)";
	    TypedQuery<Producto> query = em.createQuery(jpql, Producto.class);
	    query.setParameter("nombre", "%" + nombre + "%");
	    return query.getResultList();
	}
}
