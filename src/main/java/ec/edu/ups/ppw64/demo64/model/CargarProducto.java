package ec.edu.ups.ppw64.demo64.model;

public class CargarProducto {

	private int carrito;
	private int producto;
	private int cantidad;

	public int getCarrito() {
		return carrito;
	}

	public void setCarrito(int carrito) {
		this.carrito = carrito;
	}

	public int getProducto() {
		return producto;
	}

	public void setProducto(int producto) {
		this.producto = producto;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	@Override
	public String toString() {
		return "CargarProducto [carrito=" + carrito + ", producto=" + producto + ", cantidad=" + cantidad + "]";
	}
}
