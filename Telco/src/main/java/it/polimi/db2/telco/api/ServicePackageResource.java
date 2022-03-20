package it.polimi.db2.telco.api;

import com.google.gson.Gson;
import it.polimi.db2.telco.beans.ServicePackageBean;
import it.polimi.db2.telco.controllers.ServicePackageController;
import it.polimi.db2.telco.entities.ServicePackage;
import it.polimi.db2.telco.exceptions.alert.AlertException;
import it.polimi.db2.telco.exceptions.servicePackage.ServicePackageException;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.stream.Collectors;

@Path("/servicePackage")
public class ServicePackageResource {
    @Inject
    private ServicePackageController servicePackageController;
    private final Gson gson = new Gson();

    @Context
    HttpServletRequest request;

    @GET
    @Path("/{id: [0-9]+}")
    @Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @RolesAllowed({"user", "administrator"})
    public Response getServicePackageById(@PathParam("id") Integer id) {
        try {
            ServicePackageBean servicePackage = new ServicePackageBean(servicePackageController.getServicePackageById(id));
            return Response.ok().entity(gson.toJson(servicePackage)).build();
        } catch (ServicePackageException e) {
            return Response.status(204).entity("{}").build();
        }
    }

    @GET
    @Path("/{name}")
    @Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @RolesAllowed({"user", "administrator"})
    public Response getServicePackageByName(@PathParam("name") String name) {
        try {
            ServicePackageBean servicePackage = new ServicePackageBean(servicePackageController.getServicePackageByName(name));
            return Response.ok().entity(gson.toJson(servicePackage)).build();
        } catch (ServicePackageException e) {
            return Response.status(204).entity("{}").build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    public Response getAllServicePackages() {
        try {
            List<ServicePackageBean> servicePackages = servicePackageController.getAllServicePackages()
                    .stream()
                    .map(ServicePackageBean::new)
                    .collect(Collectors.toList());
            return Response.ok().entity(gson.toJson(servicePackages)).build();
        } catch (AlertException e) {
            return Response.status(204).entity("{}").build();
        }
    }

    @POST
    @Path("/create")
    @Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @Consumes(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @RolesAllowed({"administrator"})
    public Response createServicePackage(ServicePackage servicePackage){
        try {
            servicePackageController.createServicePackage(servicePackage);
            return Response.ok().entity("{\"success\":1}").build();
        } catch (ServicePackageException e) {
            return Response.status(400).entity("{\"success\":0}").build();
        }
    }

    @POST
    @Path("/update")
    @Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @Consumes(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @RolesAllowed({"administrator"})
    public Response updateServicePackage(ServicePackage servicePackage){
        try {
            servicePackageController.updateServicePackage(servicePackage);
            return Response.ok().entity("{\"success\":1}").build();
        } catch (ServicePackageException e) {
            e.printStackTrace();
            return Response.status(400).entity("{\"success\":0}").build();
        }
    }

    @POST
    @Path("/delete/{servicePackageId:[0-9]+}")
    @Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @Consumes(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @RolesAllowed({"administrator"})
    public Response deleteServicePackage(@PathParam("servicePackageId") Integer servicePackageId){
        try {
            servicePackageController.deleteServicePackage(servicePackageId);
            return Response.ok().entity("{\"success\":1}").build();
        } catch (ServicePackageException e) {
            return Response.status(400).entity("{\"success\":0}").build();
        }
    }
}
