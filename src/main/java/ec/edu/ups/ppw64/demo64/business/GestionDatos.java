package ec.edu.ups.ppw64.demo64.business;

import java.util.List;

import ec.edu.ups.ppw64.demo64.dao.CarritoDAO;
import ec.edu.ups.ppw64.demo64.dao.CategoriaDAO;
import ec.edu.ups.ppw64.demo64.dao.UsuarioDAO;
import ec.edu.ups.ppw64.demo64.dao.MensajeUsuarioDAO;
import ec.edu.ups.ppw64.demo64.dao.ProductoDAO;
import ec.edu.ups.ppw64.demo64.model.Carrito;
import ec.edu.ups.ppw64.demo64.model.Categoria;
import ec.edu.ups.ppw64.demo64.model.Usuario;
import ec.edu.ups.ppw64.demo64.model.MensajesUsuarios;
import ec.edu.ups.ppw64.demo64.model.Producto;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.Singleton;
import jakarta.ejb.Startup;
import jakarta.inject.Inject;

@Singleton
@Startup
public class GestionDatos {

	@Inject
	private UsuarioDAO daoCuenta;

	@Inject
	private MensajeUsuarioDAO daoMsjUsuario;
	
	@Inject
	private CategoriaDAO daoCategoria;
	
	@Inject
	private ProductoDAO daoProducto;
	
	@Inject 
	private CarritoDAO daoCarrito;

	@PostConstruct
	public void init() {
		System.out.println("iniciando");

		Usuario usuario = new Usuario();
		usuario.setNombre("Paul");
		usuario.setApellido("Sigua");
		usuario.setCorreo("mateosigua2002@gmail.com");
		usuario.setContrasenia("12345");
		usuario.setCedula("0106764830");
		usuario.setAccedio(false);
		
		Categoria cat1 = new Categoria();
		cat1.setNombre("ropa");
		
		Categoria cat2 = new Categoria();
		cat2.setNombre("tecnologia");
		
		Categoria cat3 = new Categoria();
		cat3.setNombre("hogar");
		
		Categoria cat4 = new Categoria();
		cat4.setNombre("gaming");
		
		Categoria cat5 = new Categoria();
		cat5.setNombre("electrodomesticos");
		
		Categoria cat6 = new Categoria();
		cat6.setNombre("construccion");
		
		////////////////////////////////////////
		
		Producto pro1 = new Producto();
		pro1.setNombre("Zapatos Vans Classic");
		pro1.setPrecio((float) 89.99);
		pro1.setCaracteristicas("Los mejores Zapatos en la actualidad" + "\n" + "Los clasicos de clasicos");
		pro1.setImagen("https://i.pinimg.com/564x/06/a7/a9/06a7a95fb792ce3dfd55b792547cb76a.jpg");
		pro1.setCategoria(cat1);
		pro1.setDescripcionGeneral("JAJAJAJAJA \n JAJAJJAJAJA \n JAJAJAJJAJA \n JAJAJAJAJAJ");
		
		Producto pro2 = new Producto();
		pro2.setNombre("Sueter Adidas");
		pro2.setPrecio((float) 49.99);
		pro2.setCaracteristicas("El mejor sueter");
		pro2.setImagen("https://i.pinimg.com/564x/06/a7/a9/06a7a95fb792ce3dfd55b792547cb76a.jpg");
		pro2.setCategoria(cat1);
		
		Producto pro3 = new Producto();
		pro3.setNombre("IPhone XIV");
		pro3.setPrecio((float) 299.99);
		pro3.setCaracteristicas("El IPhone que lo cambio todo");
		pro3.setImagen("https://i.pinimg.com/564x/4d/ce/e9/4dcee96ffd48ebfdf73ef5dd74d32574.jpg");
		pro3.setCategoria(cat2);
		
		Producto pro4 = new Producto();
		pro4.setNombre("Xiaomi Redmi Note 11 Pro");
		pro4.setPrecio((float) 1099.99);
		pro4.setCaracteristicas("Un telefono inigualable");
		pro4.setImagen("https://i.pinimg.com/564x/4d/ce/e9/4dcee96ffd48ebfdf73ef5dd74d32574.jpg");
		pro4.setCategoria(cat2);
		
		Producto pro5 = new Producto();
		pro5.setNombre("Sofa Luric");
		pro5.setPrecio((float) 499.99);
		pro5.setCaracteristicas("Un sofa muy comodo");
		pro5.setImagen("https://i.pinimg.com/564x/d1/b6/9e/d1b69e9389f12703b6bebbbdc5d5224e.jpg");
		pro5.setCategoria(cat3);
		
		Producto pro6 = new Producto();
		pro6.setNombre("Lampara");
		pro6.setPrecio((float) 39.59);
		pro6.setCaracteristicas("Una lampara para mejorar el Hogar");
		pro6.setImagen("https://i.pinimg.com/564x/d1/b6/9e/d1b69e9389f12703b6bebbbdc5d5224e.jpg");
		pro6.setCategoria(cat3);
		
		Producto pro7 = new Producto();
		pro7.setNombre("Laptop Gamer LEGION 9");
		pro7.setPrecio((float) 499.99);
		pro7.setCaracteristicas("La poderosa entre las poderosas");
		pro7.setImagen("https://i.pinimg.com/564x/61/e9/af/61e9af49bdc604b7d5d2b19832e34cea.jpg");
		pro7.setCategoria(cat4);
		
		Producto pro8 = new Producto();
		pro8.setNombre("Mouse Gamer Xtech");
		pro8.setPrecio((float) 20.00);
		pro8.setCaracteristicas("Un Mouse siempre es necesario");
		pro8.setImagen("https://i.pinimg.com/564x/61/e9/af/61e9af49bdc604b7d5d2b19832e34cea.jpg");
		pro8.setCategoria(cat4);
		
		Producto pro9 = new Producto();
		pro9.setNombre("Cocina Indurama");
		pro9.setPrecio((float) 499.99);
		pro9.setCaracteristicas("Cocina tus alimentos mas rapido");
		pro9.setImagen("https://i.pinimg.com/564x/ff/b1/9d/ffb19d9a01d3778fd1be745a49b25779.jpg");
		pro9.setCategoria(cat5);
		
		Producto pro10 = new Producto();
		pro10.setNombre("Refrigeradora Indurama");
		pro10.setPrecio((float) 1200.99);
		pro10.setCaracteristicas("Una buena refrigeradora siempre viene de Indurama");
		pro10.setImagen("https://i.pinimg.com/564x/ff/b1/9d/ffb19d9a01d3778fd1be745a49b25779.jpg");
		pro10.setCategoria(cat5);
		
		Producto pro11 = new Producto();
		pro11.setNombre("Taladro DEWALT");
		pro11.setPrecio((float) 326.00);
		pro11.setCaracteristicas("Para el hombre de la casa");
		pro11.setImagen("https://http2.mlstatic.com/D_NQ_NP_963270-MLA51917691186_102022-O.webp");
		pro11.setCategoria(cat6);
		
		Producto pro12 = new Producto();
		pro12.setNombre("Desarmador estrella TRUPER");
		pro12.setPrecio((float) 1.59);
		pro12.setCaracteristicas("Dura muchos años");
		pro12.setImagen("https://http2.mlstatic.com/D_NQ_NP_963270-MLA51917691186_102022-O.webp");
		pro12.setCategoria(cat6);
		
		Producto pro13 = new Producto();
		pro13.setNombre("Zapatos Vans Classic");
		pro13.setPrecio((float) 89.99);
		pro13.setCaracteristicas("Los mejores Zapatos en la actualidad" + "\n" + "Los clasicos de clasicos");
		pro13.setImagen("https://i.pinimg.com/564x/06/a7/a9/06a7a95fb792ce3dfd55b792547cb76a.jpg");
		pro13.setCategoria(cat1);
		
		Producto pro14 = new Producto();
		pro14.setNombre("Sueter Adidas");
		pro14.setPrecio((float) 49.99);
		pro14.setCaracteristicas("El mejor sueter");
		pro14.setImagen("https://i.pinimg.com/564x/06/a7/a9/06a7a95fb792ce3dfd55b792547cb76a.jpg");
		pro14.setCategoria(cat1);
		
		Producto pro15 = new Producto();
		pro15.setNombre("Xiaomi Redmi Note 11 Pro");
		pro15.setPrecio((float) 1099.99);
		pro15.setCaracteristicas("Un telefono inigualable");
		pro15.setImagen("https://i.pinimg.com/564x/4d/ce/e9/4dcee96ffd48ebfdf73ef5dd74d32574.jpg");
		pro15.setCategoria(cat2);
		
		Producto pro16 = new Producto();
		pro16.setNombre("Sueter Adidas");
		pro16.setPrecio((float) 49.99);
		pro16.setCaracteristicas("El mejor sueter");
		pro16.setImagen("https://i.pinimg.com/564x/4d/ce/e9/4dcee96ffd48ebfdf73ef5dd74d32574.jpg");
		pro16.setCategoria(cat2);
		
		Producto pro17 = new Producto();
		pro17.setNombre("IPhone XIV");
		pro17.setPrecio((float) 299.99);
		pro17.setCaracteristicas("El IPhone que lo cambio todo");
		pro17.setImagen("https://i.pinimg.com/564x/d1/b6/9e/d1b69e9389f12703b6bebbbdc5d5224e.jpg");
		pro17.setCategoria(cat3);
		
		Producto pro18 = new Producto();
		pro18.setNombre("Sueter Adidas");
		pro18.setPrecio((float) 49.99);
		pro18.setCaracteristicas("El mejor sueter");
		pro18.setImagen("https://i.pinimg.com/564x/d1/b6/9e/d1b69e9389f12703b6bebbbdc5d5224e.jpg");
		pro18.setCategoria(cat3);
		
		Producto pro19 = new Producto();
		pro19.setNombre("Zapatos Vans Classic");
		pro19.setPrecio((float) 89.99);
		pro19.setCaracteristicas("Los mejores Zapatos en la actualidad" + "\n" + "Los clasicos de clasicos");
		pro19.setImagen("https://i.pinimg.com/564x/61/e9/af/61e9af49bdc604b7d5d2b19832e34cea.jpg");
		pro19.setCategoria(cat4);
		
		Producto pro20 = new Producto();
		pro20.setNombre("Sueter Adidas");
		pro20.setPrecio((float) 49.99);
		pro20.setCaracteristicas("El mejor sueter");
		pro20.setImagen("https://i.pinimg.com/564x/61/e9/af/61e9af49bdc604b7d5d2b19832e34cea.jpg");
		pro20.setCategoria(cat4);
		
		Producto pro21 = new Producto();
		pro21.setNombre("Cocina Indurama");
		pro21.setPrecio((float) 499.99);
		pro21.setCaracteristicas("Cocina tus alimentos mas rapido");
		pro21.setImagen("https://i.pinimg.com/564x/ff/b1/9d/ffb19d9a01d3778fd1be745a49b25779.jpg");
		pro21.setCategoria(cat5);
		
		Producto pro22 = new Producto();
		pro22.setNombre("Refrigeradora Indurama");
		pro22.setPrecio((float) 1200.99);
		pro22.setCaracteristicas("Una buena refrigeradora siempre viene de Indurama");
		pro22.setImagen("https://i.pinimg.com/564x/ff/b1/9d/ffb19d9a01d3778fd1be745a49b25779.jpg");
		pro22.setCategoria(cat5);
		
		Producto pro23 = new Producto();
		pro23.setNombre("Taladro DEWALT");
		pro23.setPrecio((float) 326.00);
		pro23.setCaracteristicas("Para el hombre de la casa");
		pro23.setImagen("https://http2.mlstatic.com/D_NQ_NP_963270-MLA51917691186_102022-O.webp");
		pro23.setCategoria(cat6);
		
		Producto pro24 = new Producto();
		pro24.setNombre("Desarmador estrella TRUPER");
		pro24.setPrecio((float) 1.59);
		pro24.setCaracteristicas("Dura muchos años");
		pro24.setImagen("https://http2.mlstatic.com/D_NQ_NP_963270-MLA51917691186_102022-O.webp");
		pro24.setCategoria(cat6);
		
		daoProducto.insert(pro1);
		daoProducto.insert(pro2);
		daoProducto.insert(pro3);
		daoProducto.insert(pro4);
		daoProducto.insert(pro5);
		daoProducto.insert(pro6);
		daoProducto.insert(pro7);
		daoProducto.insert(pro8);
		daoProducto.insert(pro9);
		daoProducto.insert(pro10);
		daoProducto.insert(pro11);
		daoProducto.insert(pro12);
		daoProducto.insert(pro13);
		daoProducto.insert(pro14);
		daoProducto.insert(pro15);
		daoProducto.insert(pro16);
		daoProducto.insert(pro17);
		daoProducto.insert(pro18);
		daoProducto.insert(pro19);
		daoProducto.insert(pro20);
		daoProducto.insert(pro21);
		daoProducto.insert(pro22);
		daoProducto.insert(pro23);
		daoProducto.insert(pro24);

		cat1.addProducto(pro1);
		cat2.addProducto(pro2);
		cat2.addProducto(pro3);
		cat2.addProducto(pro4);
		cat2.addProducto(pro5);
		cat2.addProducto(pro6);
		cat2.addProducto(pro7);
		cat2.addProducto(pro8);
		cat2.addProducto(pro9);
		cat2.addProducto(pro10);
		cat2.addProducto(pro11);
		cat2.addProducto(pro12);
		
		daoCategoria.insert(cat1);
		daoCategoria.insert(cat2);
		daoCategoria.insert(cat3);
		daoCategoria.insert(cat4);
		daoCategoria.insert(cat5);
		daoCategoria.insert(cat6);
		
		MensajesUsuarios msj = new MensajesUsuarios();
		msj.setNombre("Mateo Calle");
		msj.setCorreo("mateosigua2002@gmai.com");
		msj.setMensaje("Hola buenas que tal como esta");
		
		daoMsjUsuario.insert(msj);
		
		daoCuenta.insert(usuario);
		
		System.out.println("\n------------- Cuentas");
		List<Usuario> list1 = daoCuenta.getAll();
		for (Usuario cue : list1) {
			System.out.println(cue);
		}
		
		System.out.println("\n------------- Categoria");
		List<Categoria> list2 = daoCategoria.getAll();
		for (Categoria cat : list2) {
			System.out.println(cat);
		}
		
		System.out.println("\n------------- Productos");
		List<Producto> list3 = daoProducto.getAll();
		for (Producto pro : list3) {
			System.out.println(pro);
		}
		
		System.out.println("\n------------- Carritos");
		List<Carrito> list4 = daoCarrito.getAll();
		for (Carrito carr : list4) {
			System.out.println(carr);
		}

	}
}