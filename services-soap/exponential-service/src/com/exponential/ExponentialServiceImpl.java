package com.exponential;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

/**
 * Created by alexg on 10.05.2014.
 */
@WebService(portName = "ExponentialSOAP", serviceName = "Exponential", targetNamespace = "http://www.wscomposition.org/Exponential/")
@SOAPBinding(style = SOAPBinding.Style.RPC)
public final class ExponentialServiceImpl {
    @WebMethod(operationName = "LogRequest")
    @WebResult
    public ExponentialResponse log(@WebParam(name = "param", partName = "param") Double param){
        final long start = System.nanoTime();
        final Double result = Math.log(param);
        final String elapsedTime = System.nanoTime() - start + "";
        final ExponentialResponse response = new ExponentialResponse();
        response.setElapsedTime(elapsedTime);
        response.setOperationId(String.valueOf(start));
        response.setResult(result);
        return response;
    }

    @WebMethod(operationName = "PowRequest")
    @WebResult
    public ExponentialResponse pow(@WebParam(name = "firstParam", partName = "firstParam") Double firstParam, @WebParam(name = "secondParam", partName = "secondParam") Double secondParam){
        final long start = System.nanoTime();
        final Double result = Math.pow(firstParam, secondParam);
        final String elapsedTime = System.nanoTime() - start + "";
        final ExponentialResponse response = new ExponentialResponse();
        response.setElapsedTime(elapsedTime);
        response.setOperationId(String.valueOf(start));
        response.setResult(result);
        return response;
    }
}
