package soapwrapper.impl;

import soapwrapper.Places;

import javax.jws.HandlerChain;
import javax.jws.WebParam;
import javax.jws.WebService;

/**
 * Created by AlexG on 4/27/14.
 */
@WebService(portName = "PlacesSOAP", serviceName = "Places", endpointInterface = "soapwrapper.Places", targetNamespace = "http://www.wscomposition.org/Places/")
@HandlerChain(file = "placesHandler.xml")
public final class PlacesImpl implements Places {
    @Override
    public String getPlaces(String location, Integer radius, String types) {
        return "";
    }
}
