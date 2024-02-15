package ec.edu.ups.ppw64.demo64.model;

public class Credenciales {

	private String correo;
	private String contrasenia;
	private boolean accedio;

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

	public boolean isAccedio() {
		return accedio;
	}

	public void setAccedio(boolean accedio) {
		this.accedio = accedio;
	}

	@Override
	public String toString() {
		return "Credenciales [correo=" + correo + ", contrasenia=" + contrasenia + ", accedio="
				+ accedio + "]";
	}
}
