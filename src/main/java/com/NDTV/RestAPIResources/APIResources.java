package com.NDTV.RestAPIResources;
/**
 * Return the resource which needs to be added to the base URI
 * @author onarwal
 *
 */
public enum APIResources {

	WEATHERAPI("/data/2.5/weather");
	
	
	private String resource;
	
	APIResources(String resource){
		this.resource=resource;
	}
	public String getResource() {
		return resource;
	}
}
