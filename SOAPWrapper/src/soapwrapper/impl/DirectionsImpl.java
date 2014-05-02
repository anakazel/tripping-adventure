package soapwrapper.impl;

import soapwrapper.Directions;

import javax.jws.HandlerChain;
import javax.jws.WebService;

/**
 * Created by AlexG on 4/26/14.
 */
@WebService(portName = "DirectionsSOAP", serviceName = "Directions",
        endpointInterface = "soapwrapper.Directions", targetNamespace = "http://www.wscomposition.org/Directions/")
@HandlerChain(file = "/config/directionsHandler.xml")
public final class DirectionsImpl implements Directions {
    @Override
    public String getDirections(String origin, String destination, String mode) {
        return "";
    }
}
