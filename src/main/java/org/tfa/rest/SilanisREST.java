package org.tfa.rest;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import org.tfa.dto.SilanisCallbackDTO;

@Path("silanis/v1")
public class SilanisREST {

		@POST
	    @Path("/callback")
	    public Response callback(SilanisCallbackDTO dto) {
			
			System.out.println(dto.toString());
			
			return Response.ok().build();
		}
}
