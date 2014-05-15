package com.addition;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

/**
 * Created by alexg on 10.05.2014.
 */
@WebService(portName = "AdditionSOAP", serviceName = "Addition", targetNamespace = "http://www.wscomposition.org/Addition/")
@SOAPBinding(style = SOAPBinding.Style.RPC)
public final class AdditionServiceImpl {
    @WebMethod(operationName = "AddRequest")
    @WebResult
    public AdditionResponse add(@WebParam(name = "firstParam", partName = "firstParam") Double a,
                                @WebParam(name = "secondParam", partName = "secondParam") Double b) {
        final long start = System.nanoTime();
        final Double result = a + b;
        final String elapsedTime = System.nanoTime() - start + "";
        final AdditionResponse response = new AdditionResponse();
        response.setElapsedTime(elapsedTime);
        response.setOperationId(String.valueOf(start));
        response.setResult(result);
        return response;
    }

    @WebMethod(operationName = "SubstractRequest")
    @WebResult
    public AdditionResponse substract(@WebParam(name = "firstParam", partName = "firstParam") Double a,
                                      @WebParam(name = "secondParam", partName = "secondParam") Double b){
        final long start = System.nanoTime();
        final Double result = a - b;
        final String elapsedTime = System.nanoTime() - start + "";
        final AdditionResponse response = new AdditionResponse();
        response.setElapsedTime(elapsedTime);
        response.setOperationId(String.valueOf(start));
        response.setResult(result);
        return response;

    }
}
