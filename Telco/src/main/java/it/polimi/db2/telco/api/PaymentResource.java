package it.polimi.db2.telco.api;

import com.google.gson.Gson;
import it.polimi.db2.telco.controllers.PaymentController;
import it.polimi.db2.telco.entities.PaymentHistory;
import it.polimi.db2.telco.entities.User;
import it.polimi.db2.telco.exceptions.order.OrderException;
import it.polimi.db2.telco.exceptions.payment.PaymentException;
import it.polimi.db2.telco.exceptions.user.UserException;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/payment")
public class PaymentResource {
    @Inject
    private PaymentController paymentController;
    private final Gson gson = new Gson();

    @Context
    HttpServletRequest request;

    @GET
    @Path("/{id: [0-9]+}")
    @Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @RolesAllowed({"administrator"})
    public Response getPaymentById(@PathParam("id") Integer id) {
        User user = (User) request.getSession().getAttribute("user");
        try {
            PaymentHistory payment = paymentController.getPaymentById(id, user);
            return Response.ok().entity(gson.toJson(payment)).build();
        } catch (PaymentException p) {
            return Response.status(204).entity("{}").build();
        }
    }

    @GET
    @Path("/my")
    @Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @RolesAllowed({"user"})
    public Response getMyPayments() {
        try {
            List<PaymentHistory> payments = paymentController.getPaymentOfHistoryOfUser(((User) request.getSession().getAttribute("user")).getId());
            return Response.ok().entity(gson.toJson(payments)).build();
        } catch (PaymentException | UserException e) {
            return Response.status(204).entity("[]").build();
        }
    }

    @GET
    @Path("/my/{orderId: [0-9]+}")
    @Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @RolesAllowed({"user"})
    public Response getPaymentsOfMyOrder(@PathParam("orderId") Integer orderId) {
        try {
            List<PaymentHistory> payments = paymentController.getPaymentOfHistoryOfMyOrder(orderId, ((User) request.getSession().getAttribute("user")).getId());
            return Response.ok().entity(gson.toJson(payments)).build();
        } catch (PaymentException | OrderException e) {
            return Response.status(204).entity("[]").build();
        }
    }

    @GET
    @Path("/order/{orderId: [0-9]+}")
    @Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @RolesAllowed({"administrator"})
    public Response getPaymentsOfOrder(@PathParam("orderId") Integer orderId) {
        try {
            List<PaymentHistory> payments = paymentController.getPaymentOfHistoryOfOrder(orderId);
            return Response.ok().entity(gson.toJson(payments)).build();
        } catch (PaymentException | OrderException e) {
            return Response.status(204).entity("[]").build();
        }
    }

    @POST
    @Path("/pay")
    @Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @Consumes(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    @RolesAllowed({"user"})
    public Response makeNewPayment(PaymentHistory paymentHistory){
        try {
            paymentController.makeNewPayment(paymentHistory);
            return Response.ok().entity("{\"success\":1}").build();
        } catch (PaymentException | OrderException e) {
            return Response.status(400).entity("{\"success\":0}").build();
        }
    }
}
