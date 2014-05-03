package Persistence;

import java.io.FileOutputStream;

import twitter4j.auth.AccessToken;

public class OAuthDAOImp implements IOAuthDAO {
	
	public OAuthDAOImp(){
		super();
	}
	
	@Override
	public void StoreAccessToken(long useId, AccessToken accessToken,
			Bussiness.Properties prop) throws Exception {
		prop.put("oauth.accessToken", accessToken.getToken());
		prop.put("oauth.accessTokenSecret", accessToken.getTokenSecret());
		FileOutputStream f = new FileOutputStream("twitter4j.properties");
		prop.store(f,
				"Saving access token");
		f.close();
	
		
	}
}
