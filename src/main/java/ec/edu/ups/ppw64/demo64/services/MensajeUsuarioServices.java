package ec.edu.ups.ppw64.demo64.services;

import java.util.List;

import ec.edu.ups.ppw64.demo64.business.GestionMensajesUsuarios;
import ec.edu.ups.ppw64.demo64.model.MensajesUsuarios;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("mensaje")
public class MensajeUsuarioServices {

	@Inject
	private GestionMensajesUsuarios gMsjUsuarios;
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response crear (MensajesUsuarios mensajesUsuarios) {
		try{
			gMsjUsuarios.guardarMensajesUsuarioss(mensajesUsuarios);
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
	public Response actualizar(MensajesUsuarios mensajeusuarios) {
		try{
			gMsjUsuarios.actualizarMensajesUsuarios(mensajeusuarios);
			return Response.ok(mensajeusuarios).build();
		}catch (Exception e) {
			// TODO: handle exception
			ErrorMessage error = new ErrorMessage(99, e.getMessage());
			return Response.status(Response.Status.NOT_FOUND)
					.entity(error)
					.build();
		}
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("list")
	public Response getClientes(){
		List<MensajesUsuarios> mensajes = gMsjUsuarios.getMensajes();
		if(mensajes.size()>0)
			return Response.ok(mensajes).build();
		
		ErrorMessage error = new ErrorMessage(6, "No se registran clientes");
		return Response.status(Response.Status.NOT_FOUND)
				.entity(error)
				.build();
	}
}
