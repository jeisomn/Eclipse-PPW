package ec.edu.ups.ppw64.demo64.dao;

import java.util.ArrayList;
import java.util.List;

import ec.edu.ups.ppw64.demo64.model.Factura;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

@Stateless
public class FacturaDAO {

	@PersistenceContext
	private EntityManager em;
	
	public void insert(Factura factura) {
		em.persist(factura);
	}
	
	public void update(Factura factura) {
		em.merge(factura);
	}
	
	public void remove(int codigo) {
		Factura factura = em.find(Factura.class, codigo);
		em.refresh(factura);
	}
	
	public Factura read(int codigo) {
		Factura factura = em.find(Factura.class, codigo);
		return factura;
	}
	
	public List<Factura> getAll(){
		String jpql = "SELECT f FROM Factura f JOIN FETCH f.cliente";
	    TypedQuery<Factura> query = em.createQuery(jpql, Factura.class);
	    System.out.println("FACTURAAA: " + query.getResultList());
	    return query.getResultList();
	}

}




