package by.example.rest;

import by.example.utils.PropertiesLoader;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import java.util.Properties;

import static io.restassured.RestAssured.given;

public class GetProjectByCode  {
    @Test
    public void getProgectByCode(){
        Properties properties = PropertiesLoader.loadProperties();
        given().
                contentType(ContentType.JSON).
                accept(ContentType.JSON).
                header("Token",properties.
                getProperty("Token")).
                log().all().
        when().
                get("https://api.qase.io/v1/project/DEMO").
        then().
                statusCode(200);
    }
}
