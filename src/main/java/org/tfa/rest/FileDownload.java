package org.tfa.rest;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.StreamingOutput;

/**
 * 
 */
@Path("/document/v1")
public class FileDownload {

	@GET
    @Path("/bulk")
    @Produces({"application/zip"})
    public Response bulkDocumentDownloadFile(@QueryParam("filePath") String filePath, 
    		@QueryParam("displayFileName") String displayFileName) {
   
        File file = new File(filePath);
//        StreamingOutput out = new FileStreamingOutput(file);
        return Response.ok(new FileStreamingOutput(file), MediaType.APPLICATION_OCTET_STREAM).header("Content-Disposition", "attachment; filename=" + displayFileName).build();
        
    }

   
    
    class FileStreamingOutput implements StreamingOutput {

        private File file;

        public FileStreamingOutput(File file) {
            this.file = file;
        }

        @Override
        public void write(OutputStream output)
                throws IOException, WebApplicationException {
            FileInputStream input = new FileInputStream(file);

            int bytesRead;
            int bytesBuffered = 0;
            try {
                while ((bytesRead = input.read()) != -1) {
                	output.write(bytesRead );
                    bytesBuffered += bytesRead;
//                    if (bytesBuffered > 1024 * 1024) { //flush after 1MB
//                        bytesBuffered = 0;
//                        output.flush();
//                    }
                }
            }
            catch(Throwable t){
            	System.out.println(t);
            	throw(new WebApplicationException(t));
            } finally {
                if (input != null) input.close();
                
                if (output != null) {
                	output.flush();
                }
            }
        }

    }
}
