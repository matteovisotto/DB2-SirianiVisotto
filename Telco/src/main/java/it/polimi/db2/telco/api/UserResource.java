package it.polimi.db2.telco.api;

import com.google.gson.Gson;
import it.polimi.db2.telco.controllers.UserController;
import it.polimi.db2.telco.entities.User;
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

@Path("/user")
public class UserResource {
    @Inject
    private UserController userController;
    private final Gson gson = new Gson();

    @Context
    HttpServletRequest request;

    @GET
    @Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @RolesAllowed({"user", "administrator"})
    public Response getUser() {
        User user = (User) request.getSession().getAttribute("user");
        return Response.ok().entity(gson.toJson(user)).build();
    }

    @GET
    @Path("/{uid: [0-9]+}")
    @Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @RolesAllowed("administrator")
    public Response getUserById(@PathParam("uid") Integer uid) {
        try {
            User user = userController.getUserById(uid);
            return Response.ok().entity(gson.toJson(user)).build();
        } catch (UserException e) {
            return Response.status(204).entity("{}").build();
        }
    }

    @GET
    @Path("/mail/{mail}")
    @Produces("application/json")
    @RolesAllowed("administrator")
    public Response getUserByEmail(@PathParam("mail") String mail) {
        try {
            User user = userController.getUserByEmail(mail);
            return Response.ok().entity(gson.toJson(user)).build();
        } catch (UserException e) {
            return Response.status(204).entity("{}").build();
        }
    }

    @GET
    @Path("/username/{username}")
    @Produces("application/json")
    @RolesAllowed("administrator")
    public Response getUserByUsername(@PathParam("username") String username) {
        try {
            User user = userController.getUserByUsername(username);
            return Response.ok().entity(gson.toJson(user)).build();
        } catch (UserException e) {
            return Response.status(204).entity("{}").build();
        }
    }
}
