package ec.edu.ups.ppw64.demo64.services;

import java.util.List;

import ec.edu.ups.ppw64.demo64.model.DetalleFactura;
import ec.edu.ups.ppw64.demo64.model.Factura;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("facturas")
public class FacturaServices {
	
	/*@Inject
	private GestionFacturas gFacturas;
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response crear (Factura factura) {
		try{
			gFacturas.guardarFactura(factura);
			ErrorMessage error = new ErrorMessage(1, "ok");
			System.out.println("FACTURAS GUARDADAS: " + factura);
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
	@Produces(MediaType.APPLICATION_JSON)
	@Path("list")
	public Response getFacturas(){
		List<Factura> facturas = gFacturas.getFacturas();
		System.out.println("FACTURAS LISTADAAAAAAAAAAS: " + facturas);
		if(facturas.size()>0)
			return Response.ok(facturas).build();
		
		ErrorMessage error = new ErrorMessage(6, "No se registran facturas");
		return Response.status(Response.Status.NOT_FOUND)
				.entity(error)
				.build();
		
	}*/
}
