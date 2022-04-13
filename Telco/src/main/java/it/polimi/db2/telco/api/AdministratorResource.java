/*
package it.polimi.db2.telco.api;

import com.google.gson.Gson;
import it.polimi.db2.telco.controllers.AdministratorController;
import it.polimi.db2.telco.entities.Administrator;
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

@Path("/administrator")
public class AdministratorResource {
    @Inject
    private AdministratorController administratorController;
    private final Gson gson = new Gson();

    @Context
    HttpServletRequest request;

    @GET
    @Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @RolesAllowed("administrator")
    public Response getAdministrator() {
        Administrator administrator = (Administrator) request.getSession().getAttribute("administrator");
        return Response.ok().entity(gson.toJson(administrator)).build();
    }

    @GET
    @Path("/{uid: [0-9]+}")
    @Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @RolesAllowed("administrator")
    public Response getAdministratorById(@PathParam("uid") Integer uid) {
        try {
            Administrator administrator = administratorController.getAdministratorById(uid);
            return Response.ok().entity(gson.toJson(administrator)).build();
        } catch (UserException e) {
            return Response.status(204).entity("{}").build();
        }
    }

    @GET
    @Path("/{mail}")
    @Produces("application/json")
    @RolesAllowed("administrator")
    public Response getAdministratorByEmail(@PathParam("mail") String mail) {
        try {
            Administrator administrator = administratorController.getAdministratorByEmail(mail);
            return Response.ok().entity(gson.toJson(administrator)).build();
        } catch (UserException e) {
            return Response.status(204).entity("{}").build();
        }
    }

    @GET
    @Path("role/{role}")
    @Produces("application/json")
    @RolesAllowed("administrator")
    public Response getAdministratorsByRole(@PathParam("role") String role) {
        try {
            List<Administrator> administrator = administratorController.getAdministratorsByRole(role);
            return Response.ok().entity(gson.toJson(administrator)).build();
        } catch (UserException e) {
            return Response.status(204).entity("{}").build();
        }
    }
}
*/
