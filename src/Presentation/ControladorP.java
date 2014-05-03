package Presentation;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.auth.AccessToken;
import Bussiness.Controlador;
import Bussiness.ControladorFuente;
import Exceptions.DAOExcepcion;
import Persistence.DAL;

public class ControladorP extends ControladorFuente {
	private static ControladorP controlP;
	private Controlador controlB;
	
	private String PIN;
	private Twitter twitter;

	public ControladorP() throws DAOExcepcion {
		// dame dal
		try {
			dal = DAL.getInstance();

		} catch (DAOExcepcion e) {
			// TODO: handle exception
			throw e;
		}
	}

	public static ControladorP dameControlador() throws DAOExcepcion {
		// igual que dal pero controlador
		if (controlP == null)
			controlP = new ControladorP();
		return controlP;
	}

	public void StoreAccessToken(long useId, AccessToken accessToken,
			Bussiness.Properties prop) throws Exception {
		dal.StoreAccessToken(useId, accessToken, prop);
	}

	public void initOAuth(Twitter twitter, Controlador controlB)
			throws Exception {
		controlB.initOAuth(twitter, controlB);
	}

	public void lanzarPin() throws DAOExcepcion {
		IntroducirPIN inPin = new IntroducirPIN();
		inPin.setVisible(true);
	}

	public String saberPin() {
		return PIN;
	}

	public void setPin(String p) throws TwitterException, Exception {
		PIN = p;

	}

	public void setControlB(Controlador controlB) {
		this.controlB = controlB;
	}

	public Controlador getControlB() {
		return this.controlB;
	}

	public void continuar(String PIN) throws TwitterException, Exception {
		// TODO Auto-generated method stub
		this.controlB.continuar(PIN, getControlB(), getTwitter());
	}

	public void setTwitter(Twitter twitter) {
		// TODO Auto-generated method stub
		this.twitter = twitter;
	}

	public Twitter getTwitter() {
		return this.twitter;
	}

	public void avanzar() throws Exception {
		// TODO Auto-generated method stub
		Principal.avanzar();

	}

	public boolean isOAuth() throws Exception {
		// TODO Auto-generated method stub
		return controlB.isOAuth(getTwitter());
	}
}
