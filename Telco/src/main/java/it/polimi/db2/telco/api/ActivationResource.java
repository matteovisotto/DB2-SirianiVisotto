/*
package it.polimi.db2.telco.api;

import com.google.gson.Gson;
import it.polimi.db2.telco.controllers.ActivationController;
import it.polimi.db2.telco.entities.Activation;
import it.polimi.db2.telco.entities.User;
import it.polimi.db2.telco.exceptions.activation.ActivationException;
import it.polimi.db2.telco.exceptions.user.UserException;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/activation")
public class ActivationResource {
    @Inject
    private ActivationController activationController;
    private final Gson gson = new Gson();

    @Context
    HttpServletRequest request;

    @GET
    @Path("/{id: [0-9]+}")
    @Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @RolesAllowed({"user", "administrator"})
    public Response getActivationByOrderId(@PathParam("id") Integer id) {
        try {
            Activation activation = activationController.getActivationByOrderId(id);
            return Response.ok().entity(gson.toJson(activation)).build();
        } catch (ActivationException e) {
            return Response.status(204).entity("{}").build();
        }
    }

    @GET
    @Path("/user/{userId: [0-9]+}")
    @Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @RolesAllowed({"administrator"})
    public Response getActivationsOfUser(@PathParam("userId") Integer userId) {
        try {
            List<Activation> activations = activationController.getActivationsOfUser(userId);
            return Response.ok().entity(gson.toJson(activations)).build();
        } catch (ActivationException | UserException e) {
            return Response.status(204).entity("{}").build();
        }
    }

    @GET
    @Path("/my")
    @Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @RolesAllowed({"user"})
    public Response getMyActivation() {
        try {
            List<Activation> activations = activationController.getActivationsOfUser(((User) request.getSession().getAttribute("user")).getId());
            return Response.ok().entity(gson.toJson(activations)).build();
        } catch (ActivationException | UserException e) {
            return Response.status(204).entity("{}").build();
        }
    }
}
*/
