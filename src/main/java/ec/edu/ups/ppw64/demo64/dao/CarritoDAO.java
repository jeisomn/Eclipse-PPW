package ec.edu.ups.ppw64.demo64.dao;

import java.util.List;

import ec.edu.ups.ppw64.demo64.model.Carrito;
import ec.edu.ups.ppw64.demo64.model.DetalleCarrito;
import ec.edu.ups.ppw64.demo64.model.Usuario;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

@Stateless
public class CarritoDAO {

	@PersistenceContext
	private EntityManager em;
	
	public void insert(Carrito carrito) {
		em.persist(carrito);
	}
	
	public void update(Carrito carrito) {
		em.merge(carrito);
	}
	
	public void remove(int codigo) {
		Carrito Carrito = em.find(Carrito.class, codigo);
		em.remove(Carrito);
	}
	
	public Carrito read(int codigo) {
		Carrito Carrito = em.find(Carrito.class, codigo);
		return Carrito;
	}
	
	public List<Carrito> getAll(){
		String jpql = "SELECT c FROM Carrito c";
		Query q = em.createQuery(jpql, Carrito.class);
		return q.getResultList();
	}
	
	public List<DetalleCarrito> getAllDetalles(){
		String jpql = "SELECT d FROM DetalleCarrito d ";
		Query q = em.createQuery(jpql, Carrito.class);
		return q.getResultList();
	}

	public Carrito getCarritoById(int codigo) {
		try {
	        String jpql = "SELECT c FROM Carrito c WHERE c.codigo = :codigo";
	        Query q = em.createQuery(jpql, Carrito.class);
	        q.setParameter("codigo", codigo);
	        return (Carrito) q.getSingleResult();
	    } catch (NoResultException e) {
	        return null; // O lanzar una excepción más descriptiva según tus necesidades
	    }
	}
	
	public List<Integer> getCodigoCarrito() {
		String jpql = "SELECT c.codigo FROM Carrito c";
		Query q = em.createQuery(jpql);
	    return q.getResultList();
	}
	
	public Carrito getCarritoPorCliente(int codigoCliente) {
        String jpql = "SELECT c FROM Carrito c WHERE c.usuario.codigo = :codigo";
        Query q = em.createQuery(jpql, Carrito.class);
        q.setParameter("codigo", codigoCliente);
        List<Carrito> carritos = q.getResultList();
        if (!carritos.isEmpty()) {
            return carritos.get(0);
        }
        return null;
    }
	
	public void borrarProductoDelCarrito(int carritoId, int detalleId) {
	    Carrito carrito = em.find(Carrito.class, carritoId);
	    if (carrito != null) {
	        DetalleCarrito detalle = em.find(DetalleCarrito.class, detalleId);
	        if (detalle != null && carrito.getDetalles().contains(detalle)) {
	            carrito.getDetalles().remove(detalle);
	            em.remove(detalle);
	            em.merge(carrito);
	        }
	    }
	}
}
