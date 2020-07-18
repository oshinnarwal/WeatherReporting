package com.NDTV.RestAPIResources;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
/**
 * 
 * 
 * @author onarwal
 *
 */
public class APIUtils {

	public static String getGlobalProperty(String key) throws IOException {
		
		Properties prop=new Properties();
		FileInputStream fis=new FileInputStream("/Users/oshinnarwal/Documents/TestVegNDTV/WeatherReporting/src/main/java/com/NDTV/RestAPIResources/Global.properties");
		prop.load(fis);
		
		
		return prop.getProperty(key);
		
	}
	
public static String writeGlobalProperty(String key) throws IOException {
		
		Properties prop=new Properties();
		FileInputStream fis=new FileInputStream("/Users/oshinnarwal/Documents/TestVegNDTV/WeatherReporting/src/main/java/com/NDTV/RestAPIResources/Global.properties");
		prop.load(fis);
		
		
		return prop.getProperty(key);
		
	}
}
