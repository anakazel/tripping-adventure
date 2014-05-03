package soapwrapper;

import soapwrapper.impl.DirectionsImpl;
import soapwrapper.impl.ForecastImpl;
import soapwrapper.impl.PlacesImpl;
import soapwrapper.impl.SearchImpl;

import javax.xml.ws.Endpoint;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by AlexG on 4/26/14.
 */
public class Publisher {
    public static String DIRECTIONS_URL;
    public static String FORECAST_URL;
    public static String CUSTOMSEARCH_URL;
    public static String PLACES_URL;
    public static String API_KEY;
    public static String DIRECTIONS_SENSOR;
    public static String FORECAST_MODE;
    public static String CUSTOMSEARCH_CX;
    public static String CUSTOMSEARCH_SEARCH_TYPE;
    public static String CUSTOMSEARCH_ALT;

    private static int DIRECTIONS_PORT;
    private static int FORECAST_PORT;
    private static int CUSTOMSEARCH_PORT;
    private static int PLACES_PORT;

    public static void main(String[] args) {
        final Properties prop = new Properties();
        try(InputStream input = Publisher.class.getClassLoader().getResourceAsStream("config.properties")){
            prop.load(input);
            DIRECTIONS_PORT = Integer.valueOf(prop.getProperty("google.directions.port"));
            FORECAST_PORT = Integer.valueOf(prop.getProperty("openweathermap.port"));
            CUSTOMSEARCH_PORT = Integer.valueOf(prop.getProperty("google.customsearch.port"));
            PLACES_PORT = Integer.valueOf(prop.getProperty("google.places.port"));

            API_KEY = prop.getProperty("google.developer.apikey");
            DIRECTIONS_SENSOR = prop.getProperty("directions.sensor");
            FORECAST_MODE = prop.getProperty("forecast.mode");
            CUSTOMSEARCH_CX = prop.getProperty("customsearch.cx");
            CUSTOMSEARCH_SEARCH_TYPE = prop.getProperty("customsearch.searchType");
            CUSTOMSEARCH_ALT = prop.getProperty("customsearch.alt");

            PLACES_URL = prop.getProperty("google.places.api");
            DIRECTIONS_URL = prop.getProperty("google.directions.api");
            FORECAST_URL = prop.getProperty("openweathermap.api");
            CUSTOMSEARCH_URL = prop.getProperty("google.customsearch.api");

            System.out.println(
                    "API_KEY=" + API_KEY +
                    "\nDIRECTIONS_SENSOR=" + DIRECTIONS_SENSOR +
                    "\nFORECAST_MODE=" + FORECAST_MODE +
                    "\nCUSTOMSEARCH_CX=" + CUSTOMSEARCH_CX +
                    "\nCUSTOMSEARCH_SEARCH_TYPE=" + CUSTOMSEARCH_SEARCH_TYPE +
                    "\nCUSTOMSEARCH_ALT=" + CUSTOMSEARCH_ALT);

            publishServices();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static final void publishServices(){
//        ExecutorService pool = Executors.newFixedThreadPool(10);
        Endpoint endpoint = Endpoint.publish("http://localhost:" + DIRECTIONS_PORT + "/soapwrapper/Directions", new DirectionsImpl());
//        endpoint.setExecutor(pool);
        Endpoint.publish("http://localhost:" + FORECAST_PORT + "/soapwrapper/Forecast", new ForecastImpl());
        Endpoint.publish("http://localhost:" + PLACES_PORT + "/soapwrapper/Places", new PlacesImpl());
        Endpoint.publish("http://localhost:" + CUSTOMSEARCH_PORT + "/soapwrapper/Search", new SearchImpl());
    }
}
