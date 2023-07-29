package org.itbcafrica.microservice;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.response.Response;
import org.itbcafrica.microservice.beans.Project;
import org.junit.jupiter.api.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.hasSize;

@QuarkusTest
public class ProjectResourceTest{

    @Test
    public void testHelloEndpoint(){
        Response response=given().when().get("/projects").then().statusCode(200).body(containsString("Simple Project")).extract().response();
        List<Project> projects=response.jsonPath().getList("$");
        assertThat(projects, not(empty()));
        assertThat(projects, hasSize(4));
    }

    @Test
    void testGetProjectById(){
        Project returnedProject=given().when().get("/projects/{projectId}", 637245L).then().statusCode(200).extract().as(Project.class);
        assertThat(returnedProject.getProjectId(), equalTo(637245L));
        assertThat(returnedProject.getProjectName(), equalTo("Simple Project"));
    }
}