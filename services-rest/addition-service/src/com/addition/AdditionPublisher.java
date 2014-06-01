package com.addition;
import com.sun.jersey.simple.container.SimpleServerFactory;

import java.io.IOException;

/**
 * Created by alexg on 31.05.2014.
 */
public class AdditionPublisher {
    public static void main(String[] args) throws IOException {
        int port;
        try{
            port = Integer.valueOf(args[0]);
        }catch(Exception ex){
            port = 9000;
            System.out.println(ex);
        }
        // Creates a server and listens on the address below.
        // Scans classpath for JAX-RS resources
        SimpleServerFactory.create("http://localhost:" + port);
        System.out.println("Using port " + port + ".");
    }
}
