package com.multiplication;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

/**
 * Created by alexg on 10.05.2014.
 */
@WebService(portName = "MultiplicationSOAP", serviceName = "Multiplication", targetNamespace = "http://www.wscomposition.org/Multiplication/")
@SOAPBinding(style = SOAPBinding.Style.RPC)
public final class MultiplicationServiceImpl {
    @WebMethod(operationName = "MultiplyRequest")
    @WebResult
    public MultiplicationResponse multiply(@WebParam(name = "firstParam", partName = "firstParam") Double firstParam,
                                           @WebParam(name = "secondParam", partName = "secondParam") Double secondParam){
        final long start = System.nanoTime();
        final Double result = firstParam * secondParam;
        final String elapsedTime = System.nanoTime() - start + "";
        final MultiplicationResponse response = new MultiplicationResponse();
        response.setElapsedTime(elapsedTime);
        response.setOperationId(String.valueOf(start));
        response.setResult(result);
        return response;
    }

    @WebMethod(operationName = "DivideRequest")
    @WebResult
    public MultiplicationResponse divide(@WebParam(name = "firstParam", partName = "firstParam") Double firstParam,
                                         @WebParam(name = "secondParam", partName = "secondParam") Double secondParam){
        final long start = System.nanoTime();
        final Double result = firstParam / secondParam;
        final String elapsedTime = System.nanoTime() - start + "";
        final MultiplicationResponse response = new MultiplicationResponse();
        response.setElapsedTime(elapsedTime);
        response.setOperationId(String.valueOf(start));
        response.setResult(result);
        return response;
    }
}
