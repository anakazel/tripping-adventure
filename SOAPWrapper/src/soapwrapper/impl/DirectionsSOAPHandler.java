package soapwrapper.impl;

import soapwrapper.util.AbstractSOAPHandler;
import soapwrapper.util.RestHttpClient;
import org.xml.sax.SAXException;

import javax.xml.namespace.QName;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.soap.*;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import static soapwrapper.Publisher.DIRECTIONS_SENSOR;
import static soapwrapper.Publisher.DIRECTIONS_URL;
import static soapwrapper.util.SOAPUtils.transformSOAPBody;
import static soapwrapper.util.SOAPUtils.xmlStringToSOAPElement;

/**
 * Created by AlexG on 4/26/14.
 */
public class DirectionsSOAPHandler extends AbstractSOAPHandler implements SOAPHandler<SOAPMessageContext> {

    private static final RestHttpClient REST_HTTP_CLIENT = new RestHttpClient(DIRECTIONS_URL);

    private String origin;
    private String destination;

    protected final SOAPElement getSOAPElement() throws IOException, SAXException, ParserConfigurationException {
        final Map<String, String> params = new HashMap<>();
        params.put("origin", origin);
        params.put("destination", destination);
        params.put("sensor", DIRECTIONS_SENSOR);
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
                origin = soapBody.getElementsByTagName("origin").item(0).getTextContent();
                destination = soapBody.getElementsByTagName("destination").item(0).getTextContent();
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
