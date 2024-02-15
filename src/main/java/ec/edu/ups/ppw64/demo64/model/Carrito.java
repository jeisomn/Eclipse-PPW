package ec.edu.ups.ppw64.demo64.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.json.bind.annotation.JsonbTransient;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Carrito {

	@Id
	@GeneratedValue
	private int codigo;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name="codigo_detalles")
	private List<DetalleCarrito> detalles = new ArrayList<DetalleCarrito>();
	
	@OneToOne(mappedBy = "carrito", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name="codigo_usuario")
	private Usuario usuario;
	
	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public List<DetalleCarrito> getDetalles() {
		return detalles;
	}

	public void setDetalles(List<DetalleCarrito> detalles) {
		this.detalles = detalles;
	}
	
	public void addDetalle (DetalleCarrito detalle) {
		if(detalles == null) {
			detalles = new ArrayList<DetalleCarrito>();
		}
		detalles.add(detalle);
	}

	public void removeDetalle(DetalleCarrito detalle) {
        detalles.remove(detalle);
    }

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@Override
	public String toString() {
		return "Carrito [codigo=" + codigo + ", detalles=" + detalles + ", usuario=" + usuario + "]";
	}

}
