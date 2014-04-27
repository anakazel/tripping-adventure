package soapwrapper.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

/**
 * Created by AlexG on 4/26/14.
 */
public class RestHttpClient {

    private String baseUrl;

    public RestHttpClient(String baseUrl){
        this.baseUrl = baseUrl;
    }

    public String sendRequest(Map<String, String> params){
            String response = null;
            if(params == null || params.size() == 0){
                return response;
            }
            try {
                final Set<String> paramsSet = params.keySet();
                final List<String> p = new ArrayList<>(paramsSet);
                final int size = params.size();
                String urlString = baseUrl + "?" + p.get(0) + "=" + params.get(p.get(0));
                for(int i = 1; i < size; i++){
                    urlString = urlString + "&" + p.get(i) + "=" + params.get(p.get(i));
                }
                final URL url = new URL(urlString);
                final HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.setRequestProperty("Accept", "application/xml");
                final BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                System.out.println("URL: " + urlString);
                final StringBuilder output = new StringBuilder();
                String out;
                while((out = br.readLine()) != null){
                    output.append(out);
                }
                connection.disconnect();
                response = output.toString();
                System.out.println("RESPONSE: " + response);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return response;
    }
}
