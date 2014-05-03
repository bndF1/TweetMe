package Presentation;

import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import Bussiness.Controlador;

public class Comunicador {

	private Controlador controlB;
	private ControladorP controlP;
	private Twitter twitter;
	//private static FactoryControlador factoryControlador;
	
	public Comunicador() throws Exception{
		twitter = new TwitterFactory().getInstance();
		controlB =  FactoryControlador.getControlador();
		controlP =  FactoryControlador.getControladoP();
		controlB.setControladorP(controlP);
		controlP.setControlB(controlB);
		controlP.setTwitter(twitter);
	}

	public Controlador getControlB() {
		return controlB;
	}

	public ControladorP getControlP() {
		return controlP;
	}

	public Twitter getTwitter() {
		return twitter;
	}
	
}
