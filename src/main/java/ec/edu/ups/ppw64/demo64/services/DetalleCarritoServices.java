package ec.edu.ups.ppw64.demo64.services;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;

import ec.edu.ups.ppw64.demo64.business.GestionDetallesCarrito;
import ec.edu.ups.ppw64.demo64.model.Carrito;
import ec.edu.ups.ppw64.demo64.model.DetalleCarrito;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("detallesCarrito")
public class DetalleCarritoServices {

	@Inject
	private GestionDetallesCarrito gDetallesCarrito;
	
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	public Response borrar(@QueryParam("codigo") int codigo) {
		try {
			gDetallesCarrito.borrarDetalleCarrito(codigo);
			return Response.ok().entity("OK, se borro el detalle del carrito").build();
		} catch (Exception e) {
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
					.entity("Error al eliminar el detalle del carrito" + e).build();
		}
	}

	@GET
	@Path("calcular")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getTotal(@QueryParam("total") int carrito) {
		try {
			System.out.println("Total calculado " + carrito);
			
			float total = gDetallesCarrito.calcularTotal(carrito);
			
			BigDecimal bd = new BigDecimal(Float.toString((float) total));
			bd = bd.setScale(2, RoundingMode.HALF_UP);
			total = bd.floatValue();
			
			Map<String, Float> totalMap = new HashMap<>();
	        totalMap.put("total", total);
			
			return Response.ok(totalMap).build();
		} catch (Exception e) {
			// ErrorMessage error = new ErrorMessage(6, "No se registran Categorias");
			return Response.status(Response.Status.NOT_FOUND).entity("ocurrio un error al calcular" + e).build();
		}

	}
	
	
	@GET
    @Path("/{usuarioCodigo}/{detalleCodigo}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDetalleCarrito(@PathParam("usuarioCodigo") int usuarioCodigo, @PathParam("detalleCodigo") int detalleCodigo) {
        DetalleCarrito detalle = gDetallesCarrito.getDetalleCarritoByUsuario(usuarioCodigo, detalleCodigo);
        if (detalle != null) {
            return Response.ok(detalle).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND).entity("Detalle no encontrado").build();
        }
    }

}
