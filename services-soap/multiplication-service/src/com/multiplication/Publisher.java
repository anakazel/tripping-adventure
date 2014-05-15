package com.multiplication;

import javax.xml.ws.Endpoint;

/**
 * Created by alexg on 10.05.2014.
 */
public final class Publisher {
    public static void main(String[] args) {
        int port;
        try{
            port = Integer.valueOf(args[0]);
        }catch(Exception ex){
            port = 9005;
            System.out.println(ex);
            System.out.println("Using default port 9005.");
        }
        Endpoint.publish("http://localhost:" + port + "/Multiplication", new MultiplicationServiceImpl());
    }
}
