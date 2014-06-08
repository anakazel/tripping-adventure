package com.composition.proxy;

import com.composition.model.Operation;
import com.composition.utils.HttpUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;

import static com.composition.Deployer.OPERATIONS;

/**
 * @author alexg
 * A HTTP Servlet meant to route requests
 */
public class ProxyServlet extends HttpServlet {

    @Override
    public void doGet (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        throw new RuntimeException("Not yet implemented!");
    }

    @Override
    public void doPost (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        for(int i = 0; i < OPERATIONS.size(); i++){
            if(req.getRequestURL().toString().contains(OPERATIONS.get(i).getLocation())
                    && (OPERATIONS.get(i).getHttpMethod().equals("POST") ||
                        OPERATIONS.get(i).getHttpMethod().equals("PUT") ||
                        OPERATIONS.get(i).getHttpMethod().equals("DELETE"))){

                final Operation operation = OPERATIONS.get(i);
                final PrintWriter pw = resp.getWriter();
                final String response;
                String requestBody = HttpUtils.getBody(req);
                requestBody = URLDecoder.decode(requestBody, "UTF-8");
                requestBody = requestBody.replace("requestString=", "");
                final URL url = new URL(operation.getUrl());
                final HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod(operation.getHttpMethod());
                connection.setRequestProperty("Accept", operation.getResponseContentType());
                // TODO ? why did i need this one on POST request ??
                connection.setRequestProperty("Content-Type", operation.getRequestContentType());
                // write to the body
                connection.setDoOutput(true);
                connection.getOutputStream().write(requestBody.getBytes());
                final BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                final StringBuilder output = new StringBuilder();
                String out;
                resp.setContentType("text/xml");
                pw.append("<response><![CDATA[");
                while((out = br.readLine()) != null){
                    pw.append(out);
                    output.append(out);
                }
                pw.append("]]></response>");
                connection.disconnect();
                response = output.toString();

                System.out.println("URL: " + url);
                System.out.println("REQUEST: " + requestBody);
                System.out.println("RESPONSE: " + response);
                return;
            }
        }
    }
}
