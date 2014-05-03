package Bussiness.TestsBussiness;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Scanner;

import org.junit.Test;

import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import Bussiness.Controlador;
import Bussiness.OAuth;
import Presentation.ControladorP;

public class OAuthTest {

	@Test
	public void testOAuth() throws Exception {
		Twitter twt = new TwitterFactory().getInstance();
		Controlador controlador = new Controlador();
		ControladorP controladorP = new ControladorP();
		controlador.setControladorP(controladorP);
		Bussiness.Properties prop = new Bussiness.Properties();
		prop.connectProperties(("twitter4j.properties"));
		
		OAuth oauth = new OAuth(twt, prop, controlador);
		
		File twitter4j = new File("twitter4j.properties");
		BufferedReader reader = new BufferedReader(new FileReader(twitter4j));
		String line = null;
		StringBuilder stringBuilder = new StringBuilder();
		String ls = System.getProperty("line.separator");

		while ((line = reader.readLine()) != null) {
			stringBuilder.append(line);
			stringBuilder.append(ls);
		}
		
		String content = stringBuilder.toString();
		//Al dependre de Twitter els accessToken i els accessSecretToken, tan sols podem provar la següent combinació.
		// En cas de que funcione s'enten que funcionará per a tots aquells valors proveïts per Twitter.
		if(content.contains("oauth.accessToken") &&
				content.contains("oauth.accessTokenSecret")){
			AccessToken accessToken = new AccessToken(prop.getProperty("oauth.accessToken"), 
														prop.getProperty("oauth.accessTokenSecret"));
			assertEquals(accessToken, oauth.getAccessToken());
		}else{
			assertEquals(null, oauth.getAccessToken());
		}
		
		
		
	}

	@Test
	public void testContinuar() throws Exception {
		Twitter twt = new TwitterFactory().getInstance();
		String pin0 = "";
		Controlador controlador = new Controlador();
		ControladorP controladorP = new ControladorP();
		controlador.setControladorP(controladorP);
		Bussiness.Properties prop = new Bussiness.Properties();
		prop.connectProperties(("twitter4j.properties"));
		AccessToken accessToken = null;
		if(prop.containsKey("oauth.accessToken") && prop.containsKey("oauth.accessTokenSecret")){
			accessToken = new AccessToken(prop.getProperty("oauth.accessToken"), prop.getProperty("oauth.accessTokenSecret"));

		}
		
		OAuth oauth = new OAuth(twt, prop, controlador);
		if (accessToken != null){
			oauth.continuar(pin0, twt, controlador, prop);
			if(!oauth.getAccessToken().equals(accessToken)) fail("Error en els tokens.");
		}else{
			Scanner tec = new Scanner(System.in);
			String pinCorrecto = tec.next();
			oauth.continuar(pinCorrecto, twt, controlador, prop);
			if(oauth.getAccessToken().equals(null)) fail("Error en els tokens (pinCorrecto).");
			String pinErroneo = "-100000";
			try {
				oauth.continuar(pinErroneo, twt, controlador, prop);
			} catch (Exception e) {
				// TODO: handle exception
				if(oauth.getAccessToken() != null) fail("Error en els tokens (pinErroneo).");
			}
			//if(!oauth.getAccessToken().equals(null)) fail("Error en els tokens (pinErroneo).");
		}
		
		
		
		
	}

	@Test
	public void testIsOAuth() throws Exception {
		Bussiness.Properties prop = new Bussiness.Properties();
		File twitter4j = new File("twitter4j.properties");
		boolean expected = false;

		BufferedReader reader = new BufferedReader(new FileReader(twitter4j));
		String line = null;
		StringBuilder stringBuilder = new StringBuilder();
		String ls = System.getProperty("line.separator");

		while ((line = reader.readLine()) != null) {
			stringBuilder.append(line);
			stringBuilder.append(ls);
		}
		
		expected = OAuth.isOAuth(prop);
		
		if(stringBuilder.toString().contains("oauth.accessToken") &&
				stringBuilder.toString().contains("oauth.accessTokenSecret")){
			if(expected) assertTrue("Succed.", expected); else fail("Error.");
		}else{
			if(!expected) assertFalse("Succed.", expected); else fail("Error.");
		}

	}

}
