package ec.edu.ups.ppw64.demo64.dao;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import ec.edu.ups.ppw64.demo64.model.Carrito;
import ec.edu.ups.ppw64.demo64.model.DetalleCarrito;
import ec.edu.ups.ppw64.demo64.model.Producto;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;

@Stateless
public class DetalleCarritoDAO {

	@PersistenceContext
	private EntityManager em;

	public void insert(DetalleCarrito DetalleCarrito) {
		em.persist(DetalleCarrito);
	}

	public void update(DetalleCarrito DetalleCarrito) {
		em.merge(DetalleCarrito);
	}

	public void remove(int codigo) {
		DetalleCarrito DetalleCarrito = em.find(DetalleCarrito.class, codigo);
		em.remove(DetalleCarrito);
	}

	public DetalleCarrito read(int codigo) {
		DetalleCarrito DetalleCarrito = em.find(DetalleCarrito.class, codigo);
		return DetalleCarrito;
	}

	public List<DetalleCarrito> getAll() {
		String jpql = "SELECT p FROM DetalleCarrito p";
		Query q = em.createQuery(jpql, DetalleCarrito.class);
		return q.getResultList();
	}

	public DetalleCarrito getDetalleCarritoById(int codigo) {
		try {
			String jpql = "SELECT c FROM DetalleCarrito c WHERE c.codigo = :codigo";
			Query q = em.createQuery(jpql, DetalleCarrito.class);
			q.setParameter("codigo", codigo);
			return (DetalleCarrito) q.getSingleResult();
		} catch (NoResultException e) {
			return null; // O lanzar una excepción más descriptiva según tus necesidades
		}
	}
	
	public DetalleCarrito findDetalleByUsuarioAndDetalle(int usuarioCodigo, int detalleCodigo) {
	    String jpql = "SELECT d FROM DetalleCarrito d WHERE d.codigo = :detalleCodigo AND d.carrito.usuario.codigo = :usuarioCodigo";
	    Query q = em.createQuery(jpql, DetalleCarrito.class);
	    q.setParameter("detalleCodigo", detalleCodigo);
	    q.setParameter("usuarioCodigo", usuarioCodigo);
	    try {
	        return (DetalleCarrito) q.getSingleResult();
	    } catch (NoResultException e) {
	        return null; // O manejar la excepción según la lógica de negocio
	    }
	}

	public float calcularTotal(int carrito) {
		String sql = "SELECT SUM(d.total) FROM DetalleCarrito d WHERE d.carrito = :carrito";
		Query query = em.createQuery(sql);
		query.setParameter("carrito", carrito);
		Object resultado = query.getSingleResult();
		
		System.out.println("RESULTADO CALCULAAAADO: " + resultado);
		
		if(resultado == null) {
			return 0f;
		}
		return ((Double) resultado).floatValue();
	}
	
	public DetalleCarrito agregarProductoAlCarrito(int carritoId, int productoId, int cantidad) {
        em.getTransaction().begin();
        try {
            Carrito carrito = em.find(Carrito.class, carritoId);
            Producto producto = em.find(Producto.class, productoId);
            if (carrito == null || producto == null) {
                throw new IllegalStateException("Carrito o Producto no encontrado");
            }

            DetalleCarrito detalle = new DetalleCarrito();
            detalle.setProducto(producto);
            detalle.setCantidad(cantidad);
            detalle.setPrecio(producto.getPrecio()); // Asumiendo que Producto tiene un campo precio
            detalle.setSubtotal(cantidad * producto.getPrecio());

            em.persist(detalle);
            carrito.addDetalle(detalle); // Asegúrate de que Carrito maneje correctamente la lista de detalles
            em.merge(carrito);

            em.getTransaction().commit();
            return detalle;
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw e; // o manejar la excepción como prefieras
        }
    }
}
