package Bussiness;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.auth.AccessToken;
import Exceptions.DAOExcepcion;
import Persistence.DAL;
import Presentation.ControladorP;

public class Controlador extends ControladorFuente {
	private static Controlador control;
	
	private ControladorP controlP;
	private Bussiness.Properties prop;
	private OAuth OAuth;
	public Controlador() throws Exception{
		//dame dal
		try{
			dal=DAL.getInstance();
			prop  = new Bussiness.Properties();
		//	controlP=ControladorP.dameControlador();
		}catch (DAOExcepcion e) {
			// TODO: handle exception
			throw e;
		}
	}
	public static Controlador dameControlador() throws Exception{
		//igual que dal pero controlador
		if(control==null)
			control = new Controlador();
		 return control;
	}
	public void StoreAccessToken(long useId, AccessToken accessToken,
			Bussiness.Properties prop) throws Exception {
		dal.StoreAccessToken(useId, accessToken, prop);
	}	
	
	public void initOAuth(Twitter twitter, Controlador control) throws Exception {
		OAuth = new OAuth(twitter, prop, control);
	}
	public void lanzarPIN() throws DAOExcepcion {
		controlP.lanzarPin();		
	}
	public String saberPIN(){
		return controlP.saberPin();
	}
	public void setControladorP(ControladorP controlP){
		this.controlP=controlP;
	}
	public void continuar(String pin, Controlador control, Twitter twitter) throws TwitterException, Exception{
		OAuth.continuar(pin,twitter, control, prop );
	}
	public void avanzar() throws Exception {
		// TODO Auto-generated method stub
		controlP.avanzar();
	}
	public boolean isOAuth(Twitter twitter) throws Exception {
		// TODO Auto-generated method stub
		OAuth = new OAuth(twitter, prop, control);
	return Bussiness.OAuth.isOAuth(prop);
	}
}
