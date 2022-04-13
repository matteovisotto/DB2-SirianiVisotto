/*
package it.polimi.db2.telco.api;

import com.google.gson.Gson;
import it.polimi.db2.telco.controllers.ServiceController;
import it.polimi.db2.telco.entities.Service;
import it.polimi.db2.telco.entities.ServicePackage;
import it.polimi.db2.telco.exceptions.alert.AlertException;
import it.polimi.db2.telco.exceptions.service.ServiceException;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/service")
public class ServiceResource {
    @Inject
    private ServiceController serviceController;
    private final Gson gson = new Gson();

    @Context
    HttpServletRequest request;

    @GET
    @Path("/{id: [0-9]+}")
    @Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @RolesAllowed({"user", "administrator"})
    public Response getServiceById(@PathParam("id") Integer id) {
        try {
            Service service = serviceController.getServiceById(id);
            return Response.ok().entity(gson.toJson(service)).build();
        } catch (ServiceException e) {
            return Response.status(204).entity("{}").build();
        }
    }

    @GET
    @Path("/{name}")
    @Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @RolesAllowed({"user", "administrator"})
    public Response getServiceByName(@PathParam("name") String name) {
        try {
            Service service = serviceController.getServiceByName(name);
            return Response.ok().entity(gson.toJson(service)).build();
        } catch (ServiceException e) {
            return Response.status(204).entity("{}").build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @RolesAllowed({"administrator"})
    public Response getAllServices() {
        try {
            List<Service> services = serviceController.getAllServices();
            return Response.ok().entity(gson.toJson(services)).build();
        } catch (AlertException e) {
            return Response.status(204).entity("{}").build();
        }
    }

    @POST
    @Path("/create")
    @Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @Consumes(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @RolesAllowed({"administrator"})
    public Response createService(Service service){
        try {
            serviceController.createService(service);
            return Response.ok().entity("{\"success\":1}").build();
        } catch (Exception e) {
            return Response.status(400).entity("{\"success\":0}").build();
        }
    }

    @POST
    @Path("/update")
    @Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @Consumes(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @RolesAllowed({"administrator"})
    public Response updateService(Service service){
        try {
            serviceController.updateService(service);
            return Response.ok().entity("{\"success\":1}").build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.status(400).entity("{\"success\":0}").build();
        }
    }

    @POST
    @Path("/delete/{serviceId:[0-9]+}")
    @Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @Consumes(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @RolesAllowed({"administrator"})
    public Response deleteService(@PathParam("serviceId") Integer serviceId){
        try {
            serviceController.deleteService(serviceId);
            return Response.ok().entity("{\"success\":1}").build();
        } catch (Exception e) {
            return Response.status(400).entity("{\"success\":0}").build();
        }
    }
}
*/
