package soapwrapper;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

/**
 * Created by AlexG on 4/26/14.
 */
@WebService(name = "Search", targetNamespace = "http://wscomposition.org/")
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface Search {
    @WebMethod(operationName = "GetImages")
    String getImages(@WebParam (name = "q", partName = "q", targetNamespace = "http://wscomposition.org/") String q,
                     @WebParam (name = "num", partName = "num", targetNamespace = "http://wscomposition.org/") Integer num);
}
