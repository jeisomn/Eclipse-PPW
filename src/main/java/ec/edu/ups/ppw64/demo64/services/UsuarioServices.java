package ec.edu.ups.ppw64.demo64.services;

import java.util.List;

import ec.edu.ups.ppw64.demo64.business.GestionCarrito;
import ec.edu.ups.ppw64.demo64.business.GestionUsuarios;
import ec.edu.ups.ppw64.demo64.model.Credenciales;
import ec.edu.ups.ppw64.demo64.model.Usuario;
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

@Path("usuarios")
public class UsuarioServices {
	
	@Inject
	private GestionUsuarios gUsuarios;
	
	@Inject
	private GestionCarrito gCarrito;

	
	@POST
	@Produces(MediaType.APPLICATION_JSON) // Obtiene los parametros de las consultas y los envia
	@Consumes(MediaType.APPLICATION_JSON)
	public Response crear(Usuario usuario) {
		try {
			gUsuarios.guardarCuentas(usuario);
			gCarrito.crearCarritoParaUsuario(usuario.getCodigo());
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
	public Response actualizar(Usuario Usuario) {
		try {
			gUsuarios.actualizarCuenta(Usuario);
			return Response.ok(Usuario).build();
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
			gUsuarios.borrarCuenta(codigo);
			return "OK, se borro el Usuario";
		} catch (Exception e) {
			// TODO: handle exception
			return "Error al eliminar el Usuario";
		}
	}
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response leer(@QueryParam("codigo") int codigo) {
		try{
			System.out.println("cedula " +  codigo);
			Usuario cli = gUsuarios.getCuentaByCodigo(codigo);
			return Response.ok(cli).build();
		}catch (Exception e) {
			// TODO: handle exception
			ErrorMessage error = new ErrorMessage(4, "Cliente no existe");
			return Response.status(Response.Status.NOT_FOUND)
					.entity(error)
					.build();
		}
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("list")
	public Response getCuentas() {
		List<Usuario> Usuarios = gUsuarios.getCuentas();
		if (Usuarios.size() > 0)
			return Response.ok(Usuarios).build();

		ErrorMessage error = new ErrorMessage(6, "No se registran Cuentas");
		return Response.status(Response.Status.NOT_FOUND).entity(error).build();
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	@Path("validar-login")
	public Response validarLogin(Credenciales credenciales) {
	    boolean resultado = gUsuarios.validarCredenciales(credenciales);
	    if (resultado) {
	        return Response.ok().entity("Login Exitoso!!!").build();
	    } else {
	        return Response.status(Response.Status.UNAUTHORIZED).entity("Correo o contraseña incorrectos").build();
	    }
	}
	
	@GET
	@Path("usuario-logueado")
	@Produces(MediaType.APPLICATION_JSON)
	public Response obtenerUsuarioLogueado() {
	    Usuario usuario = gUsuarios.obtenerUsuarioLogueado();
	    System.out.println("jashduiahdgjsdhklhasdjgk"+usuario);
	    if(usuario != null) {
	        return Response.ok(usuario).build();
	    } else {
	        return Response.status(Response.Status.NOT_FOUND).entity("No hay ningún usuario logueado").build();
	    }
	}

}
