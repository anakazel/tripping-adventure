package soapwrapper.impl;

import soapwrapper.Forecast;

import javax.jws.HandlerChain;
import javax.jws.WebService;

/**
 * Created by AlexG on 4/27/14.
 */
@WebService(portName = "ForecastPort", serviceName = "Forecast",
        endpointInterface = "soapwrapper.Forecast", targetNamespace = "http://wscomposition.org/")
@HandlerChain(file = "forecastHandler.xml")
public class ForecastImpl implements Forecast{
    @Override
    public String getDirections(String query, Integer cnt, String units) {
        return "";
    }
}
