package soapwrapper.util;

import com.sun.xml.internal.messaging.saaj.soap.ver1_1.BodyElement1_1Impl;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.soap.*;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

/**
 * Created by AlexG on 4/27/14.
 */
public enum SOAPUtils {
    ;
    private static MessageFactory messageFactory;
    private static DocumentBuilderFactory docFactory;
    private static DocumentBuilder docBuilder;

    // factory objects
    static{
        try {
            messageFactory = MessageFactory.newInstance();
            docFactory = DocumentBuilderFactory.newInstance();
            docBuilder = docFactory.newDocumentBuilder();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SOAPException e) {
            e.printStackTrace();
        }
    }

    /**
     * Transform XML String to a SOAPElement
     * @param xmlText
     * @return
     */
    public static final SOAPElement xmlStringToSOAPElement(final String xmlText) {
        try {
            // Load the XML text into a DOM Document
            docFactory.setNamespaceAware(true);
            final InputStream stream  = new ByteArrayInputStream(xmlText.getBytes());
            final Document doc = docFactory.newDocumentBuilder().parse(stream);

            // Use SAAJ to convert Document to SOAPElement
            // Create SoapMessage
            final SOAPMessage message    = messageFactory.createMessage();
            final SOAPBody soapBody   = message.getSOAPBody();

            // This returns the SOAPBodyElement
            // that contains ONLY the Payload
            return soapBody.addDocument(doc);

        } catch (SOAPException e) {
            System.out.println("SOAPException : " + e);
            return null;

        } catch (IOException e) {
            System.out.println("IOException : " + e);
            return null;

        } catch (ParserConfigurationException e) {
            System.out.println("ParserConfigurationException : " + e);
            return null;

        } catch (SAXException e) {
            System.out.println("SAXException : " + e);
            return null;

        }
    }

    /**
     * Remove the default return element and add a SOAPElement in the SOAPBody
     * @param soapBody
     */
    public static final void transformSOAPBody(final SOAPBody soapBody, final SOAPElement soapElement) throws SOAPException {
        // remove return element
        final Iterator<Node> it = soapBody.getChildElements();
        final Node node = it.next();
        final org.w3c.dom.Node returnNode = node.getChildNodes().item(0);
        node.removeChild(returnNode);

        // add the XML payload as an element
        final BodyElement1_1Impl response = (BodyElement1_1Impl) soapBody.getChildElements().next();
        response.addChildElement(soapElement);
    }
}
