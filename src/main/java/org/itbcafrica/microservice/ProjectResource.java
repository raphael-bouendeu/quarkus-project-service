package org.itbcafrica.microservice;

import org.itbcafrica.microservice.beans.Project;

import javax.annotation.PostConstruct;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path("/projects")
@Produces(MediaType.APPLICATION_JSON)
public class ProjectResource{

    List<Project> projectList=new ArrayList<>();

    @PostConstruct
    public void setup(){
        projectList.add(new Project(637245L, "Simple Project", 5, 6, "NEW"));
        projectList.add(new Project(637246L, "Java Project", 5, 6, "DELAYED"));
        projectList.add(new Project(637247L, "Maven Project", 6, 7, "IN_PROGRESS"));
        projectList.add(new Project(637248L, "Focker Project", 10, 16, "new"));

    }

    @GET
    public List<Project> getAllProjects(){
        return projectList;
    }

    @GET
    @Path("/{projectId}")
    public Project getProjectById(@PathParam("projectId") Long projectId){
        return projectList.stream().filter(project->project.getProjectId().equals(projectId)).findFirst().orElseThrow(()->new NotFoundException("project with id "+projectId+" not found"));
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createProject(Project project){
        if(project.getProjectId()==null)
            throw new WebApplicationException("Aproject with no id cannot be created", 400);
        projectList.add(project);
        return Response.status(Response.Status.CREATED).entity(project).build();
    }

    @PUT
    @Path("/{projectId}/changeStatus")
    public Project changeStatusOfProject(@PathParam("projectId") Long projectId, String status){
        Project searchedProject=getProjectById(projectId);
        searchedProject.setProjectStatus(status);
        return searchedProject;
    }

    @DELETE
    @Path("/{projectId}")
    public Response deleteProject(@PathParam("projectId") Long projectId){
        Project searchedProject=getProjectById(projectId);
        projectList.remove(searchedProject);
        return Response.noContent().build();
    }
}