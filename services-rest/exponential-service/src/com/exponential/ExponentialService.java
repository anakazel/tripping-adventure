package com.exponential;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.IOException;

/**
 * Created by alexg on 01.06.2014.
 */
@Path("/Exponential")
public class ExponentialService {

    @PUT
    @Path("/pow")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ExponentialResponse pow(final String json) throws IOException {
        final PowRequest request = new ObjectMapper().readValue(json, PowRequest.class);
        final long start = System.nanoTime();
        final Double result = Math.pow(request.getFirstParam(), request.getSecondParam());
        final String elapsedTime = System.nanoTime() - start + "";
        final ExponentialResponse response = new ExponentialResponse();
        response.setElapsedTime(elapsedTime);
        response.setOperationId(String.valueOf(start));
        response.setResult(result);
        return response;
    }

    @PUT
    @Path("/log")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ExponentialResponse log(final String json) throws IOException {
        final LogRequest request = new ObjectMapper().readValue(json, LogRequest.class);
        final long start = System.nanoTime();
        final Double result = Math.log(request.getParam());
        final String elapsedTime = System.nanoTime() - start + "";
        final ExponentialResponse response = new ExponentialResponse();
        response.setElapsedTime(elapsedTime);
        response.setOperationId(String.valueOf(start));
        response.setResult(result);
        return response;
    }

    @GET
    @Produces(MediaType.TEXT_HTML)
    public String info(){
        return "Exponential Service";
    }
}
