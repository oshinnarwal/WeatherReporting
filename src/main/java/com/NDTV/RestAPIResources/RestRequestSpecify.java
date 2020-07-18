package com.NDTV.RestAPIResources;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RestRequestSpecify {
	static RequestSpecBuilder httpRequest;
	/**
	 * Adding the base URI
	 * @return
	 * @throws IOException
	 */
	public static RequestSpecBuilder requestSpecification() throws IOException {
		return new RequestSpecBuilder().setBaseUri(APIUtils.getGlobalProperty(AutomationConstants.baseURL))
				;
	}
	/**
	 * Adding the query parameters to the base URI
	 * @param req
	 * @param keys
	 * @return
	 * @throws IOException
	 */
	public static RequestSpecBuilder requestSpecAddQueryParam(RequestSpecBuilder req, HashSet<String> keys)
			throws IOException {

		Iterator it = keys.iterator();
		while (it.hasNext()) {
			String key = (String) it.next();
			req.addQueryParam(key, APIUtils.getGlobalProperty(key));
		}
		return req;

	}
	/**
	 * adding headers to the request
	 * @param req
	 * @param map
	 * @return
	 * @throws IOException
	 */
	public static RequestSpecBuilder requestSpecAddHeaders(RequestSpecBuilder req, HashMap<String, String> map)
			throws IOException {
		return req.addHeaders(map);

	}
	/**
	 * Setting the final request with given()
	 * @param request
	 * @param body
	 * @return
	 */
	public static RequestSpecification getReqSpec(RequestSpecification request, String body) {
		RequestSpecification response = RestAssured.given().spec(request).body(body);
		response.log().all();
		return response;
	}
	
	public static RequestSpecification getReqSpec(RequestSpecification request) {
		RequestSpecification response = RestAssured.given().spec(request);
		response.log().all();
		return response;
	}
	/**
	 * Checking status code
	 * @param response
	 * @param expectedStatusCode
	 */
	public static void checkStatusCode(Response response, int expectedStatusCode) {
		response.then().assertThat().statusCode(expectedStatusCode);
	}
	/**
	 * Extracting the response
	 * @param response
	 * @return
	 */
	public static Response extractResponse(Response response) {
		return response.then().extract().response();
	}

}
