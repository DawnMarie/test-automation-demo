package org.example.framework;

import com.google.gson.JsonObject;
import org.apache.http.HttpRequest;
import com.fasterxml.jackson.databind.JsonNode;
import org.testah.TS;
import org.testah.driver.http.HttpWrapperV2;

import java.net.http.HttpClient;

public class Endpoints {

    public static String URL = "www.metaweather.com/";
    public static String searchQuery = "/api/location/search/?query=";
    public static String searchLatLong = "/api/location/search/?lattlong=";
    public static String location = "/api/location/";

    public JsonNode getLocationByQuery(String query) {
        HttpWrapperV2 restClient = new HttpWrapperV2();
        return restClient.doGet(URL + searchQuery + query).getResponse();
    }

    public JsonNode getLocationByLatLong(String lat, String lon) {
        HttpWrapperV2 restClient = new HttpWrapperV2();
        return restClient.doGet(URL + searchLatLong + lat + "," + lon).getResponse();
    }

    public JsonNode getLocationWeather(String woeid) {
        HttpWrapperV2 restClient = new HttpWrapperV2();
        return restClient.doGet(URL + location + woeid + "/").getResponse();
    }

    public JsonNode getLocationDate(String woeid, String date) {
        HttpWrapperV2 restClient = new HttpWrapperV2();
        return restClient.doGet(URL + location + woeid + "/" + date + "/").getResponse();
    }

    public void assertLocationSearchResponse() {}

    public void assertLocationResponse() {}
}
