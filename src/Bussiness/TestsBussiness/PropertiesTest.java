package Bussiness.TestsBussiness;

import static org.junit.Assert.assertTrue;

import java.io.File;
import java.security.CodeSource;

import org.junit.Test;

import Bussiness.Properties;


public class PropertiesTest {

	@Test
	public void testConnectProperties() throws Exception{
		boolean expected = false;
		
		Properties prop = new Properties();
		prop.connectProperties("twitter4j.properties");
		
		CodeSource codeSource = Properties.class.getProtectionDomain().getCodeSource();
        File jarFile = new File(codeSource.getLocation().toURI().getPath());
        File jarDir = jarFile.getParentFile();
        File propFile = new File(jarDir, "twitter4j.properties");
        	 if(propFile.exists()){
        		 expected = prop.getProperty("oauth.consumerKey") != null;
        	 }
        assertTrue("Error",expected );	 
	}
}
