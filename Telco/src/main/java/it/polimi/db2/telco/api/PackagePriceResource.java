package it.polimi.db2.telco.api;

import com.google.gson.Gson;
import it.polimi.db2.telco.controllers.PackagePriceController;
import it.polimi.db2.telco.entities.PackagePrice;
import it.polimi.db2.telco.exceptions.packagePrice.PackagePriceException;
import it.polimi.db2.telco.exceptions.servicePackage.ServicePackageException;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/price")
public class PackagePriceResource {
    @Inject
    private PackagePriceController packagePriceController;
    private final Gson gson = new Gson();

    @Context
    HttpServletRequest request;

    @GET
    @Path("/{id: [0-9]+}")
    @Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @RolesAllowed({"user", "administrator"})
    public Response getPackagePriceById(@PathParam("id") Integer id) {
        try {
            PackagePrice packagePrice = packagePriceController.getPackagePriceById(id);
            return Response.ok().entity(gson.toJson(packagePrice)).build();
        } catch (PackagePriceException e) {
            return Response.status(204).entity("{}").build();
        }
    }

    @GET
    @Path("/package/{packageId: [0-9]+}")
    @Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @RolesAllowed({"user", "administrator"})
    public Response getPricesOfPackage(@PathParam("packageId") Integer packageId) {
        try {
            List<PackagePrice> packagePrices = packagePriceController.getPackagePricesOfPackage(packageId);
            return Response.ok().entity(gson.toJson(packagePrices)).build();
        } catch (PackagePriceException | ServicePackageException e) {
            return Response.status(204).entity("{}").build();
        }
    }

    @POST
    @Path("/create")
    @Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @Consumes(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @RolesAllowed({"administrator"})
    public Response createPackagePrice(PackagePrice packagePrice){
        try {
            packagePriceController.createPackagePrice(packagePrice);
            return Response.ok().entity("{\"success\":1}").build();
        } catch (PackagePriceException e) {
            return Response.status(400).entity("{\"success\":0}").build();
        }
    }

    @POST
    @Path("/update")
    @Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @Consumes(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @RolesAllowed({"administrator"})
    public Response updatePackagePrice(PackagePrice packagePrice){
        try {
            packagePriceController.updatePackagePrice(packagePrice);
            return Response.ok().entity("{\"success\":1}").build();
        } catch (PackagePriceException e) {
            return Response.status(400).entity("{\"success\":0}").build();
        }
    }

    @POST
    @Path("/delete/{packagePriceId:[0-9]+}")
    @Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @Consumes(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @RolesAllowed({"administrator"})
    public Response deletePackagePrice(@PathParam("packagePriceId") Integer packagePriceId){
        try {
            packagePriceController.deletePackagePrice(packagePriceId);
            return Response.ok().entity("{\"success\":1}").build();
        } catch (PackagePriceException e) {
            return Response.status(400).entity("{\"success\":0}").build();
        }
    }
}
