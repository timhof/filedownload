package org.tfa;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import java.io.IOException;
import java.net.URI;

import javax.ws.rs.core.UriBuilder;

/**
 * Main class.
 *
 */
public class Main {

    /**
     * Main method.
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
    	URI baseUri = getBaseURI(System.getenv("HOSTNAME"), Integer.valueOf(System.getenv("PORT")));
   	 
        // create a resource config that scans for JAX-RS resources and providers
        // in org.tfa package
        final ResourceConfig rc = new ResourceConfig().packages("org.tfa.rest");

        // create and start a new instance of grizzly http server
        // exposing the Jersey application at BASE_URI
        System.out.println(String.format("Jersey app started with WADL available at "
                + "%sapplication.wadl", baseUri));
        GrizzlyHttpServerFactory.createHttpServer(baseUri, rc);
    }
    
    private static URI getBaseURI(String hostname, int port) {
        return UriBuilder.fromUri("http://0.0.0.0/").port(port).build();
    }

}

