package com.progression;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.IOException;

/**
 * Created by alexg on 01.06.2014.
 */
@Path("/ArithmeticProgression")
public class ArithmeticProgressionService {

    @Path("/compute")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public ArithmeticProgressionResponse compute(final String json) throws IOException {
        final ArithmeticProgressionRequest request = new ObjectMapper().readValue(json, ArithmeticProgressionRequest.class);
        final long start = System.nanoTime();
        final Integer result = (request.getN() / 2) * (2 * request.getA() + (request.getN() - 1) * request.getD());
        final String elapsedTime = System.nanoTime() - start + "";
        final ArithmeticProgressionResponse response = new ArithmeticProgressionResponse();
        response.setElapsedTime(elapsedTime);
        response.setOperationId(String.valueOf(start));
        response.setResult(Double.valueOf(result));
        return response;
    }

    @GET
    @Produces(MediaType.TEXT_HTML)
    public String info(){
        return "Arithmetic Progression Service";
    }
}
