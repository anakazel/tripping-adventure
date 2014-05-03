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
	private final static TransformerFactory tf = TransformerFactory.newInstance();
		
	private final static String XML_DECLARATION = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
	private final static String TRAVEL_RESPONSE_START = "<TravelResponse>";
	private final static String TRAVEL_RESPONSE_END = "</TravelResponse>";
	private final static String FORECAST_RESPONSE_START = "<ForecastResponse>";
	private final static String FORECAST_RESPONSE_END = "</ForecastResponse>";
	private final static String DIRECTIONS_RESPONSE_START = "<DirectionsResponse>";
	private final static String DIRECTIONS_RESPONSE_END = "</DirectionsResponse>";
	private final static String PLACES_RESPONSE_START = "<PlacesResponse>";
	private final static String PLACES_RESPONSE_END = "</PlacesResponse>";
	private final static String SEARCH_RESPONSE_START = "<SearchResponse>";
	private final static String SEARCH_RESPONSE_END = "</SearchResponse>";
	
	private static Transformer transformer;
	
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
		final StringBuilder muleResponse = new StringBuilder(XML_DECLARATION + TRAVEL_RESPONSE_START);
		String weatherResponse = "", directionsResponse = "", searchResponse = "", placesResponse = "";
		
		final DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
		final DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
		final Document weatherDocument, directionsDocument, searchDocument, placesDocument;

		try{
			directionsResponse = eventContext.getMessage().getInvocationProperty("directionsResponse");
			directionsDocument = docBuilder.parse(new InputSource(new StringReader(directionsResponse)));	
			muleResponse.append(DIRECTIONS_RESPONSE_START).append(documentToXMLString(directionsDocument)).append(DIRECTIONS_RESPONSE_END);
		}catch(Exception ex){
			ex.printStackTrace();
			muleResponse.append(DIRECTIONS_RESPONSE_START).append(directionsResponse).append(DIRECTIONS_RESPONSE_END);
		}
		
		try{
			placesResponse = eventContext.getMessage().getInvocationProperty("placesResponse");
			placesDocument = docBuilder.parse(new InputSource(new StringReader(placesResponse)));
			muleResponse.append(PLACES_RESPONSE_START).append(documentToXMLString(placesDocument)).append(PLACES_RESPONSE_END);
		}catch(Exception ex){
			ex.printStackTrace();
			muleResponse.append(PLACES_RESPONSE_START).append(placesResponse).append(PLACES_RESPONSE_END);
		}
		
		try{
			searchResponse = eventContext.getMessage().getInvocationProperty("searchResponse");
			searchDocument = docBuilder.parse(new InputSource(new StringReader(searchResponse)));
			muleResponse.append(SEARCH_RESPONSE_START).append(documentToXMLString(searchDocument)).append(SEARCH_RESPONSE_END);
		}catch(Exception ex){
			ex.printStackTrace();
			muleResponse.append(SEARCH_RESPONSE_START).append(searchResponse).append(SEARCH_RESPONSE_END);
		}
		
		try {
			weatherResponse = eventContext.getMessage().getInvocationProperty("weatherResponse");
			weatherDocument = docBuilder.parse(new InputSource(new StringReader(weatherResponse)));
			muleResponse.append(FORECAST_RESPONSE_START).append(documentToXMLString(weatherDocument)).append(FORECAST_RESPONSE_END);
		} catch (Exception ex) {
			ex.printStackTrace();
			muleResponse.append(FORECAST_RESPONSE_START).append(weatherResponse).append(FORECAST_RESPONSE_END);
		}
		
		muleResponse.append(TRAVEL_RESPONSE_END);
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
