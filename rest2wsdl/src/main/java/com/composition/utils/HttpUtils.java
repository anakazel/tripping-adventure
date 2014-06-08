package com.composition.utils;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.impl.client.DefaultHttpClient;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Singleton containing utility methods focused on HTTP processing
 * @author alexg
 */
public enum HttpUtils {

    ;// singleton usage

    public static final String getHttpBody(final HttpServletRequest request) throws IOException {

        final String body;
        final StringBuilder stringBuilder = new StringBuilder();
        BufferedReader bufferedReader = null;

        try {
            final InputStream inputStream = request.getInputStream();
            if (inputStream != null) {
                bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
                final char[] charBuffer = new char[128];
                int bytesRead;
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

    public static final String getHttpResponse(final BufferedReader br) throws IOException {
        final StringBuilder output = new StringBuilder();
        String out;
        while((out = br.readLine()) != null){
            output.append(out);
        }
        return output.toString();
    }
    /**
     * Not being able to do a DELETE request using Sun HTTP Client (source code not available to debug..), using Apache instead
     * @return
     */
    public static final String doHttpDelete(final String url, final String responseContentType) throws IOException {
        final DefaultHttpClient httpClient = new DefaultHttpClient();
        final HttpDelete deleteRequest = new HttpDelete(url);
        deleteRequest.addHeader("accept", responseContentType);
        final HttpResponse response = httpClient.execute(deleteRequest);
        final BufferedReader br = new BufferedReader(
                new InputStreamReader((response.getEntity().getContent())));

        final StringBuilder out = new StringBuilder();
        String output;
        while ((output = br.readLine()) != null) {
            out.append(output);
        }
        httpClient.getConnectionManager().shutdown();
        return out.toString();
    }
}
