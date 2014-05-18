package com.progression;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

/**
 * Created by alexg on 10.05.2014.
 */
@WebService(portName = "GeometricProgressionSOAP", serviceName = "GeometricProgression", targetNamespace = "http://www.wscomposition.org/GeometricProgression/")
@SOAPBinding(style = SOAPBinding.Style.RPC)
public final class GeometricProgressionServiceImpl {
    @WebMethod(operationName = "GeometricProgressionRequest")
    @WebResult
    public GeometricProgressionResponse compute(@WebParam(name = "n", partName = "n") Integer n,
                                                @WebParam(name = "r", partName = "r") Integer r,
                                                @WebParam(name = "a", partName = "a") Integer a){
        final long start = System.nanoTime();
        final Double result = a * (1 - Math.pow(r, n + 1)) / (1 - r);
        final String elapsedTime = System.nanoTime() - start + "";
        final GeometricProgressionResponse response = new GeometricProgressionResponse();
        response.setElapsedTime(elapsedTime);
        response.setOperationId(String.valueOf(start));
        response.setResult(result);
        return response;
    }
}
