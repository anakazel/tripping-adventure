package soapwrapper.util;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.soap.SOAPElement;
import java.io.IOException;

/**
 * Created by AlexG on 4/27/14.
 */
public abstract class AbstractSOAPHandler {
    protected abstract SOAPElement getSOAPElement() throws IOException, SAXException, ParserConfigurationException;
}
