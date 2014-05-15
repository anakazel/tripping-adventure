package com.trigonometrical;

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
            ex.printStackTrace();
            port = 9006;
            System.out.println("Using default port 9006.");
        }
        Endpoint.publish("http://localhost:" + port + "/Trigonometrical", new TrigonometricalServiceImpl());
    }
}
