package org.tfa.rest;

import java.sql.SQLException;
import java.util.HashMap;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.tfa.db.DAOManager;
import org.tfa.dto.SilanisCallbackDTO;

@Path("silanis/v1")
public class SilanisREST {

//		@POST
//	    @Consumes(MediaType.APPLICATION_JSON)
//	    @Path("/callback")
//	    public Response callback(SilanisCallbackDTO dto) {
//			
//			System.out.println(dto.toString());
//			
//			try {
//				DAOManager.getInstance().insertSilanisCallback(dto);
//				return Response.ok().build();
//			} catch (SQLException e) {
//				e.printStackTrace();
//				return Response.serverError().build();
//			}
//			
//		}
		
		@POST
	    @Consumes(MediaType.APPLICATION_JSON)
	    @Path("/callback")
	    public Response callback(HashMap<String, String> requestBody) {
			
			SilanisCallbackDTO callback = new SilanisCallbackDTO();
			callback.setName(requestBody.get("name"));
			callback.setPackageId(requestBody.get("packageId"));
			callback.setSessionUser(requestBody.get("sessionUser"));
			callback.setMessage(requestBody.get("message"));
			callback.setDocumentId(requestBody.get("documentId"));

			try {
				DAOManager.getInstance().insertSilanisCallback(callback);
				return Response.ok().build();
			} catch (SQLException e) {
				e.printStackTrace();
				return Response.serverError().build();
			}
			
		}
		
		@GET
	    @Produces(MediaType.APPLICATION_JSON)
	    @Path("/callbacks")
	    public Response listCalbacks() {
			
			try {
				return Response.ok(DAOManager.getInstance().getSilanisCallbacks()).build();
			} catch (SQLException e) {
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
}
