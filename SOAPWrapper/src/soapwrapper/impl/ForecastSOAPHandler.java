package soapwrapper.impl;

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

import static soapwrapper.Publisher.FORECAST_MODE;
import static soapwrapper.Publisher.FORECAST_URL;
import static soapwrapper.util.SOAPUtils.transformSOAPBody;
import static soapwrapper.util.SOAPUtils.xmlStringToSOAPElement;

/**
 * Created by AlexG on 4/27/14.
 */
public class ForecastSOAPHandler extends AbstractSOAPHandler implements SOAPHandler<SOAPMessageContext> {

    private static final RestHttpClient REST_HTTP_CLIENT = new RestHttpClient(FORECAST_URL);

    private String q;
    private String mode;
    private Integer cnt;
    private String units;

    protected final SOAPElement getSOAPElement() throws IOException, SAXException, ParserConfigurationException {
        final Map<String, String> params = new HashMap<>();
        params.put("q", q);
        params.put("mode", FORECAST_MODE);
        params.put("cnt", String.valueOf(cnt));
        params.put("units", units);
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
                q = soapBody.getElementsByTagName("q").item(0).getTextContent();
                cnt = Integer.valueOf(soapBody.getElementsByTagName("cnt").item(0).getTextContent());
                units = soapBody.getElementsByTagName("units").item(0).getTextContent();
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
