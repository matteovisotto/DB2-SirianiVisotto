/*
package it.polimi.db2.telco.api;

import com.google.gson.Gson;
import it.polimi.db2.telco.controllers.OrderController;
import it.polimi.db2.telco.entities.Order;
import it.polimi.db2.telco.entities.User;
import it.polimi.db2.telco.exceptions.order.OrderException;
import it.polimi.db2.telco.exceptions.user.UserException;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

public class OrderResource {
    @Inject
    private OrderController orderController;
    private final Gson gson = new Gson();

    @Context
    HttpServletRequest request;

    @GET
    @Path("/{id: [0-9]+}")
    @Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @RolesAllowed({"user", "administrator"})
    public Response getOrderById(@PathParam("id") Integer id) {
        try {
            Order order = orderController.getOrderById(id);
            return Response.ok().entity(gson.toJson(order)).build();
        } catch (OrderException e) {
            return Response.status(204).entity("{}").build();
        }
    }

    @GET
    @Path("/my/{orderId: [0-9]+}")
    @Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @RolesAllowed({"user"})
    public Response getMyOrderByOrderId(@PathParam("orderId") Integer orderId) {
        try {
            Order order = orderController.getMyOrderByOrderId(orderId, ((User) request.getSession().getAttribute("user")).getId());
            return Response.ok().entity(gson.toJson(order)).build();
        } catch (OrderException e) {
            return Response.status(204).entity("{}").build();
        }
    }

    @GET
    @Path("/my")
    @Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @RolesAllowed({"user"})
    public Response getAllMyOrders() {
        try {
            List<Order> orders = orderController.getOrdersOfUser(((User) request.getSession().getAttribute("user")).getId());
            return Response.ok().entity(gson.toJson(orders)).build();
        } catch (OrderException | UserException e) {
            return Response.status(204).entity("{}").build();
        }
    }

    @GET
    @Path("/{userId}")
    @Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @RolesAllowed({"administrator"})
    public Response getOrdersOfUser(@PathParam("userId") Integer userId) {
        try {
            List<Order> orders = orderController.getOrdersOfUser(userId);
            return Response.ok().entity(gson.toJson(orders)).build();
        } catch (OrderException | UserException e) {
            return Response.status(204).entity("{}").build();
        }
    }
}
*/
