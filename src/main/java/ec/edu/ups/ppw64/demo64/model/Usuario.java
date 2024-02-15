package ec.edu.ups.ppw64.demo64.model;

import jakarta.json.bind.annotation.JsonbTransient;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;

@Entity
public class Usuario {

	@Id
	@GeneratedValue
	private int codigo;

	private String nombre;
	private String apellido;
	private String cedula;
	private String correo;
	private String contrasenia;
	private boolean accedio;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "codigo_carrito")
	@JsonbTransient
	private Carrito carrito;

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getContrasenia() {
		return contrasenia;
	}

	public void setContrasenia(String contrasenia) {
		this.contrasenia = contrasenia;
	}

	public String getCedula() {
		return cedula;
	}

	public void setCedula(String cedula) {
		this.cedula = cedula;
	}

	public Carrito getCarrito() {
		return carrito;
	}

	public void setCarrito(Carrito carrito) {
		this.carrito = carrito;
	}

	public boolean isAccedio() {
		return accedio;
	}

	public void setAccedio(boolean accedio) {
		this.accedio = accedio;
	}

	@Override
	public String toString() {
	    return "Usuario{" +
	            "codigo=" + codigo +
	            ", nombre='" + nombre + '\'' +
	            ", apellido='" + apellido + '\'' +
	            ", correo='" + correo + '\'' +
	            '}';
	}

}
