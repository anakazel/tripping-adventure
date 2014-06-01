package com.csv;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * Created by alexg on 01.06.2014.
 */
@Path("CSVFile")
public class CSVFileService {

    private static final String LINE_SEPARATOR = System.getProperty("line.separator");

    @PUT
    @Path("/addEntry")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public CSVFileResponse addEntry(final String json) throws IOException {
        final CSVFileRequest request = new ObjectMapper().readValue(json, CSVFileRequest.class);
        final CSVFileResponse response = new CSVFileResponse();
        final long start = System.nanoTime();
        final String elapsedTime = System.nanoTime() - start + "";
        Files.write(Paths.get(request.getPath()), (request.getKey() + "," + request.getValue() + LINE_SEPARATOR).getBytes(), StandardOpenOption.APPEND);
        response.setElapsedTime(elapsedTime);
        response.setOperationId(String.valueOf(start));
        response.setResult("OK");
        return response;
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public CSVFileResponse cleanup(@QueryParam("path") String path) throws IOException{
        final String newPath = path.replace("|", "/");
        final long start = System.nanoTime();
        final CSVFileResponse response = new CSVFileResponse();
        final PrintWriter writer = new PrintWriter(new File(newPath));
        writer.write("");
        writer.close();
        final String elapsedTime = System.nanoTime() - start + "";
        response.setElapsedTime(elapsedTime);
        response.setOperationId(String.valueOf(start));
        response.setResult("OK");
        return response;
    }

    @GET
    @Produces(MediaType.TEXT_HTML)
    public String info(){
        return "CSVFile Service";
    }
}
