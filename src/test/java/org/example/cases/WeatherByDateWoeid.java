package org.example.cases;

import com.fasterxml.jackson.databind.JsonNode;
import org.example.framework.Endpoints;
import org.junit.Ignore;
import org.junit.jupiter.api.Test;
import org.testah.TS;
import org.testah.framework.annotations.TestCase;

public class WeatherByDateWoeid extends Asserts {

    @Test
    @TestCase
    public void getWeatherValidDate()  {
        Endpoints endpoint = new Endpoints();
        JsonNode response = endpoint.getLocationDate("2423945", "2020/4/7").getResponse();
        assertConsolidatedWeatherResponse(response);
    }

    @Test
    @TestCase
    @Ignore("Throws a 500 error - bug - logged as XXX-101")
    public void getWeatherInvalidDate()  {
        Endpoints endpoint = new Endpoints();
        JsonNode response = endpoint.getLocationDate("2423945", "2020/4/32").getResponse();
        assertConsolidatedWeatherResponse(response);
    }

    @Test
    @TestCase
    public void getWeatherInvalidLocation()  {
        Endpoints endpoint = new Endpoints();
        int statusCode = endpoint.getLocationDate("24239456", "2020/4/30").getStatusCode();
        TS.asserts().equalsTo("Assert no location found", 404, statusCode);
    }

    @Test
    @TestCase
    public void getWeatherMissingLocation()  {
        Endpoints endpoint = new Endpoints();
        int statusCode = endpoint.getLocationDate("", "2020/4/30").getStatusCode();
        TS.asserts().equalsTo("Assert no location found", 404, statusCode);
    }

    //No separate missing date test case, because the endpoint missing the date is identical to
    //the endpoint to search by location woeid
    @Test
    @TestCase
    public void getWeatherNamedLocation()  {
        Endpoints endpoint = new Endpoints();
        int statusCode = endpoint.getLocationDate("Honolulu", "2020/4/30").getStatusCode();
        TS.asserts().equalsTo("Assert no location found", 404, statusCode);
    }

}
