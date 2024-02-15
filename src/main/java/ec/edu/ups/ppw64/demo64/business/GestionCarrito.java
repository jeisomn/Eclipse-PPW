package ec.edu.ups.ppw64.demo64.business;

import java.util.List;

import ec.edu.ups.ppw64.demo64.dao.CarritoDAO;
import ec.edu.ups.ppw64.demo64.dao.DetalleCarritoDAO;
import ec.edu.ups.ppw64.demo64.dao.ProductoDAO;
import ec.edu.ups.ppw64.demo64.dao.UsuarioDAO;
import ec.edu.ups.ppw64.demo64.model.Carrito;
import ec.edu.ups.ppw64.demo64.model.DetalleCarrito;
import ec.edu.ups.ppw64.demo64.model.Producto;
import ec.edu.ups.ppw64.demo64.model.Usuario;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

@Stateless
public class GestionCarrito {

	@Inject
	private CarritoDAO daoCarrito;
	
	@Inject
	private ProductoDAO daoProducto;
	
	@Inject 
	private DetalleCarritoDAO daoDetalleCarrito;
	
	@Inject
	private UsuarioDAO daoUsuario;

	public void guardarCarrito(Carrito Carrito) {
		Carrito car = daoCarrito.read(Carrito.getCodigo());
		if (car != null) {
			daoCarrito.update(Carrito);
		} else {
			daoCarrito.insert(Carrito);
		}
	}
	
	public Carrito getClientePorCliente(int codigo){
        return daoCarrito.getCarritoPorCliente(codigo);
    }

	public void actualizarCarrito(Carrito Carrito) throws Exception {
		Carrito car = daoCarrito.read(Carrito.getCodigo());
		if (car != null) {
			daoCarrito.update(Carrito);
		} else {
			throw new Exception("Cliente no existe");
		}
	}
	
	public void borrarCarrito(int codigo) {

		daoCarrito.remove(codigo);
	}

	public List<Carrito> getCarritos() {
		return daoCarrito.getAll();
	}
	
	public List<Integer> getCodigoCarrito() {
		return daoCarrito.getCodigoCarrito();
	}
	
	public List<DetalleCarrito> getDetallesCarrito() {
		return daoCarrito.getAllDetalles();
	}
	
	
	public Carrito crearCarritoParaUsuario(int usuarioId) {
	    Usuario usuario = daoUsuario.read(usuarioId);
	    if (usuario != null && usuario.getCarrito() == null) {
	        Carrito nuevoCarrito = new Carrito();
	        // Configura el carrito si es necesario
	        DetalleCarrito det = new DetalleCarrito();
	        daoCarrito.insert(nuevoCarrito);
	        
	        // Establece la referencia inversa en el usuario
	        usuario.setCarrito(nuevoCarrito);
	        daoUsuario.update(usuario); // Asume que UsuarioDAO tiene un método update
	        
	        // Establece la referencia inversa en el carrito
	        nuevoCarrito.setUsuario(usuario);
	        daoCarrito.update(nuevoCarrito); // Asume que CarritoDAO tiene un método update
	        
	        
	        System.out.println("Carrito creado para usuario: " + usuarioId);
	        return nuevoCarrito;
	    }
	    
	    return null; // o maneja esta situación de otra manera
	}

	
	public DetalleCarrito agregarProductoACarrito(int  carritoId, int productoId, int cantidad) {
        Carrito carrito = daoCarrito.read(carritoId);
        Producto producto = daoProducto.read(productoId);
        
        if (carrito == null || producto == null) {
            throw new IllegalStateException("Carrito o Producto no encontrado");
        }

        DetalleCarrito detalleExistente = null;
	    for (DetalleCarrito det : carrito.getDetalles()) {
	        if (det.getProducto().getCodigo() == (producto.getCodigo())) {
	            detalleExistente = det;
	            break;
	        }
	    }
	    
	    if (detalleExistente != null) {
	        detalleExistente.setCantidad(detalleExistente.getCantidad() + detalleExistente.getCantidad());
	        detalleExistente.setSubtotal(detalleExistente.getSubtotal() + producto.getPrecio() * detalleExistente.getCantidad());
	        daoDetalleCarrito.update(detalleExistente);
	    }
        
        
        DetalleCarrito detalle = new DetalleCarrito();
        
        detalle.setProducto(producto);
        detalle.setCantidad(cantidad);
        detalle.setPrecio(producto.getPrecio());
        detalle.setSubtotal(cantidad * producto.getPrecio());

        daoDetalleCarrito.insert(detalle);
        
        daoCarrito.update(carrito);
        
        return detalle;
	    
	    
    }
}
