package soapwrapper;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

/**
 * Created by AlexG on 4/26/14.
 */
@WebService(name = "Directions", targetNamespace = "http://wscomposition.org/")
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface Directions {
    @WebMethod(operationName = "GetDirections")
    String getDirections(@WebParam(name = "origin", partName = "origin", targetNamespace = "http://wscomposition.org/") String origin,
                         @WebParam(name = "destination", partName = "destination", targetNamespace = "http://wscomposition.org/") String destination);
}
