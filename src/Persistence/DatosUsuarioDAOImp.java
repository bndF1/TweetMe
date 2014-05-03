package Persistence;

import twitter4j.User;

public class DatosUsuarioDAOImp implements IDatosUsuario {
	User u;

	public DatosUsuarioDAOImp(){
		
	}
	public DatosUsuarioDAOImp(User u) {
		this.u = u;
	}

	@SuppressWarnings("deprecation")
	@Override
	public String urlIcono(User u) {
		return u.getProfileImageURL();
	}

	@Override
	public User getUser() {
			return u;
	}
	@Override
	public void setUser(User u){
		this.u=u;
	}

}
