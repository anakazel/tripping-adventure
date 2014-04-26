import java.io.StringReader;
import java.io.StringWriter;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.mule.api.MuleEventContext;
import org.mule.api.lifecycle.Callable;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;

public final class MuleXmlResponseComponent implements Callable {
	final static TransformerFactory tf = TransformerFactory.newInstance();
	static Transformer transformer;
	int x = 1;
	
	static{
		try {
			transformer = tf.newTransformer();
		} catch (TransformerConfigurationException e) {
			e.printStackTrace();
		}
		transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
	}
	
	@Override
	public Object onCall(MuleEventContext eventContext) throws ParserConfigurationException {
		final StringBuilder muleResponse = new StringBuilder("<?xml version=\"1.0\" encoding=\"UTF-8\"?><MuleResponse>");
		String weatherResponse = "", directionsResponse = "", searchResponse = "", placesResponse = "";
		
		final DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		final DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
		final Document weatherDocument, directionsDocument, searchDocument, placesDocument;
		
		try {
			weatherResponse = eventContext.getMessage().getInvocationProperty("weatherResponse");
			weatherDocument = docBuilder.parse(new InputSource(new StringReader(weatherResponse)));
			muleResponse.append("<Weather>").append(documentToXMLString(weatherDocument)).append("</Weather>");
		} catch (Exception ex) {
			ex.printStackTrace();
			muleResponse.append("<Weather>").append(weatherResponse).append("</Weather>");
		}
		
		try{
			directionsResponse = eventContext.getMessage().getInvocationProperty("directionsResponse");
			directionsDocument = docBuilder.parse(new InputSource(new StringReader(directionsResponse)));	
			muleResponse.append("<Directions>").append(documentToXMLString(directionsDocument)).append("</Directions>");
		}catch(Exception ex){
			ex.printStackTrace();
			muleResponse.append("<Directions>").append(directionsResponse).append("</Directions>");
		}
		
		try{
			searchResponse = eventContext.getMessage().getInvocationProperty("searchResponse");
			searchDocument = docBuilder.parse(new InputSource(new StringReader(searchResponse)));
			muleResponse.append("<Search>").append(documentToXMLString(searchDocument)).append("</Search>");
		}catch(Exception ex){
			ex.printStackTrace();
			muleResponse.append("<Search>").append(searchResponse).append("</Search>");
		}
		
		try{
			placesResponse = eventContext.getMessage().getInvocationProperty("placesResponse");
			placesDocument = docBuilder.parse(new InputSource(new StringReader(placesResponse)));
			muleResponse.append("<Places>").append(documentToXMLString(placesDocument)).append("</Places>");
		}catch(Exception ex){
			ex.printStackTrace();
			muleResponse.append("<Places>").append(placesResponse).append("</Places>");
		}
		
		muleResponse.append("</MuleResponse>");
		return muleResponse.toString();
	}

	private final String documentToXMLString(final Document document) throws TransformerException {
		final DOMSource domSource = new DOMSource(document);
		final StringWriter writer = new StringWriter();
		final StreamResult result = new StreamResult(writer);
		transformer.transform(domSource, result);
		return writer.toString();
	}

}
