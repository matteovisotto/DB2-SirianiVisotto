package it.polimi.db2.telco.api;

import com.google.gson.Gson;
import it.polimi.db2.telco.beans.OptionalProductBean;
import it.polimi.db2.telco.controllers.ServicePackageController;
import it.polimi.db2.telco.exceptions.optionalProduct.OptionalProductException;

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

    Gson gson = new Gson();

    @Inject
    ServicePackageController servicePackageController;

    @Context
    HttpServletRequest request;

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
}
