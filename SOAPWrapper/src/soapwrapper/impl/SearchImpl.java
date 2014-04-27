package soapwrapper.impl;

import soapwrapper.Search;

import javax.jws.HandlerChain;
import javax.jws.WebParam;
import javax.jws.WebService;

/**
 * Created by AlexG on 4/27/14.
 */
@WebService(portName = "SearchPort", serviceName = "Search",
        endpointInterface = "soapwrapper.Search", targetNamespace = "http://wscomposition.org/")
@HandlerChain(file = "searchHandler.xml")
public class SearchImpl implements Search {
    @Override
    public String getImages(String q, Integer num) {
        return "";
    }
}
