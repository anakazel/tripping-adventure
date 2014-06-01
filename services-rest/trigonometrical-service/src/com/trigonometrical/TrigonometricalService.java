package com.trigonometrical;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.IOException;

/**
 * Created by alexg on 01.06.2014.
 */
@Path("/Trigonometrical")
public class TrigonometricalService {

    @PUT
    @Path("/sin")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public TrigonometricalResponse sin(final String json) throws IOException {
        final TrigonometricalRequest request = new ObjectMapper().readValue(json, TrigonometricalRequest.class);
        final long start = System.nanoTime();
        final Double result = Math.sin(Math.toRadians(request.getAngle()));
        final String elapsedTime = System.nanoTime() - start + "";
        final TrigonometricalResponse response = new TrigonometricalResponse();
        response.setElapsedTime(elapsedTime);
        response.setOperationId(String.valueOf(start));
        response.setResult(result);
        return response;
    }

    @PUT
    @Path("/cos")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public TrigonometricalResponse cos(final String json) throws IOException {
        final TrigonometricalRequest request = new ObjectMapper().readValue(json, TrigonometricalRequest.class);
        final long start = System.nanoTime();
        final Double result = Math.sin(Math.toRadians(request.getAngle()));
        final String elapsedTime = System.nanoTime() - start + "";
        final TrigonometricalResponse response = new TrigonometricalResponse();
        response.setElapsedTime(elapsedTime);
        response.setOperationId(String.valueOf(start));
        response.setResult(result);
        return response;
    }

    @GET
    @Produces(MediaType.TEXT_HTML)
    public String info(){
        return "Trigonometrical Service";
    }
}
