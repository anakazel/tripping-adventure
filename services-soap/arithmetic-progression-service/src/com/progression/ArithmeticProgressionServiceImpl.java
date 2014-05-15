package com.progression;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

/**
 * Created by alexg on 10.05.2014.
 */
@WebService(portName = "ArithmeticProgressionSOAP", serviceName = "ArithmeticProgression", targetNamespace = "http://www.wscomposition.org/ArithmeticProgression/")
@SOAPBinding(style = SOAPBinding.Style.RPC)
public final class ArithmeticProgressionServiceImpl {
    @WebMethod(operationName = "ArithmeticProgressionRequest")
    @WebResult
    public ArithmeticProgressionResponse compute(@WebParam(name = "n", partName = "n") Integer n,
                                                 @WebParam(name = "a", partName =  "a") Integer a,
                                                 @WebParam(name = "d", partName = "d") Integer d){
        final long start = System.nanoTime();
        final Integer result = (n / 2) * (2 * a + (n - 1) * d);
        final String elapsedTime = System.nanoTime() - start + "";
        final ArithmeticProgressionResponse response = new ArithmeticProgressionResponse();
        response.setElapsedTime(elapsedTime);
        response.setOperationId(String.valueOf(start));
        response.setResult(Double.valueOf(result));
        return response;
    }
}
