package soapwrapper.impl;

import static soapwrapper.Publisher.PLACES_URL;
import static soapwrapper.Publisher.DIRECTIONS_SENSOR;
import static soapwrapper.Publisher.API_KEY;
import static soapwrapper.util.SOAPUtils.transformSOAPBody;
import static soapwrapper.util.SOAPUtils.xmlStringToSOAPElement;

import org.xml.sax.SAXException;
import soapwrapper.util.AbstractSOAPHandler;
import soapwrapper.util.RestHttpClient;

import javax.xml.namespace.QName;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPException;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by AlexG on 4/27/14.
 */
public class PlacesSOAPHandler extends AbstractSOAPHandler implements SOAPHandler<SOAPMessageContext> {

    private static final RestHttpClient REST_HTTP_CLIENT = new RestHttpClient(PLACES_URL);

    private String location;
    private String radius;
    private String types;

    @Override
    protected SOAPElement getSOAPElement() throws IOException, SAXException, ParserConfigurationException {
        final Map<String, String> params = new HashMap<>();
        params.put("location", location);
        params.put("radius", radius);
        params.put("types", types);
        params.put("sensor", DIRECTIONS_SENSOR);
        params.put("key", API_KEY);
        final String response = REST_HTTP_CLIENT.sendRequest(params);
        return xmlStringToSOAPElement(response);
    }

    @Override
    public Set<QName> getHeaders() {
        return null;
    }

    @Override
    public boolean handleMessage(SOAPMessageContext context) {
        final boolean outbound = (boolean) context.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);
        try {
            final SOAPBody soapBody = context.getMessage().getSOAPBody();
            if (outbound) {
                transformSOAPBody(soapBody, getSOAPElement());
            }else{
                location = soapBody.getElementsByTagName("location").item(0).getTextContent();
                radius = soapBody.getElementsByTagName("radius").item(0).getTextContent();
                types = soapBody.getElementsByTagName("types").item(0).getTextContent();
            }
        } catch (SOAPException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }

        return true;
    }

    @Override
    public boolean handleFault(SOAPMessageContext context) {
        return false;
    }

    @Override
    public void close(MessageContext context) {

    }
}
