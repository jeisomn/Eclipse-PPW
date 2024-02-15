package ec.edu.ups.ppw64.demo64.model;

import java.util.Date;
import java.sql.Time;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

@Entity
public class Factura {

	@Id
	@GeneratedValue
	private int codigo;
	private String numero;

	private double total;
	private String fechaEmision;

	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "codigo_detalles")
	private List<DetalleFactura> detalles;

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public String getFechaEmision() {
		return fechaEmision;
	}

	public void setFechaEmision(String fechaEmision) {
		this.fechaEmision = fechaEmision;
	}

	public List<DetalleFactura> getDetalles() {
		return detalles;
	}

	public void setDetalles(List<DetalleFactura> detalles) {
		this.detalles = detalles;
	}

	public void addDetalle(DetalleFactura detalle) {
		if (detalles == null)
			detalles = new ArrayList<DetalleFactura>();

		detalles.add(detalle);
	}

	@Override
	public String toString() {
		return "Factura [codigo=" + codigo + ", numero=" + numero + ", total=" + total
				+ ", fechaEmision=" + fechaEmision + ", detalles=" + detalles + "]";
	}
}
