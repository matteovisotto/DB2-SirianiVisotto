/*
package it.polimi.db2.telco.api;

import com.google.gson.Gson;
import it.polimi.db2.telco.controllers.InsolventUserController;
import it.polimi.db2.telco.entities.InsolventUser;
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

public class InsolventUserResource {
    @Inject
    private InsolventUserController insolventUserController;
    private final Gson gson = new Gson();

    @Context
    HttpServletRequest request;

    @GET
    @Path("/{id: [0-9]+}")
    @Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @RolesAllowed({"administrator"})
    public Response getInsolventUserById(@PathParam("id") Integer id) {
        try {
            InsolventUser insolventUser = insolventUserController.getInsolventUserById(id);
            return Response.ok().entity(gson.toJson(insolventUser)).build();
        } catch (UserException e) {
            return Response.status(204).entity("{}").build();
        }
    }

    @GET
    @Path("username/{username}")
    @Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @RolesAllowed({"administrator"})
    public Response getInsolventUserByUsername(@PathParam("username") String username) {
        try {
            InsolventUser insolventUser = insolventUserController.getInsolventUserByUsername(username);
            return Response.ok().entity(gson.toJson(insolventUser)).build();
        } catch (UserException e) {
            return Response.status(204).entity("{}").build();
        }
    }

    @GET
    @Path("username/{email}")
    @Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @RolesAllowed({"administrator"})
    public Response getInsolventUserByEmail(@PathParam("email") String email) {
        try {
            InsolventUser insolventUser = insolventUserController.getInsolventUserByEmail(email);
            return Response.ok().entity(gson.toJson(insolventUser)).build();
        } catch (UserException e) {
            return Response.status(204).entity("{}").build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @RolesAllowed({"administrator"})
    public Response getAllInsolventUsers() {
        try {
            List<InsolventUser> insolventUsers = insolventUserController.getAllInsolventUsers();
            return Response.ok().entity(gson.toJson(insolventUsers)).build();
        } catch (UserException e) {
            return Response.status(204).entity("{}").build();
        }
    }
}
*/
