package by.example.rest.clients;

import by.example.rest.dto.responses.TestCaseResponse.TestCaseById;
import by.example.rest.dto.responses.TestCaseResponse.TestCaseResponse;
import by.example.rest.dto.testCases.Case;
import io.restassured.response.Response;

import java.util.Map;

public class TestCaseApiClient extends BaseApiClient {

    public String CASE_URI = "v1/case";
    public  String CASE_URI_WITH_CODE = CASE_URI + "/{code}";
    public String CASE_URI_WITH_CODE_AND_ID = CASE_URI_WITH_CODE + "/{id}";
    public String PROJECT_CODE = "code";
    public String CASE_ID = "id";


    public TestCaseResponse postTestCase(Case testCase, String code, int statusCode) {
        Response response = postCode(CASE_URI_WITH_CODE, testCase, Map.of(PROJECT_CODE, code));
        return response.then()
                .statusCode(statusCode)
                .extract()
                .body()
                .as(TestCaseResponse.class);
    }

    public TestCaseById getTestCase(String code, int id, int httpStasusCode) {
        Response response = get(CASE_URI_WITH_CODE_AND_ID, Map.of(PROJECT_CODE, code, CASE_ID, id));
        return response.then()
                .statusCode(httpStasusCode)
                .extract()
                .body()
                .as(TestCaseById.class);
    }
}

