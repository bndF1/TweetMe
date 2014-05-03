package Persistence.TestsPersistence;

import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import org.junit.Test;

import twitter4j.auth.AccessToken;
import Persistence.OAuthDAOImp;

public class OAuthDAOImpTest {

	@Test
	public void testStoreAccessToken() throws Exception {
		AccessToken token = new AccessToken("token", "tokenSecret");
		long id = 999999999;
		Bussiness.Properties prop = new Bussiness.Properties();
		OAuthDAOImp classeTest = new OAuthDAOImp();
		classeTest.StoreAccessToken(id, token, prop);

		boolean expected = false;
		File twitter4j = new File("twitter4j.properties");

		BufferedReader reader = new BufferedReader(new FileReader(twitter4j));
		String line = null;
		StringBuilder stringBuilder = new StringBuilder();
		String ls = System.getProperty("line.separator");

		while ((line = reader.readLine()) != null) {
			stringBuilder.append(line);
			stringBuilder.append(ls);
		}
		
		if(stringBuilder.toString().contains("oauth.accessToken=token") &&
				stringBuilder.toString().contains("oauth.accessTokenSecret=tokenSecret")){
			expected = true;
		}
		assertTrue("Succed", expected);
		
	}

}
