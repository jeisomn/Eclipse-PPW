package ec.edu.ups.ppw64.demo64.services;

import java.util.List;

import ec.edu.ups.ppw64.demo64.business.GestionCarrito;
import ec.edu.ups.ppw64.demo64.model.Carrito;
import ec.edu.ups.ppw64.demo64.model.DetalleCarrito;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("carrito")
public class CarritoServices {

	@Inject
	private GestionCarrito gCarritos;
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)//Obtiene los parametros de las consultas y los envia
	@Consumes(MediaType.APPLICATION_JSON)
	public Response crear(Carrito Carrito) {
		try{
			gCarritos.guardarCarrito(Carrito);
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
	
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response actualizar(Carrito Carrito) {
		try{
			gCarritos.actualizarCarrito(Carrito);
			return Response.ok(Carrito).build();
		}catch (Exception e) {
			// TODO: handle exception
			ErrorMessage error = new ErrorMessage(99, e.getMessage());
			return Response.status(Response.Status.NOT_FOUND)
					.entity(error)
					.build();
		}
	}
	
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	public String borrar(@QueryParam("id") int codigo) {
		try{
			gCarritos.borrarCarrito(codigo);
			return "OK, se borro el Carrito";
		}catch (Exception e) {
			// TODO: handle exception
			return "Error al eliminar el Carrito";
		}
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("list")
	public Response getCarritos(){
		List<Carrito> Carritos = gCarritos.getCarritos();
		if(Carritos.size()>0)
			return Response.ok(Carritos).build();
		
		ErrorMessage error = new ErrorMessage(6, "No se registran Carritos");
		return Response.status(Response.Status.NOT_FOUND)
				.entity(error)
				.build();
		
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("list/detalles")
	public Response getDetallesCarrito(){
		List<DetalleCarrito> Carritos = gCarritos.getDetallesCarrito();
		if(Carritos.size()>0)
			return Response.ok(Carritos).build();
		
		ErrorMessage error = new ErrorMessage(6, "No se registran detalles");
		return Response.status(Response.Status.NOT_FOUND)
				.entity(error)
				.build();
		
	}
	
	@GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response leerPorCliente(@QueryParam("codigo") int codigo) {
        try{
            System.out.println("id " +  codigo );
            Carrito car = gCarritos.getClientePorCliente(codigo);
            return Response.ok(car).build();
        }catch (Exception e) {
            // TODO: handle exception
            ErrorMessage error = new ErrorMessage(4, "Carro no existe");
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(error)
                    .build();
        }
    }
	
}
