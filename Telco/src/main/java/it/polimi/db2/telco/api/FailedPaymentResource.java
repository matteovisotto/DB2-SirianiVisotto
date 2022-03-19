package it.polimi.db2.telco.api;

import com.google.gson.Gson;
import it.polimi.db2.telco.controllers.FailedPaymentController;
import it.polimi.db2.telco.entities.FailedPayment;
import it.polimi.db2.telco.entities.User;
import it.polimi.db2.telco.exceptions.payment.PaymentException;
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

@Path("/failed")
public class FailedPaymentResource {
    @Inject
    private FailedPaymentController failedPaymentController;
    private final Gson gson = new Gson();

    @Context
    HttpServletRequest request;

    @GET
    @Path("/{id: [0-9]+}")
    @Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @RolesAllowed({"user", "administrator"})
    public Response getFailedPaymentById(@PathParam("id") Integer userId) {
        try {
            FailedPayment failedPayment = failedPaymentController.getFailedPaymentByUserId(userId);
            return Response.ok().entity(gson.toJson(failedPayment)).build();
        } catch (PaymentException e) {
            return Response.status(204).entity("{}").build();
        }
    }

    @GET
    @Path("/my")
    @Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @RolesAllowed({"user"})
    public Response getMyFailedPayments() {
        try {
            List<FailedPayment> failedPayments = failedPaymentController.getFailedPaymentsOfUser(((User) request.getSession().getAttribute("user")).getId());
            return Response.ok().entity(gson.toJson(failedPayments)).build();
        } catch (PaymentException | UserException e) {
            return Response.status(204).entity("{}").build();
        }
    }

    @GET
    @Path("/user/{userId: [0-9]+}")
    @Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @RolesAllowed({"administrator"})
    public Response getFailedPaymentsPerUser(@PathParam("userId") Integer userId) {
        try {
            List<FailedPayment> failedPayments = failedPaymentController.getFailedPaymentsOfUser(userId);
            return Response.ok().entity(gson.toJson(failedPayments)).build();
        } catch (PaymentException | UserException e) {
            return Response.status(204).entity("{}").build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @RolesAllowed({"administrator"})
    public Response getAllFailedPayments() {
        try {
            List<FailedPayment> failedPayments = failedPaymentController.getAllFailedPaymentsPerUser();
            return Response.ok().entity(gson.toJson(failedPayments)).build();
        } catch (PaymentException e) {
            return Response.status(204).entity("{}").build();
        }
    }
}