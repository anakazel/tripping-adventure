package soapwrapper;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

/**
 * Created by AlexG on 4/26/14.
 */
@WebService(name = "Places", targetNamespace = "http://wscomposition.org/")
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface Places {
    @WebMethod(operationName = "GetPlaces")
    String getPlaces(@WebParam (name = "location", partName = "location", targetNamespace = "http://wscomposition.org/") String location,
                     @WebParam (name = "radius", partName = "radius", targetNamespace = "http://wscomposition.org/") String radius,
                     @WebParam (name = "types", partName = "types", targetNamespace = "http://wscomposition.org/") String types);
}
