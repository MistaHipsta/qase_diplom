package by.example.rest.clients;

import by.example.rest.dto.project.Project;
import by.example.rest.dto.responses.ProjResp;
import by.example.utils.PropertiesLoader;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.Map;
import java.util.Properties;

import static io.restassured.RestAssured.given;

public class ProjectApiClient extends BaseApiClient {

    private final static String PROJECT_URI = "v1/defect";
    private static final String PROJECT_URI_WITH_CODE = PROJECT_URI + "/{code}";
    private static final String PROJECT_CODE = "code";

 public ProjResp postProject(Project project, int stasusCode) {
     Response response = post("v1/project", project,200);
     return  response.then()
                     .statusCode(200)
                     .extract()
                     .body()
                     .as(ProjResp.class);

 }
//    public ProjResp getProject(String projectId, int statusCodeHttp){
//        Response response = get(projectId);
//        return response.then()
//                .statusCode(statusCodeHttp)
//                .extract()
//                .body()
//                .as(ProjResp.class);
//    }
}
