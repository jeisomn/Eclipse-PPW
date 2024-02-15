package ec.edu.ups.ppw64.demo64.model;

public class CargarCuentaCarrito {

	private int cuenta;
	private int carrito;

	public int getCuenta() {
		return cuenta;
	}

	public void setCuenta(int cuenta) {
		this.cuenta = cuenta;
	}

	public int getCarrito() {
		return carrito;
	}

	public void setCarrito(int carrito) {
		this.carrito = carrito;
	}

	@Override
	public String toString() {
		return "CargarCuentaCarrito [cuenta=" + cuenta + ", carrito=" + carrito + "]";
	}

}
