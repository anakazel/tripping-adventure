package soapwrapper.impl;

import soapwrapper.Directions;

import javax.jws.HandlerChain;
import javax.jws.WebService;

/**
 * Created by AlexG on 4/26/14.
 */
@WebService(portName = "DirectionsPort", serviceName = "Directions",
        endpointInterface = "soapwrapper.Directions", targetNamespace = "http://wscomposition.org/")
@HandlerChain(file = "directionsHandler.xml")
public class DirectionsImpl implements Directions {
    @Override
    public String getDirections(String origin, String destination) {
        return "";
    }
}
