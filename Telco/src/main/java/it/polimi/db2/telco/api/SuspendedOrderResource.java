/*
package it.polimi.db2.telco.api;

import com.google.gson.Gson;
import it.polimi.db2.telco.controllers.SuspendedOrderController;
import it.polimi.db2.telco.entities.SuspendedOrder;
import it.polimi.db2.telco.entities.User;
import it.polimi.db2.telco.exceptions.suspendedOrder.SuspendedOrderException;

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

@Path("/suspended")
public class SuspendedOrderResource {
    @Inject
    private SuspendedOrderController suspendedOrderController;
    private final Gson gson = new Gson();

    @Context
    HttpServletRequest request;

    @GET
    @Path("/{id: [0-9]+}")
    @Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @RolesAllowed({"administrator"})
    public Response getSuspendedOrderByOrderIdAndUserId(@PathParam("id") Integer orderId, Integer userId) {
        try {
            SuspendedOrder suspendedOrder = suspendedOrderController.getSuspendedOrderByOrderIdAndUserId(orderId, userId);
            return Response.ok().entity(gson.toJson(suspendedOrder)).build();
        } catch (SuspendedOrderException e) {
            return Response.status(204).entity("{}").build();
        }
    }

    @GET
    @Path("/my")
    @Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @RolesAllowed({"user"})
    public Response getMySuspendedOrders() {
        try {
            List<SuspendedOrder> suspendedOrders = suspendedOrderController.getMySuspendedOrders(((User) request.getSession().getAttribute("user")).getId());
            return Response.ok().entity(gson.toJson(suspendedOrders)).build();
        } catch (SuspendedOrderException e) {
            return Response.status(204).entity("{}").build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @RolesAllowed({"administrator"})
    public Response getAllSuspendedOrders() {
        try {
            List<SuspendedOrder> suspendedOrders = suspendedOrderController.getAllSuspendedOrders();
            return Response.ok().entity(gson.toJson(suspendedOrders)).build();
        } catch (SuspendedOrderException e) {
            return Response.status(204).entity("{}").build();
        }
    }
}
*/
