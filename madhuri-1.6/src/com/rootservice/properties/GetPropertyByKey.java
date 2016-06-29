package com.rootservice.properties;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class GetPropertyByKey {

	public String getProperty(String key) throws IOException{
	
		String result = null;
		InputStream inputStream = null;
		
		try {
			Properties prop = new Properties();
			String propFileName = "service.properties";

			inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);

			if (inputStream != null) {
				prop.load(inputStream);
			} else {
				throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
			}
			// get the property value and print it out
			result = prop.getProperty(key);
		} catch (Exception e) {
			System.out.println("Exception: " + e);
		} finally {
			inputStream.close();
		}
		return result;
	}
	public static void main(String args[]) throws IOException{
		GetPropertyByKey getkey = new GetPropertyByKey();
		System.out.println(getkey.getProperty("getdirection"));
	}
}
