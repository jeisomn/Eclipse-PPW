package ec.edu.ups.ppw64.demo64.dao;

import java.util.List;

import ec.edu.ups.ppw64.demo64.model.Carrito;
import ec.edu.ups.ppw64.demo64.model.Credenciales;
import ec.edu.ups.ppw64.demo64.model.Usuario;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

@Stateless
public class UsuarioDAO {

	@PersistenceContext
	private EntityManager em;
	
	public void insert(Usuario Usuario) {
		em.persist(Usuario);
	}
	
	public void update(Usuario Usuario) {
		em.merge(Usuario);
	}
	
	public void remove(int codigo) {
		Usuario Usuario = em.find(Usuario.class, codigo);
		em.remove(Usuario);
	}
	
	public Usuario read(int codigo) {
		Usuario Usuario = em.find(Usuario.class, codigo);
		return Usuario;
	}
	
	public List<Usuario> getAll(){
		String jpql = "SELECT c FROM Usuario c";
		Query q = em.createQuery(jpql, Usuario.class);
		return q.getResultList();
	}

	public Usuario findPorId(int codigo) {
		System.out.println("Buscando usuario con ID: " + codigo);
        return em.find(Usuario.class, codigo);
    }
	
	public Usuario getCuentaPorCodigo(int codigo) {
	    try {
	        String jpql = "SELECT c FROM Usuario c WHERE c.codigo = :codigo";
	        Query q = em.createQuery(jpql, Usuario.class);
	        q.setParameter("codigo", codigo);
	        return (Usuario) q.getSingleResult();
	    } catch (NoResultException e) {
	        return null; // O lanzar una excepción más descriptiva según tus necesidades
	    }
	}
	
	public boolean validarCredenciales(Credenciales credenciales) {
	    String correo = credenciales.getCorreo();
	    String contrasenia = credenciales.getContrasenia();
	    try {
	        em.createQuery("UPDATE Usuario c SET c.accedio = FALSE WHERE c.accedio = TRUE").executeUpdate();

	        Usuario usuario = em.createQuery("SELECT c FROM Usuario c WHERE c.correo = :correo AND c.contrasenia = :contrasenia", Usuario.class)
	                          .setParameter("correo", correo)
	                          .setParameter("contrasenia", contrasenia)
	                          .getSingleResult();

	        
	        
	        if (usuario != null) {
	            usuario.setAccedio(true);
	            em.merge(usuario);
	            
	            return true;
	        }
	    } catch (NoResultException e) {
	        System.out.println("No se encontró la cuenta: " + e.getMessage());
	    }
	    return false;
	}

	public Usuario obtenerUsuarioLogueado() {
	    try {
	        Usuario usuario = em.createQuery("SELECT u FROM Usuario u WHERE u.accedio = TRUE", Usuario.class)
	                            .getSingleResult();
	        return usuario;
	    } catch (NoResultException e) {
	        return null; // Maneja el caso de que no haya ningún usuario logueado
	    }
	}
}
