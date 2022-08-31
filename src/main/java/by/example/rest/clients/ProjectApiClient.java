package by.example.rest.clients;

import by.example.rest.dto.project.Project;
import by.example.rest.dto.responses.ProjResp;
import by.example.utils.PropertiesLoader;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import java.util.Properties;

import static io.restassured.RestAssured.given;

public class ProjectApiClient extends BaseApiClient {

 public ProjResp postProject(Project project, int stasusCode) {
     Response response = post("v1/project", project,200);
     return  response.then()
                     .statusCode(200)
                     .extract()
                     .body()
                     .as(ProjResp.class);

 }
    public Project getProject(String projectId){
        Properties properties = PropertiesLoader.loadProperties();
       return   given().
                contentType(ContentType.JSON).
                accept(ContentType.JSON).
                header("Token",properties.
                        getProperty("Token")).
                pathParam(projectId,projectId).
                when().
                get("https://api.qase.io/v1/project/{projectId}").
                then().
                statusCode(200).extract().as(Project.class);
    }
}
