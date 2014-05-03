package soapwrapper;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

/**
 * Created by AlexG on 4/26/14.
 */
@WebService(name = "Places", targetNamespace = "http://www.wscomposition.org/Places/")
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface Places {
    @WebMethod(operationName = "PlacesRequest")
    @WebResult(partName = "PlacesResponse")
    String getPlaces(@WebParam (name = "location", partName = "location", targetNamespace = "http://www.wscomposition.org/") String location,
                     @WebParam (name = "radius", partName = "radius", targetNamespace = "http://www.wscomposition.org/") Integer radius,
                     @WebParam (name = "types", partName = "types", targetNamespace = "http://www.wscomposition.org/") String types);
}
