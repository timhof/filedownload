package org.tfa.rest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.tfa.db.DAOManager;
import org.tfa.dto.SilanisCallbackDTO;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.silanis.esl.sdk.Document;
import com.silanis.esl.sdk.DocumentPackage;
import com.silanis.esl.sdk.EslClient;
import com.silanis.esl.sdk.PackageId;
import com.silanis.esl.sdk.Signer;

@Path("silanis/v1")
public class SilanisREST {

		@POST
	    @Consumes(MediaType.APPLICATION_JSON)
	    @Path("/callback")
	    public Response callback(String requestBody) {
			
			System.out.println(requestBody);
			
			String name = getRequestValue(requestBody, "name");
			String packageId = getRequestValue(requestBody, "packageId");
			String documentId = getRequestValue(requestBody, "documentId");
			String message = getRequestValue(requestBody, "message");
			String sessionUser = getRequestValue(requestBody, "sessionUser");
			
			SilanisCallbackDTO callback = new SilanisCallbackDTO();
			callback.setName(name);
			callback.setPackageId(packageId);
			callback.setSessionUser(sessionUser);
			callback.setMessage(message);
			callback.setDocumentId(documentId);
			
			try {
				DAOManager.getInstance().insertSilanisCallback(callback);
				return Response.ok().build();
			} catch (SQLException e) {
				e.printStackTrace();
				return Response.serverError().build();
			}
			
		}
		
		private String getRequestValue(String requestBody, String key){
			String value = null;
			if(requestBody.indexOf(key + "\":\"") >= 0){
				int valueStart = requestBody.indexOf(key + "\":\"")+((key + "\":\"").length());
				int valueEnd = requestBody.indexOf("\"", valueStart);
				value = requestBody.substring(valueStart, valueEnd);
			}
			return value;
		}

		@GET
	    @Produces(MediaType.APPLICATION_JSON)
	    @Path("/callbacks")
	    public Response listCalbacks() {
			
			try {
				Gson gson = new GsonBuilder().setPrettyPrinting().create();
				String json = gson.toJson(DAOManager.getInstance().getSilanisCallbacks());
				return Response.ok(json).build();
			} catch (SQLException e) {
				e.printStackTrace();
				return Response.serverError().build();
			}
		}
		
		@GET
	    @Produces(MediaType.APPLICATION_JSON)
	    @Path("/package/{packageId}")
	    public Response getPackageInfo(@PathParam("packageId") String packageId)  {
			
			try {
				String prettyJsonString = getResponse("packages/" + packageId);
				
				return Response.ok(prettyJsonString).build();
			} catch (Exception e) {
				e.printStackTrace();
				return Response.serverError().build();
			}
		}
		
		@GET
	    @Produces(MediaType.APPLICATION_JSON)
	    @Path("/package/{packageId}/signers")
	    public Response getPackageSigners(@PathParam("packageId") String packageId)  {
			
			try {
				DocumentPackage documentPackage = getPackage(packageId);
				Map<String, Signer> signers = documentPackage.getSigners();
				
				Gson gson = new GsonBuilder().setPrettyPrinting().create();
				String json = gson.toJson(signers);
				return Response.ok(json).build();
			} catch (Exception e) {
				e.printStackTrace();
				return Response.serverError().build();
			}
		}
		
		@GET
	    @Produces(MediaType.APPLICATION_JSON)
	    @Path("/package/{packageId}/documents")
	    public Response getPackageDocuments(@PathParam("packageId") String packageId)  {
			
			try {
				DocumentPackage documentPackage = getPackage(packageId);
				Collection<Document> documents = documentPackage.getDocuments();
				
				Gson gson = new GsonBuilder().setPrettyPrinting().create();
				String json = gson.toJson(documents);
				return Response.ok(json).build();
			} catch (Exception e) {
				e.printStackTrace();
				return Response.serverError().build();
			}
		}
		
		@GET
	    @Produces(MediaType.APPLICATION_JSON)
	    @Path("package/{packageId}/signingStatus")
	    public Response getPackageSigningStatus(@PathParam("packageId") String packageId)  {
			
			try {
				
				String prettyJsonString = getResponse("packages/" + packageId + "/signingStatus");
				
				return Response.ok(prettyJsonString).build();
			} catch (Exception e) {
				e.printStackTrace();
				return Response.serverError().build();
			}
		}
		
		@GET
	    @Produces(MediaType.APPLICATION_JSON)
	    @Path("package/{packageId}/roles")
	    public Response getPackageRoles(@PathParam("packageId") String packageId)  {
			
			try {
				
				String prettyJsonString = getResponse("packages/" + packageId + "/roles");
				
				return Response.ok(prettyJsonString).build();
			} catch (Exception e) {
				e.printStackTrace();
				return Response.serverError().build();
			}
		}
		
		@GET
	    @Produces(MediaType.APPLICATION_JSON)
	    @Path("account")
	    public Response getAccount()  {
			
			try {
				
				String prettyJsonString = getResponse("account");
				
				return Response.ok(prettyJsonString).build();
			} catch (Exception e) {
				e.printStackTrace();
				return Response.serverError().build();
			}
		}
		
		@GET
	    @Produces(MediaType.APPLICATION_JSON)
	    @Path("account/senders")
	    public Response getAccountSenders()  {
			
			try {
				
				String prettyJsonString = getResponse("account/senders");
				
				return Response.ok(prettyJsonString).build();
			} catch (Exception e) {
				e.printStackTrace();
				return Response.serverError().build();
			}
		}
		
		@GET
	    @Produces(MediaType.APPLICATION_JSON)
	    @Path("package/{packageId}/document/{documentId}")
	    public Response getDocumentInfo(@PathParam("packageId") String packageId, @PathParam("documentId") String documentId)  {
			
			try {
				
				String prettyJsonString = getResponse("packages/" + packageId + "/documents/" + documentId);
				
				return Response.ok(prettyJsonString).build();
			} catch (Exception e) {
				e.printStackTrace();
				return Response.serverError().build();
			}
		}
		
		@GET
	    @Produces(MediaType.APPLICATION_JSON)
	    @Path("user/{sessionUser}/journal")
	    public Response getUserJournal(@PathParam("sessionUser") String sessionUser)  {
			
			try {
				
				String prettyJsonString = getResponse("user/" + sessionUser + "/journal");
				
				return Response.ok(prettyJsonString).build();
			} catch (Exception e) {
				e.printStackTrace();
				return Response.serverError().build();
			}
		}
		
		@DELETE
	    @Path("/callbacks/{callbackId}")
	    public Response deleteCalback(@PathParam("callbackId") Integer callbackId) {
			
			try {
				DAOManager.getInstance().deleteSilanisCallback(callbackId);
				return Response.ok().build();
			} catch (SQLException e) {
				e.printStackTrace();
				return Response.serverError().build();
			}
		}
		
		private DocumentPackage getPackage(String packageId) throws IOException{
			EslClient eslClient = getEslClient();
			return eslClient.getPackage(new PackageId(packageId));
		}
		
		private EslClient getEslClient() throws IOException{
			return new EslClient(getSilanisProperties().getProperty("apikey"), "https://sandbox.e-signlive.com/api/");
		}
		
		private String getResponse(String path) throws IOException{
			
			DefaultHttpClient httpClient = new DefaultHttpClient();
			HttpGet getRequest = new HttpGet(
				"https://sandbox.e-signlive.com/api/" + path);
			getRequest.addHeader("accept", "application/json; esl-api-version=10.6");
			getRequest.setHeader("Authorization", "Basic " + getSilanisProperties().getProperty("apikey"));
			HttpResponse response = httpClient.execute(getRequest);
	 
			if (response.getStatusLine().getStatusCode() != 200) {
				throw new RuntimeException("Failed : HTTP error code : "
				   + response.getStatusLine().getStatusCode());
			}
	 
			String responseStr = IOUtils.toString(new InputStreamReader(response.getEntity().getContent()));
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			JsonParser jp = new JsonParser();
			JsonElement je = jp.parse(responseStr);
			String prettyJsonString = gson.toJson(je);
			
			httpClient.getConnectionManager().shutdown();
			
			return prettyJsonString;
		}
		
		private Properties getSilanisProperties() throws IOException{
			String resourceName = "silanis.properties"; // could also be a constant
			ClassLoader loader = Thread.currentThread().getContextClassLoader();
			Properties props = new Properties();
			try(InputStream resourceStream = loader.getResourceAsStream(resourceName)) {
			    props.load(resourceStream);
			}
			return props;
		}
}
