package com.composition.proxy;

import com.composition.model.Operation;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;

import static com.composition.Deployer.*;
import static com.composition.Deployer.OPERATIONS;
import static com.composition.utils.HttpUtils.*;

/**
 * A HTTP Servlet meant to route requests
 * @author alexg
 */
public final class ProxyServlet extends HttpServlet {

    @Override
    public void doGet (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        throw new RuntimeException("Not yet implemented!");
    }

    @Override
    public void doPost (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final String requestUrl = req.getRequestURL().toString();
        for(int i = 0; i < OPERATIONS.size(); i++){
            if(requestUrl.contains(OPERATIONS.get(i).getLocation())
                    && (OPERATIONS.get(i).getHttpMethod().equals("POST") ||
                        OPERATIONS.get(i).getHttpMethod().equals("PUT") ||
                        OPERATIONS.get(i).getHttpMethod().equals("DELETE"))){

                final Operation operation = OPERATIONS.get(i);
                final PrintWriter pw = resp.getWriter();
                final String response;
                HttpURLConnection connection = null;

                String requestBody;
                requestBody = getHttpBody(req);
                requestBody = URLDecoder.decode(requestBody, "UTF-8");
                requestBody = requestBody.replace("requestString=", "");

                URL url = new URL(operation.getUrl());
                if(!operation.getHttpMethod().equals("DELETE")){
                    connection = (HttpURLConnection) url.openConnection();

                    connection.setRequestMethod(operation.getHttpMethod());
                    connection.setRequestProperty("Accept", operation.getResponseContentType());
                    connection.setRequestProperty("Content-Type", operation.getRequestContentType());
                    connection.setDoOutput(true);
                    connection.getOutputStream().write(requestBody.getBytes());

                    final BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    response = getHttpResponse(br);
                    br.close();
                }else{
                    final String param = requestBody.substring(0, requestBody.indexOf("="));
                    final String value = requestBody.substring(requestBody.indexOf("=") + 1, requestBody.length());
                    final String deleteUrl = operation.getUrl() + "?" + param + "=" + URLEncoder.encode(value);
                    // see HttpUtils.doHttpDelete
                    response = doHttpDelete(deleteUrl, operation.getResponseContentType());
                }

                // will use always xml content type on the response, because of the BPEL limitations
                resp.setContentType("text/xml");
                if(operation.getResponseContentType().equals("application/json")){
                    pw.append("<" + context + "><![CDATA[");
                    pw.append(response);
                    pw.append("]]></" + context + " >");
                }else{
                    pw.append(response);
                }

                if(connection != null){
                    connection.disconnect();
                }

                System.out.println("URL: " + url);
                System.out.println("REQUEST: " + requestBody);
                System.out.println("RESPONSE: " + response);
                return;
            }
        }
    }
}
