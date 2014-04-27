package soapwrapper;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

/**
 * Created by AlexG on 4/26/14.
 */
@WebService(name = "Forecast", targetNamespace = "http://wscomposition.org/")
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface Forecast {
    @WebMethod(operationName = "GetForecast")
    String getDirections(@WebParam(name = "q", partName = "q", targetNamespace = "http://wscomposition.org/") String query,
                         @WebParam(name = "cnt", partName = "cnt", targetNamespace = "http://wscomposition.org/") Integer cnt,
                         @WebParam(name = "units", partName = "units", targetNamespace = "http://wscomposition.org/") String units);
}
