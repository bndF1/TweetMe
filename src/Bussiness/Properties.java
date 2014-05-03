package Bussiness;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.security.CodeSource;
import java.util.logging.Level;
import java.util.logging.Logger;
public class Properties {
	java.util.Properties prop;
	public Properties() throws Exception{
		 prop = new java.util.Properties();
		 
	}
	
	
	public void connectProperties(String arc) throws Exception{
		 
		    try {
		        CodeSource codeSource = Properties.class.getProtectionDomain().getCodeSource();
		        File jarFile = new File(codeSource.getLocation().toURI().getPath());
		        File jarDir = jarFile.getParentFile();
		        if (jarDir != null && jarDir.isDirectory()) {
		            File propFile = new File(jarDir, arc);
		            if(propFile.exists()){
		            	//prop = new java.util.Properties();
		            	prop.load(new BufferedReader(new FileReader(propFile.getAbsoluteFile())));
		            }else{
		            	//System.out.println(jarDir.getAbsolutePath()+"\\"+arc);
		            	//File propF = new File(jarDir.getAbsolutePath()+"\\"+arc);
		            	propFile.createNewFile();
		            	prop.put("oauth.consumerSecret", "HzHtr7csYvWFs2q9DnAmbY8I0pk3KkDa93u3AFXbg");
		            	prop.put("debug","true");
	            		prop.put("oauth.consumerKey","7Y7bxRje7IoeSj7rF9QkIQ");
		            	prop.store(new FileOutputStream(propFile), "Saved first time.");
		            	//prop.load(new BufferedReader(new FileReader(propF.getAbsoluteFile())));
		            	System.out.println("Se han cargado las propiedades.");
		            	System.out.println("key: "+prop.getProperty("oauth.consumerKey"));
		            	
		            }
		        }
		    } catch (URISyntaxException ex) {
		        Logger.getLogger(Properties.class.getName()).log(Level.SEVERE, null, ex);
		    } catch (FileNotFoundException ex) {
		        //System.err.println("No se encuentra el archivo: " + ex.getMessage());
		        throw new FileNotFoundException("No se econtró el archivo de propiedades...");
		    } catch (IOException ex) {
		        Logger.getLogger(Properties.class.getName()).log(Level.SEVERE, null, ex);
		    }
		    
		    }

	public boolean containsKey(String key) {
		// TODO Auto-generated method stub
		return prop.containsKey(key);
	}


	public String getProperty(String key) {
		// TODO Auto-generated method stub
		return prop.getProperty(key);
	}


	public void put(String key, String value) {
		// TODO Auto-generated method stub
		prop.put(key, value);
		
	}


	public void store(FileOutputStream f, String string) throws Exception {
		// TODO Auto-generated method stub
		prop.store(f, string);
		
	}
}