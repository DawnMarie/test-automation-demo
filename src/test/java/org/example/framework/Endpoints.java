package org.example.framework;

import com.fasterxml.jackson.databind.JsonNode;
import org.testah.driver.http.HttpWrapperV2;
import org.testah.driver.http.response.ResponseDto;

public class Endpoints {

    public static String URL = "http://www.metaweather.com";
    public static String searchQuery = "/api/location/search/?query=";
    public static String searchLatLong = "/api/location/search/?lattlong=";
    public static String location = "/api/location/";

    public ResponseDto getLocationByQuery(String query) {
        HttpWrapperV2 restClient = new HttpWrapperV2();
        return restClient.doGet(URL + searchQuery + query);
    }

    public ResponseDto getLocationByLatLong(String lat, String lon) {
        HttpWrapperV2 restClient = new HttpWrapperV2();
        return restClient.doGet(URL + searchLatLong + lat + "," + lon);
    }

    public ResponseDto getLocationWeather(String woeid) {
        HttpWrapperV2 restClient = new HttpWrapperV2();
        return restClient.doGet(URL + location + woeid + "/");
    }

    public ResponseDto getLocationDate(String woeid, String date) {
        HttpWrapperV2 restClient = new HttpWrapperV2();
        return restClient.doGet(URL + location + woeid + "/" + date + "/");
    }

}
