package Bussiness;

import javax.swing.JOptionPane;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;

public class OAuth {

	private static boolean OAuth;
	private RequestToken requestToken;
	private AccessToken accessToken = null;

	public OAuth(Twitter twitter, Bussiness.Properties prop, Controlador Control)
			throws Exception, Exception {
		OAuth = false;
		try {
			prop.connectProperties("twitter4j.properties");

			twitter.setOAuthConsumer(prop.getProperty("oauth.consumerKey"), prop.getProperty("oauth.consumerSecret"));

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		try {
			accessToken = twitter.getOAuthAccessToken();
		} catch (Exception e) {
			accessToken = null;
		}
		if (prop.containsKey("oauth.accessToken")
				&& prop.containsKey("oauth.accessTokenSecret")) {
			OAuth = true;
		} else {
			requestToken = twitter.getOAuthRequestToken();
			if (accessToken == null) {
				showPage();
				Control.lanzarPIN();
			}
		}
	}

	public void continuar(String pin, Twitter twitter, Controlador Control,
			Bussiness.Properties prop) throws TwitterException, Exception {
		try {
			if (pin.length() > 0) {
				accessToken = twitter.getOAuthAccessToken(requestToken, pin);
			} else {
				accessToken = twitter.getOAuthAccessToken();
			}
		} catch (TwitterException te) {
			if (401 == te.getStatusCode()) {
				System.out.println("Unable to get the access token.");
			} else {
				te.printStackTrace();
			}
			JOptionPane.showMessageDialog(null, te.getMessage(),
					te.getErrorMessage(), JOptionPane.ERROR_MESSAGE);
			accessToken = null;
		}
		// persist to the accessToken for future reference.
		if(accessToken != null){
			Control.StoreAccessToken(twitter.verifyCredentials().getId(),
					accessToken, prop);
			Control.avanzar();
		}

	}
	public static boolean isOAuth(Bussiness.Properties prop) {
		try {
			prop.connectProperties(("twitter4j.properties"));

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		if (prop.containsKey("oauth.accessToken")
				&& prop.containsKey("oauth.accessTokenSecret")) {
			OAuth = true;
		} else
			OAuth= false;

		return OAuth;
	}

	public void showPage() throws Exception {

		String osName = System.getProperty("os.name");
		String url = requestToken.getAuthorizationURL();

		if (osName.contains("Windows"))
			Runtime.getRuntime().exec(
					"rundll32 url.dll,FileProtocolHandler " + url);
		else if (osName.contains("Mac OS"))
			Runtime.getRuntime().exec("open " + url);
		else if (osName.indexOf("nix") >= 0 || osName.indexOf("nux") >= 0) {

			String[] browsers = { "epiphany", "firefox", "mozilla",
					"konqueror", "netscape", "opera", "links", "lynx" };

			StringBuffer cmd = new StringBuffer();
			for (int i = 0; i < browsers.length; i++)
				cmd.append((i == 0 ? "" : " || ") + browsers[i] + " \"" + url
						+ "\" ");

			Runtime.getRuntime().exec(
					new String[] { "sh", "-c", cmd.toString() });
		}
	}

	public static boolean isOAuth() {
		return OAuth;
	}

	public static void setOAuth(boolean oAuth) {
		OAuth = oAuth;
	}

	public RequestToken getRequestToken() {
		return requestToken;
	}

	public void setRequestToken(RequestToken requestToken) {
		this.requestToken = requestToken;
	}

	public AccessToken getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(AccessToken accessToken) {
		this.accessToken = accessToken;
	}
}
