package it.polimi.db2.telco.api;

import com.google.gson.Gson;
import it.polimi.db2.telco.controllers.AlertController;
import it.polimi.db2.telco.entities.Alert;
import it.polimi.db2.telco.entities.User;
import it.polimi.db2.telco.exceptions.alert.AlertException;
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

@Path("alert")
public class AlertResource {
    @Inject
    private AlertController alertController;
    private final Gson gson = new Gson();

    @Context
    HttpServletRequest request;

    @GET
    @Path("/{id: [0-9]+}")
    @Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @RolesAllowed({"user", "administrator"})
    public Response getAlertById(@PathParam("id") Integer id) {
        try {
            Alert alert = alertController.getAlertById(id);
            return Response.ok().entity(gson.toJson(alert)).build();
        } catch (AlertException e) {
            return Response.status(204).entity("{}").build();
        }
    }

    @GET
    @Path("/user/{userId: [0-9]+}")
    @Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @RolesAllowed({"administrator"})
    public Response getAlertsByUser(@PathParam("userId") Integer userId) {
        try {
            List<Alert> alerts = alertController.getAlertsByUser(userId);
            return Response.ok().entity(gson.toJson(alerts)).build();
        } catch (AlertException | UserException e) {
            return Response.status(204).entity("{}").build();
        }
    }

    @GET
    @Path("/my")
    @Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @RolesAllowed({"user"})
    public Response getMyAlerts() {
        try {
            List<Alert> alerts = alertController.getMyAlerts(((User) request.getSession().getAttribute("user")).getId());
            return Response.ok().entity(gson.toJson(alerts)).build();
        } catch (AlertException | UserException e) {
            return Response.status(204).entity("{}").build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @RolesAllowed({"administrator"})
    public Response getAllAlerts() {
        try {
            List<Alert> alerts = alertController.getAllAlerts();
            return Response.ok().entity(gson.toJson(alerts)).build();
        } catch (AlertException e) {
            return Response.status(204).entity("{}").build();
        }
    }
}
