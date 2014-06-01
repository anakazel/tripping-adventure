package com.addition;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.IOException;

/**
 * Created by alexg on 31.05.2014.
 */
@Path("/Addition")
public class AdditionService {

    @Path("/add")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public AdditionResponse add(final String json) throws IOException {
        final AdditionRequest request = new ObjectMapper().readValue(json, AdditionRequest.class);
        final long start = System.nanoTime();
        final Double result = request.getFirstParam() + request.getSecondParam();
        final String elapsedTime = System.nanoTime() - start + "";
        final AdditionResponse response = new AdditionResponse();
        response.setElapsedTime(elapsedTime);
        response.setOperationId(String.valueOf(start));
        response.setResult(result);
        return response;
    }

    @Path("/substract")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public AdditionResponse substract(final String json) throws IOException{
        final AdditionRequest request = new ObjectMapper().readValue(json, AdditionRequest.class);
        final long start = System.nanoTime();
        final Double result = request.getFirstParam() - request.getSecondParam();
        final String elapsedTime = System.nanoTime() - start + "";
        final AdditionResponse response = new AdditionResponse();
        response.setElapsedTime(elapsedTime);
        response.setOperationId(String.valueOf(start));
        response.setResult(result);
        return response;
    }

    @GET
    @Produces(MediaType.TEXT_HTML)
    public String info(){
        return "Addition Service";
    }
}
