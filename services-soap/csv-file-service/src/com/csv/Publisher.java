package com.csv;

import javax.xml.ws.Endpoint;

/**
 * Created by alexg on 12.05.2014.
 */
public final class Publisher {
    public static void main(String[] args) {
        int port;
        try{
            port = Integer.valueOf(args[0]);
        }catch(Exception ex){
            System.out.println(ex);
            port = 9002;
            System.out.println("Using default port 9002");
        }
        Endpoint.publish("http://localhost:" + port + "/CSVFile", new CSVFileServiceImpl());
    }
}
