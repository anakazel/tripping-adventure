package com.composition;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by alexg on 05.06.2014.
 */
public class ServiceConfig {
    private Integer proxyPort;
    private String baseUrl;

    private Map<String, String> operations = new HashMap<String, String>();
    // url: operation name

    private String contentType;

    public static void main(String[] args) {
        System.out.println(Math.sin(Math.toRadians(30.0)) / Math.cos(Math.toRadians(30.0)));
        System.out.println(Math.sqrt(3)/ 2);
    }
}
