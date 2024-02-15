package ec.edu.ups.ppw64.demo64.services;

import java.util.List;

import ec.edu.ups.ppw64.demo64.business.GestionCategoria;
import ec.edu.ups.ppw64.demo64.model.Categoria;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("categoria")
public class CategoriaServices {

	@Inject
	private GestionCategoria gCategorias;

	@POST
	@Produces(MediaType.APPLICATION_JSON) // Obtiene los parametros de las consultas y los envia
	@Consumes(MediaType.APPLICATION_JSON)
	public Response crear(Categoria categoria) {
		try {
			gCategorias.guardarCategoria(categoria);
			ErrorMessage error = new ErrorMessage(1, "ok");
			return Response.status(Response.Status.CREATED).entity(error).build();
		} catch (Exception e) {
			// TODO: handle exception
			ErrorMessage error = new ErrorMessage(99, e.getMessage());
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(error).build();
		}
	}

	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response actualizar(Categoria categoria) {
		try {
			gCategorias.actualizarCategoria(categoria);
			return Response.ok(categoria).build();
		} catch (Exception e) {
			// TODO: handle exception
			ErrorMessage error = new ErrorMessage(99, e.getMessage());
			return Response.status(Response.Status.NOT_FOUND).entity(error).build();
		}
	}

	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	public String borrar(@QueryParam("id") int codigo) {
		try {
			gCategorias.borrarCategoria(codigo);
			return "OK, se borro el Categoria";
		} catch (Exception e) {
			// TODO: handle exception
			return "Error al eliminar el Categoria";
		}
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("list")
	public Response getCategorias() {
		List<Categoria> cat = gCategorias.getCategorias();
		if (cat.size() > 0)
			return Response.ok(cat).build();

		ErrorMessage error = new ErrorMessage(6, "No se registran Categorias");
		return Response.status(Response.Status.NOT_FOUND).entity(error).build();
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getCategoriaByCodigo(@QueryParam("codigo") int codigo) {
		try {
			System.out.println("Codigo " +  codigo);
			Categoria cat = gCategorias.getCategoria(codigo);
				return Response.ok(cat).build();
		} catch (Exception e) {
			ErrorMessage error = new ErrorMessage(6, "No se registran Categorias");
			return Response.status(Response.Status.NOT_FOUND)
					.entity(error).build();
		}
		
	}

}
