package com.progression;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.IOException;

/**
 * Created by alexg on 01.06.2014.
 */
@Path("GeometricProgression")
public class GeometricProgressionService {

    @Path("/compute")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public GeometricProgressionResponse compute(final String json) throws IOException {
        final GeometricProgressionRequest request = new ObjectMapper().readValue(json, GeometricProgressionRequest.class);
        final long start = System.nanoTime();
        final Double result = request.getA() * (1 - Math.pow(request.getR(), request.getN() + 1)) / (1 - request.getR());
        final String elapsedTime = System.nanoTime() - start + "";
        final GeometricProgressionResponse response = new GeometricProgressionResponse();
        response.setElapsedTime(elapsedTime);
        response.setOperationId(String.valueOf(start));
        response.setResult(result);
        return response;
    }

    @GET
    @Produces(MediaType.TEXT_HTML)
    public String info(){
        return "Geometric Progression Service";
    }
}
