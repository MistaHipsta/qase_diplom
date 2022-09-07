package by.example.rest.clients;


import by.example.rest.dto.responses.TestCaseResponse.TestCaseResponse;
import by.example.rest.dto.testCases.Case;
import io.restassured.response.Response;

import java.util.Map;

public class TestCaseApiClient extends BaseApiClient {

    private final static String CASE_URI = "v1/case";
    private static final String CASE_URI_WITH_CODE = CASE_URI + "/{id}";
    private static final String PROJECT_CODE = "projectCode";
    private static final String CASE_ID = "id";

    public TestCaseResponse postTestCase(Case testCase, String code, int statusCode) {
        Response response = postCode(CASE_URI, testCase, Map.of(PROJECT_CODE, code) );
        return response.then()
                .statusCode(statusCode)
                .extract()
                .body()
                .as(TestCaseResponse.class);

    }
}

