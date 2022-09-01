package by.example.rest.clients;

import by.example.utils.PropertiesLoader;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.Map;
import java.util.Properties;

import static io.restassured.RestAssured.given;

public class BaseApiClient {
    RequestSpecification baseRequest;
    Properties properties = PropertiesLoader.loadProperties();

    public BaseApiClient() {
        baseRequest = given()
                .contentType(ContentType.JSON)
                .accept(ContentType.JSON)
                .header("Token", properties.getProperty("Token"))
                .baseUri("https://api.qase.io/")
                .log().ifValidationFails();

    }

    public Response post(String uri, Object body, int statusCode) {
        return  given().
                spec(baseRequest).
                body(body).
                log().ifValidationFails().
                when().
                post("https://api.qase.io/"+ uri).
                then().
                statusCode(statusCode).
                log().ifValidationFails().
                extract().
                response();
    }
    public Response get(String uri, Map<String, ?> code) {
        return   given()
                .spec(baseRequest)
                .pathParams(code)
                .when()
                .log().ifValidationFails()
                .get(uri)
                .then()
                .log().ifValidationFails()
                .extract()
                .response();
    }

}
