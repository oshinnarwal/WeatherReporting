package com.NDTV.RestAPIResources;

import java.io.IOException;
import java.util.HashSet;

import org.apache.http.HttpStatus;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;


public class RestRequestResponseExtract {

	public Response buildRequest(String method, String apiResource, String body) throws IOException {

		HashSet<String> set = new HashSet();
		set.add("q");
		set.add("appid");
		
		/**
		 * Building a request with base URI and query parameters
		 */
		RequestSpecBuilder request = RestRequestSpecify.requestSpecification();
		RequestSpecification httpRequest = RestRequestSpecify.requestSpecAddQueryParam(request, set).build();
		httpRequest.log().all();

		/**
		 * Getting the resource for the end point
		 */
		APIResources resourceAPI = APIResources.valueOf(apiResource);
		resourceAPI.getResource();
		RequestSpecification req = null;
		Response res = null;
		if (method.equalsIgnoreCase("POST")) {
		 req = RestRequestSpecify.getReqSpec(httpRequest, body);
		 res = req.when().post(resourceAPI.getResource());
		}
		
			
		if (method.equalsIgnoreCase("GET"))
			 req = RestRequestSpecify.getReqSpec(httpRequest);
			res = req.when().get(resourceAPI.getResource());
		return res;

	}

	public Response extractResponse(Response res) throws IOException {

		// Now the body of the message to see what response
		// we have recieved from the server
		RestRequestSpecify.checkStatusCode(res, HttpStatus.SC_OK);
		Response responseObj = RestRequestSpecify.extractResponse(res);
		return responseObj;

	}
}
