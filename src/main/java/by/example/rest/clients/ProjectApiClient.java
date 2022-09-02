package by.example.rest.clients;

import by.example.rest.dto.project.Project;
import by.example.rest.dto.responses.project.ProjResp;
import by.example.rest.dto.responses.projectByCode.ProjRespByCode;
import io.restassured.response.Response;

import java.util.Map;

public class ProjectApiClient extends BaseApiClient {

    private final static String PROJECT_URI = "v1/project";
    private static final String PROJECT_URI_WITH_CODE = PROJECT_URI + "/{projectCode}";
    private static final String PROJECT_CODE = "projectCode";

    public ProjResp postProject(Project project, int statusCode) {
        Response response = post(PROJECT_URI, project, statusCode);
        return response.then()
                .statusCode(statusCode)
                .extract()
                .body()
                .as(ProjResp.class);

    }

    public ProjRespByCode getProject(String projectCode, int statusCodeHttp) {
        Response response = get(PROJECT_URI_WITH_CODE, Map.of(PROJECT_CODE, projectCode));
        return response.then()
                .statusCode(statusCodeHttp)
                .extract()
                .body()
                .as(ProjRespByCode.class);
    }
}
