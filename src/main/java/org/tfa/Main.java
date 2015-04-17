package org.tfa;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import java.io.IOException;
import java.net.URI;

/**
 * Main class.
 *
 */
public class Main {

    /**
     * Starts Grizzly HTTP server exposing JAX-RS resources defined in this application.
     * @return Grizzly HTTP server.
     */
    public static HttpServer startServer() {
    	
    	 final String baseUri = "http://localhost:"+(System.getenv("PORT")!=null?System.getenv("PORT"):"8080")+"/";
    	 
        // create a resource config that scans for JAX-RS resources and providers
        // in org.tfa package
        final ResourceConfig rc = new ResourceConfig().packages("org.tfa.rest");

        // create and start a new instance of grizzly http server
        // exposing the Jersey application at BASE_URI
        return GrizzlyHttpServerFactory.createHttpServer(URI.create(baseUri), rc);
    }

    /**
     * Main method.
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
    	final String baseUri = "http://localhost:"+(System.getenv("PORT")!=null?System.getenv("PORT"):"8080")+"/";
   	 
        // create a resource config that scans for JAX-RS resources and providers
        // in org.tfa package
        final ResourceConfig rc = new ResourceConfig().packages("org.tfa.rest");

        // create and start a new instance of grizzly http server
        // exposing the Jersey application at BASE_URI
        System.out.println(String.format("Jersey app started with WADL available at "
                + "%sapplication.wadl", baseUri));
        GrizzlyHttpServerFactory.createHttpServer(URI.create(baseUri), rc);
    }
}

