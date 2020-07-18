package com.NDTV.RestAPIResources;

import java.util.LinkedHashMap;
import java.util.Map;

import org.json.simple.JSONObject;

/**
 * This class prepares the body and return it we can either use the POJo classes to build the body or we can simply make the body with map
 * @author onarwal
 *
 */
public class TestData {
	static String body;

	public static String getPayloadLogin(String username, String password) {

		JSONObject requestParams = new JSONObject();
		requestParams.put("dummy",  "dummy");
		requestParams.put("dummy", "password");
		body = requestParams.toJSONString();
		System.out.println(body);

		return body;

	}

}
