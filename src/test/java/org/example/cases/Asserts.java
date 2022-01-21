package org.example.cases;

import com.fasterxml.jackson.databind.JsonNode;
import org.testah.TS;

import java.util.Arrays;
import java.util.List;

public class Asserts {

    public void assertLocationSearchResponse(JsonNode response, String title, String locationType) {
        //It is unclear if this endpoint limits the amount of returned data; if so we could make
        //sure the size of the response is within designated limits
        for(int i = 0; i < response.size(); i++) {
            TS.asserts().contains("Assert title", response.get(i).get("title").asText(), title);
            TS.asserts().equalsTo("Assert location type", locationType, response.get(i).get("location_type").asText());
            TS.asserts().isNotEmpty("Assert woeid", response.get(i).get("woeid").asText());

            String latt_long = response.get(i).get("latt_long").asText();
            List<String> latt_long_split = Arrays.asList(latt_long.split(","));
            TS.asserts().isGreaterThan("Assert latitude", -90, Float.parseFloat(latt_long_split.get(0)), true);
            TS.asserts().isLessThan("Assert latitude", 90, Float.parseFloat(latt_long_split.get(0)), true);
            TS.asserts().isGreaterThan("Assert longitude", -180, Float.parseFloat(latt_long_split.get(1)), true);
            TS.asserts().isLessThan("Assert longitude", 180, Float.parseFloat(latt_long_split.get(1)), true);
        }
    }

    public void assertLocationSearchResponse(JsonNode response) {
        //It is unclear if this endpoint limits the amount of returned data; if so we could make
        //sure the size of the response is within designated limits
        for(int i = 0; i < response.size(); i++) {
            TS.asserts().isNotEmpty("Assert title", response.get(i).get("title").asText());
            TS.asserts().isNotEmpty("Assert location type", response.get(i).get("location_type").asText());
            TS.asserts().isNotEmpty("Assert woeid", response.get(i).get("woeid").asText());

            String latt_long = response.get(i).get("latt_long").asText();
            List<String> latt_long_split = Arrays.asList(latt_long.split(","));
            TS.asserts().isGreaterThan("Assert latitude", -90, Float.parseFloat(latt_long_split.get(0)), true);
            TS.asserts().isLessThan("Assert latitude", 90, Float.parseFloat(latt_long_split.get(0)), true);
            TS.asserts().isGreaterThan("Assert longitude", -180, Float.parseFloat(latt_long_split.get(1)), true);
            TS.asserts().isLessThan("Assert longitude", 180, Float.parseFloat(latt_long_split.get(1)), true);

           //Testing specifics of distance depend on desired calculation behavior
            //It appears that the API returns a set # of locations near the designated point
            //but as I have limited examples, the requirement could instead be that
            //"API returns list of stored locations with X miles" instead of "X number of locations"
            //Since I don't know, I am simply asserting against its presence in the response
            TS.asserts().isNotEmpty("Assert distance", response.get(i).get("distance").asText());
        }
    }

    public void assertLocationResponse(JsonNode response, String title, String locationType) {
        //Improvement: wrap in try/catch to better fail with null values / missing JSON
        TS.asserts().equalsTo("Assert title", title, response.get("title").asText());
        TS.asserts().equalsTo("Assert location type", locationType, response.get("location_type").asText());
        TS.asserts().isNotEmpty("Assert woeid", response.get("woeid").asText());
        TS.asserts().isNotEmpty("Assert timezone", response.get("timezone").asText());
        TS.asserts().isNotEmpty("Assert time", response.get("time").asText());
        TS.asserts().isNotEmpty("Assert sun_rise", response.get("sun_rise").asText());
        TS.asserts().isNotEmpty("Assert sun_set", response.get("sun_set").asText());
        TS.asserts().isNotEmpty("Assert timezone_name", response.get("timezone_name").asText());

        String latt_long = response.get("latt_long").asText();
        List<String> latt_long_split = Arrays.asList(latt_long.split(","));
        TS.asserts().isGreaterThan("Assert latitude", -90, Float.parseFloat(latt_long_split.get(0)), true);
        TS.asserts().isLessThan("Assert latitude", 90, Float.parseFloat(latt_long_split.get(0)), true);
        TS.asserts().isGreaterThan("Assert longitude", -180, Float.parseFloat(latt_long_split.get(1)), true);
        TS.asserts().isLessThan("Assert longitude", 180, Float.parseFloat(latt_long_split.get(1)), true);

        assertParentResponse(response.get("parent"));
        assertSourcesResponse(response.get("sources"));
        assertConsolidatedWeatherResponse(response.get("consolidated_weather"));
    }

    public void assertSourcesResponse(JsonNode response) {
        for (int i = 0; i < response.size(); i++) {
            TS.asserts().isNotEmpty("Assert title", response.get(i).get("title").asText());
            TS.asserts().isNotEmpty("Assert slug", response.get(i).get("slug").asText());
            TS.asserts().isNotEmpty("Assert url", response.get(i).get("url").asText());
            TS.asserts().isNotEmpty("Assert crawl_rate", response.get(i).get("crawl_rate").asText());
        }
    }

    public void assertParentResponse(JsonNode response) {
        TS.asserts().isNotEmpty("Assert title", response.get("title").asText());
        TS.asserts().isNotEmpty("Assert location type", response.get("location_type").asText());
        TS.asserts().isNotEmpty("Assert woeid", response.get("woeid").asText());

        String latt_long = response.get("latt_long").asText();
        List<String> latt_long_split = Arrays.asList(latt_long.split(","));
        TS.asserts().isGreaterThan("Assert latitude", -90, Float.parseFloat(latt_long_split.get(0)), true);
        TS.asserts().isLessThan("Assert latitude", 90, Float.parseFloat(latt_long_split.get(0)), true);
        TS.asserts().isGreaterThan("Assert longitude", -180, Float.parseFloat(latt_long_split.get(1)), true);
        TS.asserts().isLessThan("Assert longitude", 180, Float.parseFloat(latt_long_split.get(1)), true);
    }

    public void assertConsolidatedWeatherResponse(JsonNode response) {
        for (int i = 0; i < response.size(); i++) {
            TS.asserts().isNotEmpty("Assert id", response.get(i).get("id").asText());
            TS.asserts().isNotEmpty("Assert weather_state_name", response.get(i).get("weather_state_name").asText());
            TS.asserts().isNotEmpty("Assert weather_state_abbr", response.get(i).get("weather_state_abbr").asText());
            TS.asserts().isNotEmpty("Assert wind_direction_compass", response.get(i).get("wind_direction_compass").asText());
            TS.asserts().isNotEmpty("Assert created", response.get(i).get("created").asText());
            TS.asserts().isNotEmpty("Assert applicable_date", response.get(i).get("applicable_date").asText());
            TS.asserts().notNull("Assert min_temp", response.get(i).get("min_temp").asInt());
            TS.asserts().notNull("Assert max_temp", response.get(i).get("max_temp").asInt());
            TS.asserts().notNull("Assert the_temp", response.get(i).get("the_temp").asInt());
            TS.asserts().notNull("Assert wind_speed", response.get(i).get("wind_speed").asDouble());
            TS.asserts().notNull("Assert wind_direction", response.get(i).get("wind_direction").asDouble());
            TS.asserts().notNull("Assert air_pressure", response.get(i).get("air_pressure").asDouble());
            TS.asserts().notNull("Assert humidity", response.get(i).get("humidity").asInt());
            TS.asserts().notNull("Assert visibility", response.get(i).get("visibility").asDouble());
            TS.asserts().notNull("Assert predictability", response.get(i).get("predictability").asInt());
        }
    }
}
