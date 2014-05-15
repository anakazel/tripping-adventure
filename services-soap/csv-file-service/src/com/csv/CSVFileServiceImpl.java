package com.csv;

import org.omg.CORBA.Environment;
import sun.misc.IOUtils;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.*;

/**
 * Created by alexg on 12.05.2014.
 */
@WebService(portName = "CSVFileSOAP", serviceName = "CSVFile", targetNamespace = "http://www.wscomposition.org/CSVFile/")
@SOAPBinding(style = SOAPBinding.Style.RPC)
public final class CSVFileServiceImpl {

    static final String LINE_SEPARATOR = System.getProperty("line.separator");

    @WebMethod(operationName = "AddEntryRequest")
    @WebResult
    public CSVFileResponse addEntry(@WebParam(name = "key", partName = "key") String key,
                                    @WebParam(name = "value", partName = "value") String value,
                                    @WebParam(name = "path", partName = "path") String path) throws IOException {
        final long start = System.nanoTime();
        final String elapsedTime = System.nanoTime() - start + "";
        final CSVFileResponse response = new CSVFileResponse();
        Files.write(Paths.get(path), (key + "," + value + LINE_SEPARATOR).getBytes(), StandardOpenOption.APPEND);
        response.setElapsedTime(elapsedTime);
        response.setOperationId(String.valueOf(start));
        response.setResult("OK");
        return response;
    }

    @WebMethod(operationName = "CleanupRequest")
    @WebResult
    public CSVFileResponse cleanup(@WebParam(name = "path", partName = "path") String path) throws IOException {
        final long start = System.nanoTime();
        final String elapsedTime = System.nanoTime() - start + "";
        final CSVFileResponse response = new CSVFileResponse();
        final PrintWriter writer = new PrintWriter(new File(path));
        writer.write("");
        writer.close();
        response.setElapsedTime(elapsedTime);
        response.setOperationId(String.valueOf(start));
        response.setResult("OK");
        return response;
    }
}
