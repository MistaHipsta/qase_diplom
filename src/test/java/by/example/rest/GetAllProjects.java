package by.example.rest;

import by.example.utils.PropertiesLoader;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import java.util.Properties;

import static io.restassured.RestAssured.given;

public class GetAllProjects {

    @Test
    public void getProgectByCode(){
        Properties properties = PropertiesLoader.loadProperties();

        String code = given().
                contentType(ContentType.JSON).
                accept(ContentType.JSON).
                header("Token",properties.
                getProperty("Token")).
                log().all().
        when().
                get("https://api.qase.io/v1/project").
        then().
                statusCode(200).
                extract().body().jsonPath().getString("result.entities[0].code");

        given().
                contentType(ContentType.JSON).
                accept(ContentType.JSON).
                header("Token",properties.
                getProperty("Token")).
                pathParams("firstProjectCode", code).
                log().all().
        when().
                get("https://api.qase.io/v1/project/{firstProjectCode}").
        then().
                statusCode(200);

    }
}
