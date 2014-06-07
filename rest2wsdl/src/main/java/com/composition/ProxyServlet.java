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
public class ProxyServlet extends HttpServlet {

    //todo <asd>rest2wsdl_response</asd>
    @Override
    public void doGet (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        throw new RuntimeException("Not yet implemented!");
    }

    @Override
    public void doPost (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //http://localhost:9090/rest2wsdl/Multiplication/multiply
        if(req.getRequestURL().toString().equals("http://localhost:9090/rest2wsdl/Multiplication/multiply")){
            PrintWriter pw = resp.getWriter();
            String response;
            String requestBody = getBody(req);
            System.out.println(requestBody);
            requestBody = URLDecoder.decode(requestBody, "UTF-8");
            requestBody = requestBody.replace("requestString=", "");
            final URL url = new URL("http://localhost:9005/Multiplication/multiply");
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
            resp.setContentType("text/xml");
            pw.append("<response><![CDATA[");
            while((out = br.readLine()) != null){
                pw.append(out);
                output.append(out);
            }
            pw.append("]]></response>");
            connection.disconnect();
            response = output.toString();
            System.out.println("RESPONSE: " + response);
            //TODO use json string in CDATA?
            pw.close();
        }else if(req.getRequestURL().toString().equals("http://localhost:9090/rest2wsdl/Addition/add")){
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
            resp.setContentType("text/xml");
            pw.append("<response><![CDATA[");
            while((out = br.readLine()) != null){
                pw.append(out);
                output.append(out);
            }
            pw.append("]]></response>");
            connection.disconnect();
            response = output.toString();
            System.out.println("RESPONSE: " + response);
            //TODO use json string in CDATA?
            pw.close();
        }else if(req.getRequestURL().toString().equals("http://localhost:9090/rest2wsdl/Addition/substract")){
            PrintWriter pw = resp.getWriter();
            String response;
            String requestBody = getBody(req);
            System.out.println(requestBody);
            requestBody = URLDecoder.decode(requestBody, "UTF-8");
            requestBody = requestBody.replace("requestString=", "");
            final URL url = new URL("http://localhost:9000/Addition/substract");
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
            resp.setContentType("text/xml");
            pw.append("<response><![CDATA[");
            while((out = br.readLine()) != null){
                pw.append(out);
                output.append(out);
            }
            pw.append("]]></response>");
            connection.disconnect();
            response = output.toString();
            System.out.println("RESPONSE: " + response);
            //TODO use json string in CDATA?
            pw.close();
        }else if(req.getRequestURL().toString().equals("http://localhost:9090/rest2wsdl/Exponential/pow")){
            PrintWriter pw = resp.getWriter();
            String response;
            String requestBody = getBody(req);
            System.out.println(requestBody);
            requestBody = URLDecoder.decode(requestBody, "UTF-8");
            requestBody = requestBody.replace("requestString=", "");
            final URL url = new URL("http://localhost:9003/Exponential/pow");
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
            resp.setContentType("text/xml");
            pw.append("<response><![CDATA[");
            while((out = br.readLine()) != null){
                pw.append(out);
                output.append(out);
            }
            pw.append("]]></response>");
            connection.disconnect();
            response = output.toString();
            System.out.println("RESPONSE: " + response);
            //TODO use json string in CDATA?
        }else if(req.getRequestURL().toString().equals("http://localhost:9090/rest2wsdl/ArithmeticProgression/compute")){
            PrintWriter pw = resp.getWriter();
            String response;
            String requestBody = getBody(req);
            System.out.println(requestBody);
            requestBody = URLDecoder.decode(requestBody, "UTF-8");
            requestBody = requestBody.replace("requestString=", "");
            final URL url = new URL("http://localhost:9001/ArithmeticProgression/compute");
            final HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Accept", "application/json");
            // TODO ? why did i need this one on POST request ??
            connection.setRequestProperty("Content-Type", "application/json");
            // write to the body
            connection.setDoOutput(true);

            connection.getOutputStream().write(requestBody.getBytes());

            final BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            System.out.println("URL: " + url);
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
            System.out.println("RESPONSE: " + response);
            //TODO use json string in CDATA?
        }else if(req.getRequestURL().toString().equals("http://localhost:9090/rest2wsdl/GeometricProgression/compute")) {
            PrintWriter pw = resp.getWriter();
            String response;
            String requestBody = getBody(req);
            System.out.println(requestBody);
            requestBody = URLDecoder.decode(requestBody, "UTF-8");
            requestBody = requestBody.replace("requestString=", "");
            final URL url = new URL("http://localhost:9004/GeometricProgression/compute");
            final HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Accept", "application/json");
            // TODO ? why did i need this one on POST request ??
            connection.setRequestProperty("Content-Type", "application/json");
            // write to the body
            connection.setDoOutput(true);

            connection.getOutputStream().write(requestBody.getBytes());

            final BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            System.out.println("URL: " + url);
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
            System.out.println("RESPONSE: " + response);
            //TODO use json string in CDATA?
        }else if(req.getRequestURL().toString().equals("http://localhost:9090/rest2wsdl/Trigonometrical/sin")) {
            PrintWriter pw = resp.getWriter();
            String response;
            String requestBody = getBody(req);
            System.out.println(requestBody);
            requestBody = URLDecoder.decode(requestBody, "UTF-8");
            requestBody = requestBody.replace("requestString=", "");
            final URL url = new URL("http://localhost:9006/Trigonometrical/sin");
            final HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("PUT");
            connection.setRequestProperty("Accept", "application/json");
            // TODO ? why did i need this one on POST request ??
            connection.setRequestProperty("Content-Type", "application/json");
            // write to the body
            connection.setDoOutput(true);

            connection.getOutputStream().write(requestBody.getBytes());

            final BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            System.out.println("URL: " + url);
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
            System.out.println("RESPONSE: " + response);
            //TODO use json string in CDATA?
        }else if(req.getRequestURL().toString().equals("http://localhost:9090/rest2wsdl/Trigonometrical/cos")) {
            PrintWriter pw = resp.getWriter();
            String response;
            String requestBody = getBody(req);
            System.out.println(requestBody);
            requestBody = URLDecoder.decode(requestBody, "UTF-8");
            requestBody = requestBody.replace("requestString=", "");
            final URL url = new URL("http://localhost:9006/Trigonometrical/cos");
            final HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("PUT");
            connection.setRequestProperty("Accept", "application/json");
            // TODO ? why did i need this one on POST request ??
            connection.setRequestProperty("Content-Type", "application/json");
            // write to the body
            connection.setDoOutput(true);

            connection.getOutputStream().write(requestBody.getBytes());

            final BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            System.out.println("URL: " + url);
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
            System.out.println("RESPONSE: " + response);
            //TODO use json string in CDATA?
        }else if(req.getRequestURL().toString().equals("http://localhost:9090/rest2wsdl/Multiplication/divide")){
            PrintWriter pw = resp.getWriter();
            String response;
            String requestBody = getBody(req);
            System.out.println(requestBody);
            requestBody = URLDecoder.decode(requestBody, "UTF-8");
            requestBody = requestBody.replace("requestString=", "");
            final URL url = new URL("http://localhost:9005/Multiplication/divide");
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
            resp.setContentType("text/xml");
            pw.append("<response><![CDATA[");
            while((out = br.readLine()) != null){
                pw.append(out);
                output.append(out);
            }
            pw.append("]]></response>");
            connection.disconnect();
            response = output.toString();
            System.out.println("RESPONSE: " + response);
            //TODO use json string in CDATA?
            pw.close();
        }else if(req.getRequestURL().toString().equals("http://localhost:9090/rest2wsdl/Exponential/log")){
            PrintWriter pw = resp.getWriter();
            String response;
            String requestBody = getBody(req);
            System.out.println(requestBody);
            requestBody = URLDecoder.decode(requestBody, "UTF-8");
            requestBody = requestBody.replace("requestString=", "");
            final URL url = new URL("http://localhost:9003/Exponential/log");
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
            resp.setContentType("text/xml");
            pw.append("<response><![CDATA[");
            while((out = br.readLine()) != null){
                pw.append(out);
                output.append(out);
            }
            pw.append("]]></response>");
            connection.disconnect();
            response = output.toString();
            System.out.println("RESPONSE: " + response);
            //TODO use json string in CDATA?
            pw.close();
        }

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
