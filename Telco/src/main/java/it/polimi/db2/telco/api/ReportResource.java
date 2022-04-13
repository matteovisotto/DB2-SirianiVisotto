/*
package it.polimi.db2.telco.api;

import com.google.gson.Gson;
import it.polimi.db2.telco.controllers.*;
import it.polimi.db2.telco.entities.*;
import it.polimi.db2.telco.exceptions.report.ReportException;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/report")
public class ReportResource {
    @Inject
    private AveragePurchaseOptionalPackageController averagePurchaseOptionalPackageController;

    @Inject
    private TotalPurchaseOptionalController totalPurchaseOptionalController;

    @Inject
    private TotalPurchasePackageOptionalController totalPurchasePackageOptionalController;

    @Inject
    private TotalPurchasePackageController totalPurchasePackageController;

    @Inject
    private TotalPurchasePackageValidityController totalPurchasePackageValidityController;
    private final Gson gson = new Gson();

    @Context
    HttpServletRequest request;

    @GET
    @Path("/averagePurchaseOptionalPackages")
    @Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @RolesAllowed({"administrator"})
    public Response getAllAveragePurchaseOptionalPackages() {
        try {
            List<AveragePurchaseOptionalPackage> averagePurchaseOptionalPackages = averagePurchaseOptionalPackageController.getAllAveragePurchaseOptionalPackage();
            return Response.ok().entity(gson.toJson(averagePurchaseOptionalPackages)).build();
        } catch (ReportException e) {
            return Response.status(204).entity("{}").build();
        }
    }

    @GET
    @Path("/totalPurchaseOptionals")
    @Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @RolesAllowed({"administrator"})
    public Response getAllTotalPurchaseOptionals() {
        try {
            List<TotalPurchaseOptional> totalPurchaseOptionals = totalPurchaseOptionalController.getAllTotalPurchaseOptionals();
            return Response.ok().entity(gson.toJson(totalPurchaseOptionals)).build();
        } catch (ReportException e) {
            return Response.status(204).entity("{}").build();
        }
    }

    @GET
    @Path("/totalPurchasePackageOptionals")
    @Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @RolesAllowed({"administrator"})
    public Response getAllTotalPurchasePackageOptionals() {
        try {
            List<TotalPurchasePackageOptional> totalPurchasePackageOptionals = totalPurchasePackageOptionalController.getAllTotalPurchasePackageOptionals();
            return Response.ok().entity(gson.toJson(totalPurchasePackageOptionals)).build();
        } catch (ReportException e) {
            return Response.status(204).entity("{}").build();
        }
    }

    @GET
    @Path("/totalPurchasePackages")
    @Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @RolesAllowed({"administrator"})
    public Response getAllTotalPurchasePackages() {
        try {
            List<TotalPurchasePackage> totalPurchasePackages = totalPurchasePackageController.getAllTotalPurchasePackages();
            return Response.ok().entity(gson.toJson(totalPurchasePackages)).build();
        } catch (ReportException e) {
            return Response.status(204).entity("{}").build();
        }
    }

    @GET
    @Path("/totalPurchasePackageValidity")
    @Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @RolesAllowed({"administrator"})
    public Response getAllTotalPurchasePackageValidity() {
        try {
            List<TotalPurchasePackageValidity> totalPurchasePackageValidity = totalPurchasePackageValidityController.getAllTotalPurchasePackageValidity();
            return Response.ok().entity(gson.toJson(totalPurchasePackageValidity)).build();
        } catch (ReportException e) {
            return Response.status(204).entity("{}").build();
        }
    }
}
*/
