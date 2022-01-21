package org.example.cases;

import com.fasterxml.jackson.databind.JsonNode;
import org.example.framework.Endpoints;
import org.junit.jupiter.api.Test;
import org.testah.TS;
import org.testah.framework.annotations.TestCase;

public class WeatherByWoeid extends Asserts {

    @Test
    @TestCase
    public void getWeatherValidLocation()  {
        Endpoints endpoint = new Endpoints();
        JsonNode response = endpoint.getLocationWeather("2423945").getResponse();
        assertLocationResponse(response, "Honolulu", "City");
    }

    @Test
    @TestCase
    public void getWeatherInvalidLocation()  {
        Endpoints endpoint = new Endpoints();
        int statusCode = endpoint.getLocationWeather("24239456").getStatusCode();
        TS.asserts().equalsTo("Assert no location found", 404, statusCode);
    }

    @Test
    @TestCase
    public void getWeatherMissingLocation()  {
        Endpoints endpoint = new Endpoints();
        int statusCode = endpoint.getLocationWeather("").getStatusCode();
        TS.asserts().equalsTo("Assert no location found", 404, statusCode);
    }

    @Test
    @TestCase
    public void getWeatherNamedLocation()  {
        Endpoints endpoint = new Endpoints();
        int statusCode = endpoint.getLocationWeather("Honolulu").getStatusCode();
        TS.asserts().equalsTo("Assert no location found", 404, statusCode);
    }

}
