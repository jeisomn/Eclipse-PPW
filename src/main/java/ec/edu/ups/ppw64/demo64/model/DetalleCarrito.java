package ec.edu.ups.ppw64.demo64.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class DetalleCarrito {

	@Id
	@GeneratedValue
	private int codigo;

    @ManyToOne
    @JoinColumn(name = "codigo_producto")
    private Producto producto;

    private int cantidad;
    private float precio;
    private float subtotal;
    private float total;
    

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public Producto getProducto() {
		return producto;
	}

	public void setProducto(Producto producto) {
		this.producto = producto;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}

	public float getSubtotal() {
		return subtotal;
	}

	public void setSubtotal(float subtotal) {
		this.subtotal = subtotal;
	}
	
	public float getTotal() {
		return total;
	}

	public void setTotal(float total) {
		this.total = total;
	}

	@Override
	public String toString() {
		return "DetalleCarrito [codigo=" + codigo + ", producto=" + producto + ", cantidad="
				+ cantidad + ", precio=" + precio + ", subtotal=" + subtotal + ", total=" + total + "]";
	}
}
