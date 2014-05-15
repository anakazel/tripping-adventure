package com.trigonometrical;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

/**
 * Created by alexg on 10.05.2014.
 */
@WebService(portName = "TrigonometricalSOAP", serviceName = "Trigonometrical", targetNamespace = "http://wwww.wscomposition.org/Trigonometrical/")
@SOAPBinding(style = SOAPBinding.Style.RPC)
public final class TrigonometricalServiceImpl {
    @WebMethod(operationName = "SinRequest")
    @WebResult
    public TrigonometricalResponse sin(@WebParam(name = "angle", partName = "angle") Integer angle){
        final long start = System.nanoTime();
        final Double result = Math.sin(Math.toRadians(angle));
        final String elapsedTime = System.nanoTime() - start + "";
        final TrigonometricalResponse response = new TrigonometricalResponse();
        response.setElapsedTime(elapsedTime);
        response.setOperationId(String.valueOf(start));
        response.setResult(result);
        return response;
    }

    @WebMethod(operationName = "CosRequest")
    @WebResult
    public TrigonometricalResponse cos(@WebParam(name = "angle", partName = "angle") Integer angle){
        final long start = System.nanoTime();
        final Double result = Math.cos(Math.toRadians(angle));
        final String elapsedTime = System.nanoTime() - start + "";
        final TrigonometricalResponse response = new TrigonometricalResponse();
        response.setElapsedTime(elapsedTime);
        response.setOperationId(String.valueOf(start));
        response.setResult(result);
        return response;
    }
}
