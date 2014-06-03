package com.composition;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;

/**
 * Created by alexg on 03.06.2014.
 */
public class BaseServlet extends HttpServlet {

    @Override
    public void doGet (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    public void doPost (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter pw = resp.getWriter();
        String response;
        String requestBody = getBody(req);
        System.out.println(requestBody);
        requestBody = URLDecoder.decode(requestBody, "UTF-8");
        requestBody = requestBody.replace("requestString=", "");
        final URL url = new URL("http://localhost:9000/Addition/add");
        final HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("PUT");
        connection.setRequestProperty("Accept", "application/json");
        // write to the body
        connection.setDoOutput(true);

        connection.getOutputStream().write(requestBody.getBytes());

        final BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        System.out.println("URL: " + url);
        final StringBuilder output = new StringBuilder();
        String out;
        resp.setContentType("application/json");
        while((out = br.readLine()) != null){
            pw.append(out);
            output.append(out);
        }
        connection.disconnect();
        response = output.toString();
        System.out.println("RESPONSE: " + response);
        pw.close();
    }

    public static String getBody(HttpServletRequest request) throws IOException {

        String body = null;
        StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader = null;

        try {
            InputStream inputStream = request.getInputStream();
            if (inputStream != null) {
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                char[] charBuffer = new char[128];
                int bytesRead = -1;
                while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
                    stringBuilder.append(charBuffer, 0, bytesRead);
                }
            } else {
                stringBuilder.append("");
            }
        } catch (IOException ex) {
            throw ex;
        } finally {
            if (bufferedReader != null) {
                try {
                    bufferedReader.close();
                } catch (IOException ex) {
                    throw ex;
                }
            }
        }

        body = stringBuilder.toString();
        return body;
    }
}
