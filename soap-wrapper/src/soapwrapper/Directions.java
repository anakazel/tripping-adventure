package soapwrapper;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

/**
 * Created by AlexG on 4/26/14.
 */
@WebService(name = "Directions", targetNamespace = "http://www.wscomposition.org/Directions/")
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface Directions {
    @WebMethod(operationName = "DirectionsRequest")
    @WebResult(name = "DirectionsResponse")
    String getDirections(@WebParam(name = "origin", partName = "origin", targetNamespace = "http://www.wscomposition.org/") String origin,
                         @WebParam(name = "destination", partName = "destination", targetNamespace = "http://www.wscomposition.org/") String destination,
                         @WebParam(name = "mode", partName = "mode", targetNamespace = "http://www.wscomposition.org/") String mode);
}
