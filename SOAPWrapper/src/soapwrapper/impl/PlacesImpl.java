package soapwrapper.impl;

import soapwrapper.Places;

import javax.jws.HandlerChain;
import javax.jws.WebParam;
import javax.jws.WebService;

/**
 * Created by AlexG on 4/27/14.
 */
@WebService(portName = "PlacesPort", serviceName = "Places", endpointInterface = "soapwrapper.Places", targetNamespace = "http://wscomposition.org/")
@HandlerChain(file = "placesHandler.xml")
public class PlacesImpl implements Places {
    @Override
    public String getPlaces(String location, String radius, String types) {
        return "";
    }
}
