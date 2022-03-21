package it.polimi.db2.telco.servlet;

import it.polimi.db2.telco.beans.PendingOrderBean;
import it.polimi.db2.telco.controllers.OrderController;
import it.polimi.db2.telco.controllers.PaymentController;
import it.polimi.db2.telco.controllers.ServicePackageController;
import it.polimi.db2.telco.entities.*;
import it.polimi.db2.telco.exceptions.order.OrderNotFoundException;
import it.polimi.db2.telco.exceptions.payment.PaymentException;
import org.eclipse.persistence.jpa.jpql.parser.DateTime;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;

@WebServlet("/order/pay")
public class PaymentServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private TemplateEngine templateEngine;

    @Inject
    PaymentController paymentController;
    @Inject
    OrderController orderController;

    @Override
    public void init() throws ServletException {
        ServletContextTemplateResolver templateResolver = new ServletContextTemplateResolver(getServletContext());
        templateResolver.setTemplateMode(TemplateMode.HTML);
        this.templateEngine = new TemplateEngine();
        this.templateEngine.setTemplateResolver(templateResolver);
        templateResolver.setSuffix(".html");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = "templates/paymentPage";
        ServletContext servletContext = getServletContext();
        final WebContext ctx = new WebContext(req, resp, servletContext, req.getLocale());
        User user = null;
        if(req.getSession().getAttribute("user")==null){
            resp.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }
        user = (User) req.getSession().getAttribute("user");
        ctx.setVariable("user", user);
        Order order = null;
        if(req.getParameter("orderId") == null){
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        Integer orderId = Integer.parseInt(req.getParameter("orderId"));
        try {
            order = orderController.getOrderById(orderId);
            ctx.setVariable("order", order);
            if(!order.getUser().equals(user)){
               resp.sendError(HttpServletResponse.SC_UNAUTHORIZED, "You have to be logged with your account in to see your order");
               return;
            }
            if(order.getOrderStatus() == 1){
                 resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "This order is completed");
                 return;
            }

        } catch (OrderNotFoundException e){
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Order not found");
            return;
        }

        templateEngine.process(path, ctx, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = "templates/paymentResult";
        ServletContext servletContext = getServletContext();
        final WebContext ctx = new WebContext(req, resp, servletContext, req.getLocale());
        User user = null;
        if(req.getSession().getAttribute("user")==null){
            resp.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }
        user = (User) req.getSession().getAttribute("user");
        ctx.setVariable("user", user);
        Order order = null;
        if(req.getParameter("orderId") == null){
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }
        int paymentStatus = 0;
        Integer orderId = Integer.parseInt(req.getParameter("orderId"));
        try {
            order = orderController.getOrderById(orderId);
            String pMethod = req.getParameter("pMethod");

            if(pMethod != null && pMethod.equals("card")){
                paymentStatus = 1;
            } else {
                paymentStatus = 0;
            }
            if(!order.getUser().equals(user)){
                resp.sendError(HttpServletResponse.SC_UNAUTHORIZED, "You have to be logged with your account in to see your order");
                return;
            }
            if(order.getOrderStatus() == 1){
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "This order is completed");
                return;
            }

            PaymentHistory paymentHistory = new PaymentHistory();
            paymentHistory.setPaymentStatus(paymentStatus);
            paymentHistory.setUser(user);
            paymentHistory.setOrder(order);
            paymentController.makeNewPayment(paymentHistory);

        } catch (OrderNotFoundException e){
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Order not found");
            return;
        } catch (PaymentException p){
            paymentStatus = 0;
            return;
        }
        ctx.setVariable("success", paymentStatus);
        templateEngine.process(path, ctx, resp.getWriter());
    }


}