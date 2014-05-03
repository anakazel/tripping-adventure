package soapwrapper;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

/**
 * Created by AlexG on 4/26/14.
 */
@WebService(name = "Forecast", targetNamespace = "http://www.wscomposition.org/Forecast/")
@SOAPBinding(style = SOAPBinding.Style.RPC)
public interface Forecast {
    @WebMethod(operationName = "ForecastRequest")
    @WebResult(partName = "ForecastResponse")
    String getDirections(@WebParam(name = "q", partName = "q", targetNamespace = "http://www.wscomposition.org/") String query,
                         @WebParam(name = "cnt", partName = "cnt", targetNamespace = "http://www.wscomposition.org/") Integer cnt,
                         @WebParam(name = "units", partName = "units", targetNamespace = "http://www.wscomposition.org/") String units);
}
