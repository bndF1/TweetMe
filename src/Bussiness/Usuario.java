package Bussiness;

public class Usuario {
	/**
	 * Classe "Usuario". Interessa guardar el seu nom d'usuari, el email, autom�ticament traguem la seua p�gina del perfil de twitter
	 * Podem ficar el nom real, per� aix� es pot traure de la p�gina de twitter.
	 */
	private String email, nombreUsuario, ubicacion, biografia ;
	private Object icono;
	public Usuario(String nombreUsuario){
		this.setNombreUsuario(nombreUsuario);
	}
	public void setNombreUsuario(String nombreUsuario) {
		this.nombreUsuario = nombreUsuario;
	}
	public String getNombreUsuario() {
		return nombreUsuario;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getEmail() {
		return email;
	}
	public void setBiografia(String biografia) {
		this.biografia = biografia;
	}
	public String getBiografia() {
		return biografia;
	}
	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}
	public String getUbicacion() {
		return ubicacion;
	}
	public Object getIcono(){
		return icono;
	}
}
