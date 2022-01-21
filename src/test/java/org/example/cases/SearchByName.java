package org.example.cases;

import com.fasterxml.jackson.databind.JsonNode;
import org.junit.jupiter.api.Test;
import org.testah.TS;
import org.testah.driver.http.response.ResponseDto;
import org.testah.framework.annotations.TestCase;
import org.example.framework.Endpoints;

public class SearchByName extends Asserts {

    @Test
    @TestCase
    public void queryALocationByStringValidLocation()  {
        Endpoints endpoint = new Endpoints();
        ResponseDto response = endpoint.getLocationByQuery("Mount");
        assertLocationSearchResponse(response.getResponse(), "Mount", "City");
    }

    @Test
    @TestCase
    public void queryALocationByStringInvalidLocation() {
        Endpoints endpoint = new Endpoints();
        ResponseDto response = endpoint.getLocationByQuery("nonsense");
        TS.asserts().isEmpty("Assert no location found", response.getResponse().asText());
    }

    @Test
    @TestCase
    public void queryALocationByStringInt() {
        Endpoints endpoint = new Endpoints();
        JsonNode response = endpoint.getLocationByQuery("123").getResponse();
        TS.asserts().isEmpty("Assert no location found", response.asText());
    }

    @Test
    @TestCase
    public void queryALocationByStringEmpty() {
        Endpoints endpoint = new Endpoints();
        int status = endpoint.getLocationByQuery("").getStatusCode();
        TS.asserts().equalsTo("Assert no location found", 403, status);
    }
}
