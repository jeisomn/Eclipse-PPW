package ec.edu.ups.ppw64.demo64.business;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import ec.edu.ups.ppw64.demo64.dao.CarritoDAO;
import ec.edu.ups.ppw64.demo64.dao.DetalleCarritoDAO;
import ec.edu.ups.ppw64.demo64.dao.ProductoDAO;
import ec.edu.ups.ppw64.demo64.dao.UsuarioDAO;
import ec.edu.ups.ppw64.demo64.model.CargarProducto;
import ec.edu.ups.ppw64.demo64.model.Carrito;
import ec.edu.ups.ppw64.demo64.model.Categoria;
import ec.edu.ups.ppw64.demo64.model.DetalleCarrito;
import ec.edu.ups.ppw64.demo64.model.Producto;
import ec.edu.ups.ppw64.demo64.model.Usuario;
import jakarta.ejb.Stateless;
import jakarta.inject.Inject;

@Stateless
public class GestionProductos {

	@Inject
	private ProductoDAO daoProducto;
	
	@Inject
	private CarritoDAO daoCarrito;
	
	@Inject
	private UsuarioDAO daouUsuario;
	
	@Inject
	private DetalleCarritoDAO daoDetalleCarrito;

	public void guardarProducto(Producto producto) {
		Producto cli = daoProducto.read(producto.getCodigo());
		if (cli != null) {
			daoProducto.update(producto);
		} else {
			daoProducto.insert(producto);
		}
	}

	public void actualizarCliente(Producto producto) throws Exception {
		Producto pro = daoProducto.read(producto.getCodigo());
		if (pro != null) {
			daoProducto.update(producto);
		} else {
			throw new Exception("Cliente no existe");
		}
	}

	public List<Producto> getProductos() {
		return daoProducto.getAll();
	}
	
	public List<Producto> getProductosPorCategoria(int categoria){
		return daoProducto.getProductosPorCategoria(categoria);
	}
	
	public void borrarProducto(int codigo) {
		daoProducto.remove(codigo);
	}
	
	public Carrito getCarritoById(int carrito) {
		return daoCarrito.getCarritoById(carrito);
	}
	
	public Producto getProductoById(int producto) {
		return daoProducto.getProductoById(producto);
	}
	
	public List<Producto> getProducto(int codigo){
		return daoProducto.getProductoDescripcion(codigo);
	}
	
	public void aniadirProductoAlCarrito(CargarProducto cargarProducto) {
//		Carrito carrito = getCarritoById(cargarProducto.getCarrito());
		Usuario usu = daouUsuario.obtenerUsuarioLogueado();
		if(usu != null) {
			Carrito car = usu.getCarrito();
		if (car == null) {
	        car = new Carrito();
	        daoCarrito.insert(car);
	    }
		
		Producto producto = getProductoById(cargarProducto.getProducto());
		
		DetalleCarrito detalleExistente = null;
	    for (DetalleCarrito det : car.getDetalles()) {
	        if (det.getProducto().getCodigo() == (producto.getCodigo())) {
	            detalleExistente = det;
	            break;
	        }
	    }
	    
	    if (detalleExistente != null) {
	        detalleExistente.setCantidad(detalleExistente.getCantidad() + cargarProducto.getCantidad());
	        detalleExistente.setTotal(detalleExistente.getSubtotal() + producto.getPrecio() * cargarProducto.getCantidad());
	        daoDetalleCarrito.update(detalleExistente);
	    } else {
		
		DetalleCarrito det = new DetalleCarrito();
		det.setProducto(producto);
		det.setCantidad(cargarProducto.getCantidad());
		det.setPrecio(producto.getPrecio());
		det.setSubtotal(producto.getPrecio() * cargarProducto.getCantidad());
		
		float precio = (float) (producto.getPrecio() * 0.12) + producto.getPrecio();
		float cantidad = cargarProducto.getCantidad();
		
		float total = precio * cantidad;
		BigDecimal bd = new BigDecimal(Float.toString(total));
		bd = bd.setScale(2, RoundingMode.HALF_UP);
		total = bd.floatValue();
		
		det.setTotal(total);
		System.out.println("CODIGO PRODUCTO: " + producto.getCodigo());
		car.addDetalle(det);
		
		
		daoDetalleCarrito.insert(det);
		
		daoCarrito.update(car);
		
	    }
		System.out.println("SE ANIADIO EL PRODUCTO AL CARRITO");
		}else {
		System.out.println("No hay usuario logeado");
		return;
		}
	}

	public List<Producto> buscarProductos(String nombre){
		return daoProducto.buscarPorNombre(nombre);
	}
}
