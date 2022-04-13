package it.polimi.db2.telco.api;

import com.google.gson.Gson;
import it.polimi.db2.telco.beans.OptionalProductBean;
import it.polimi.db2.telco.controllers.OptionalProductController;
import it.polimi.db2.telco.controllers.ServicePackageController;
import it.polimi.db2.telco.entities.Alert;
import it.polimi.db2.telco.entities.OptionalProduct;
import it.polimi.db2.telco.exceptions.optionalProduct.OptionalProductException;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.stream.Collectors;

@Path("/optional")
public class OptionalProductResource {
    /*@Inject
    OptionalProductController optionalProductController;*/
    Gson gson = new Gson();

    @Inject
    ServicePackageController servicePackageController;

    @Context
    HttpServletRequest request;

    /*@GET
    @Path("/{id: [0-9]+}")
    @Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @RolesAllowed({"administrator"})
    public Response getOptionalProductById(@PathParam("id") Integer id) {
        try {
            OptionalProductBean optionalProduct = new OptionalProductBean(optionalProductController.getOptionalProductById(id));
            return Response.ok().entity(gson.toJson(optionalProduct)).build();
        } catch (OptionalProductException e) {
            return Response.status(204).entity("{}").build();
        }
    }*/

    @GET
    @Path("/package/{id: [0-9]+}")
    @Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    public Response getOptionalProductByPackageId(@PathParam("id") Integer id) {
        try {
            List<OptionalProductBean> optionalProducts = servicePackageController.getServicePackageById(id)
                    .getOptionalProducts()
                    .stream()
                    .map(OptionalProductBean::new)
                    .collect(Collectors.toList());
            return Response.ok().entity(gson.toJson(optionalProducts)).build();
        } catch (OptionalProductException e) {
            return Response.status(204).entity("{}").build();
        }
    }
/*
    @GET
    @Path("/{name}")
    @Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @RolesAllowed({"administrator"})
    public Response getOptionalProductByName(@PathParam("name") String name) {
        try {
            OptionalProductBean optionalProduct = new OptionalProductBean(optionalProductController.getOptionalProductByName(name));
            return Response.ok().entity(gson.toJson(optionalProduct)).build();
        } catch (OptionalProductException e) {
            return Response.status(204).entity("{}").build();
        }
    }



    @GET
    @Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @RolesAllowed({"administrator"})
    public Response getAllOptionalProducts() {
        try {
            List<OptionalProductBean> optionalProducts = optionalProductController.getAllOptionalProducts()
                    .stream()
                    .map(OptionalProductBean::new)
                    .collect(Collectors.toList());
            return Response.ok().entity(gson.toJson(optionalProducts)).build();
        } catch (AlertException e) {
            return Response.status(204).entity("{}").build();
        }
    }

    @POST
    @Path("/create")
    @Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @Consumes(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @RolesAllowed({"administrator"})
    public Response createOptionalProduct(OptionalProduct optionalProduct){
        try {
            optionalProductController.createOptionalProduct(optionalProduct);
            return Response.ok().entity("{\"success\":1}").build();
        } catch (OptionalProductException e) {
            return Response.status(400).entity("{\"success\":0}").build();
        }
    }

    @POST
    @Path("/update")
    @Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @Consumes(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @RolesAllowed({"administrator"})
    public Response updateOptionalProduct(OptionalProduct optionalProduct){
        try {
            optionalProductController.updateOptionalProduct(optionalProduct);
            return Response.ok().entity("{\"success\":1}").build();
        } catch (OptionalProductException e) {
            e.printStackTrace();
            return Response.status(400).entity("{\"success\":0}").build();
        }
    }

    @POST
    @Path("/delete/{optionalProductId:[0-9]+}")
    @Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @Consumes(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @RolesAllowed({"administrator"})
    public Response deleteOptionalProduct(@PathParam("optionalProductId") Integer optionalProductId){
        try {
            optionalProductController.deleteOptionalProduct(optionalProductId);
            return Response.ok().entity("{\"success\":1}").build();
        } catch (OptionalProductException e) {
            return Response.status(400).entity("{\"success\":0}").build();
        }
    }*/
}
