package soapwrapper;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

/**
 * Created by AlexG on 4/26/14.
 */
@WebService(name = "Search", targetNamespace = "http://www.wscomposition.org/Search/")
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface Search {
    @WebMethod(operationName = "SearchRequest")
    @WebResult(partName = "SearchResponse")
    String getImages(@WebParam (name = "q", partName = "q", targetNamespace = "http://www.wscomposition.org/") String q,
                     @WebParam (name = "num", partName = "num", targetNamespace = "http://www.wscomposition.org/") Integer num);
}
