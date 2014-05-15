package com.progression;

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
            System.out.println("Using default port 9001.");
            port = 9001;
            System.out.println(ex);
        }
        Endpoint.publish("http://localhost:" + port + "/ArithmeticProgression", new ArithmeticProgressionServiceImpl());
    }
}
