package com.multiplication;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.IOException;

/**
 * Created by alexg on 01.06.2014.
 */
@Path("Multiplication")
public class MultiplicationService {

    @PUT
    @Path("/multiply")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public MultiplicationResponse multiply(final String json) throws IOException {
        final MultiplicationRequest request = new ObjectMapper().readValue(json, MultiplicationRequest.class);
        final long start = System.nanoTime();
        final Double result = request.getFirstParam() * request.getSecondParam();
        final String elapsedTime = System.nanoTime() - start + "";
        final MultiplicationResponse response = new MultiplicationResponse();
        response.setElapsedTime(elapsedTime);
        response.setOperationId(String.valueOf(start));
        response.setResult(result);
        return response;
    }

    @PUT
    @Path("/divide")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public MultiplicationResponse divide(final String json) throws IOException {
        final MultiplicationRequest request = new ObjectMapper().readValue(json, MultiplicationRequest.class);
        final long start = System.nanoTime();
        final Double result = request.getFirstParam() / request.getSecondParam();
        final String elapsedTime = System.nanoTime() - start + "";
        final MultiplicationResponse response = new MultiplicationResponse();
        response.setElapsedTime(elapsedTime);
        response.setOperationId(String.valueOf(start));
        response.setResult(result);
        return response;
    }

    @GET
    @Produces(MediaType.TEXT_HTML)
    public String info(){
        return "Multiplication Service";
    }
}
