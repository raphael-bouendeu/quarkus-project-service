package org.itbcafrica.microservice;


import org.itbcafrica.microservice.beans.Project;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/projects")
@Produces(MediaType.APPLICATION_JSON)
public class ProjectResource{


    @Inject
    EntityManager entityManager;

    @GET
    public List<Project> getAllProjects(){
        return entityManager.createNamedQuery("Projects.findAll", Project.class).getResultList();
    }

    @GET
    @Path("/{projectId}")
    public Project getProjectById(@PathParam("projectId") Long projectId){
        try{
            return entityManager.createNamedQuery("Projects.findByProjectId", Project.class).setParameter("projectId", projectId).getSingleResult();
        } catch(NoResultException nre){
            throw new NotFoundException("project with id "+projectId+" not found");
        }

    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response createProject(Project project){
        if(project.getProjectId()==null)
            throw new WebApplicationException("Aproject with no id cannot be created", 400);
        entityManager.persist(project);
        return Response.status(Response.Status.CREATED).entity(project).build();
    }

    @PUT
    @Path("/{projectId}/changeStatus")
    @Transactional
    public Project changeStatusOfProject(@PathParam("projectId") Long projectId, String status){
        Project searchedProject=getProjectById(projectId);
        searchedProject.setProjectStatus(status);
        entityManager.merge(searchedProject);
        return searchedProject;
    }

    @DELETE
    @Path("/{projectId}")
    public Response deleteProject(@PathParam("projectId") Long projectId){
        Project searchedProject=getProjectById(projectId);
        searchedProject.setProjectStatus("CLOSED");
        //entityManager.remove(searchedProject);
        return Response.noContent().build();
    }
}