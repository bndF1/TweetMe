package Persistence;

import twitter4j.User;

public interface IDatosUsuario {
	public String urlIcono(User u);

	public User getUser();
	public void setUser(User u);
}
