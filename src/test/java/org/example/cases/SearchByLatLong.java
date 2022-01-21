package org.example.cases;

import org.example.framework.Endpoints;
import org.junit.Ignore;
import org.junit.jupiter.api.Test;
import org.testah.driver.http.response.ResponseDto;
import org.testah.framework.annotations.TestCase;

public class SearchByLatLong extends Asserts {

    @Test
    @TestCase
    public void queryALocationByLatLongValidLocation()  {
        Endpoints endpoint = new Endpoints();
        ResponseDto response = endpoint.getLocationByLatLong("-10", "10");
        assertLocationSearchResponse(response.getResponse());
    }

    @Test
    @TestCase
    public void queryALocationByInvalidLatLong()  {
        //Apparently intended behavior is to return locations as close to 180 as possible,
        //rather than return an error for an out-of-range value. Given that both this test
        //and the subsequent test return the same results I HAVE QUESTIONS if this is actually
        //intended behavior.
        Endpoints endpoint = new Endpoints();
        ResponseDto response = endpoint.getLocationByLatLong("190", "10");
        assertLocationSearchResponse(response.getResponse());
    }

    @Test
    @TestCase
    public void queryALocationByLatInvalidLong()  {
        Endpoints endpoint = new Endpoints();
        ResponseDto response = endpoint.getLocationByLatLong("-10", "190");
        assertLocationSearchResponse(response.getResponse());
    }

    @Test
    @TestCase
    @Ignore("Throws a 500 error; a bug that could be logged as XXXX-1000")
    public void queryALocationByStringLatLong()  {
        Endpoints endpoint = new Endpoints();
        ResponseDto response = endpoint.getLocationByLatLong("test", "190");
        assertLocationSearchResponse(response.getResponse());
    }


}
