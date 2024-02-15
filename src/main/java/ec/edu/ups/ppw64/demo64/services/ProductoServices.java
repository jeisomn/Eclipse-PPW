package ec.edu.ups.ppw64.demo64.services;

import java.util.List;

import ec.edu.ups.ppw64.demo64.business.GestionProductos;
import ec.edu.ups.ppw64.demo64.model.CargarProducto;
import ec.edu.ups.ppw64.demo64.model.Producto;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("productos")
public class ProductoServices {

	@Inject
	private GestionProductos gProductos;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("list")
	public Response getProductos() {
		List<Producto> productos = gProductos.getProductos();
		if (productos.size() > 0)
			return Response.ok(productos).build();

		ErrorMessage error = new ErrorMessage(6, "No se registran productos");
		return Response.status(Response.Status.NOT_FOUND).entity(error).build();
	}
	
	@GET
	@Path("categoria")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCategoriaByCodigo(@QueryParam("codigo") int codigo) {
		try {
			System.out.println("Codigo " +  codigo);
			List<Producto> pro = gProductos.getProductosPorCategoria(codigo);
			if (pro.isEmpty()) {
                return Response.status(Response.Status.NOT_FOUND).entity("No se encontraron productos para la categoría con código " + codigo).build();
            }
				return Response.ok(pro).build();
		} catch (Exception e) {
			ErrorMessage error = new ErrorMessage(6, "No se registran Categorias");
			return Response.status(Response.Status.NOT_FOUND)
					.entity(error).build();
		}
		
	}
	
	@GET
	@Path("id")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getProductoByCodigo(@QueryParam("codigo") int codigo) {
		try {
			System.out.println("Codigo " +  codigo);
			List<Producto> pro = gProductos.getProducto(codigo);
			if (pro.isEmpty()) {
                return Response.status(Response.Status.NOT_FOUND).entity("No se encontraron productos para la categoría con código " + codigo).build();
            }
				return Response.ok(pro).build();
		} catch (Exception e) {
			ErrorMessage error = new ErrorMessage(6, "No se registran Categorias");
			return Response.status(Response.Status.NOT_FOUND)
					.entity(error).build();
		}
		
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)//Obtiene los parametros de las consultas y los envia
	@Consumes(MediaType.APPLICATION_JSON)
	@Path("addProducto")
	public Response crear(CargarProducto cargarProducto) {
		try{
			gProductos.aniadirProductoAlCarrito(cargarProducto);
			ErrorMessage error = new ErrorMessage(1, "ok");
			return Response.status(Response.Status.CREATED).entity(error).build();
		}catch (Exception e) {
			// TODO: handle exception
			ErrorMessage error = new ErrorMessage(99, e.getMessage());
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
					.entity(error)
					.build();
		}
	}
	
	@GET
    @Path("buscar")
    @Produces(MediaType.APPLICATION_JSON)
    public Response buscarPorNombre(@QueryParam("nombre") String nombre) {
        List<Producto> productos = gProductos.buscarProductos(nombre);
        if (productos.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).entity("No se encontraron productos.").build();
        }
        return Response.ok(productos).build();
    }
}
