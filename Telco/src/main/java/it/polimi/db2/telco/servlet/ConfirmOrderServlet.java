package it.polimi.db2.telco.servlet;

import it.polimi.db2.telco.beans.PendingOrderBean;
import it.polimi.db2.telco.controllers.OrderController;
import it.polimi.db2.telco.controllers.ServicePackageController;
import it.polimi.db2.telco.entities.OptionalProduct;
import it.polimi.db2.telco.entities.Order;
import it.polimi.db2.telco.entities.ServicePackage;
import it.polimi.db2.telco.entities.User;
import it.polimi.db2.telco.exceptions.order.OrderNotFoundException;
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
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;

@WebServlet("/order/confirm")
public class ConfirmOrderServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private TemplateEngine templateEngine;

    @Inject
    OrderController orderController;
    @Inject
    ServicePackageController servicePackageController;

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
        String path = "templates/confirmationPage";
        ServletContext servletContext = getServletContext();
        final WebContext ctx = new WebContext(req, resp, servletContext, req.getLocale());
        User user = null;
        if(req.getSession().getAttribute("user")!=null){
            user = (User) req.getSession().getAttribute("user");
            ctx.setVariable("user", user);
        }

        Order order = null;
        if(req.getParameter("orderId") != null) {
            Integer orderId = Integer.parseInt(req.getParameter("orderId"));
            try {
                order = orderController.getOrderById(orderId);
                if(!order.getUser().equals(user)){
                    resp.sendError(HttpServletResponse.SC_UNAUTHORIZED, "You have to be logged with your account in to see your order");
                    return;
                }
                if(order.getOrderStatus() != 3){
                    resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "This order is completed or not confirmed");
                    return;
                }
                //Here I have a rejected order loaded from the DB
                ctx.setVariable("inSession", "0");
            } catch (OrderNotFoundException e){
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Order not found");
                return;
            }
        } else if (req.getSession().getAttribute("pendingOrder") != null){
            PendingOrderBean pendingOrderBean = (PendingOrderBean) req.getSession().getAttribute("pendingOrder");
            //Here I have a new order in the server session not already saved
            order = pendingToOrder(pendingOrderBean);
            ctx.setVariable("inSession", "1");
        } else {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }
        ctx.setVariable("pendingOrder", order);
        templateEngine.process(path, ctx, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //SAVE THE ORDER IF IN SESSION ELSE UPDATE IN DB THEN REDIRECT TO THE PAYMENT PAGE GIVING THE ORDER ID
        if(req.getSession().getAttribute("user") == null){
            resp.sendError(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }
        User user = (User) req.getSession().getAttribute("user");
        if(req.getParameter("inSession") == null){
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }
        String inSession = req.getParameter("inSession");
        Integer orderId = 0;
        if(inSession.equals("1")){
            //Save the order in the db and get the order id to redirect to the payment page
            Order order = pendingToOrder((PendingOrderBean) req.getSession().getAttribute("pendingOrder"));
            order.setUser(user);
            order.setOrderStatus(0);
            orderId = orderController.createOrder(order);

        } else {
            //The order is already in the db
            if(req.getParameter("orderId") == null) {
                resp.sendError(HttpServletResponse.SC_BAD_REQUEST);
                return;
            }
            orderId = Integer.parseInt(req.getParameter("orderId"));
            Order order = orderController.getOrderById(orderId);
            if(!order.getUser().equals(user)){
                resp.sendError(HttpServletResponse.SC_UNAUTHORIZED);
                return;
            }
            //Just control if the user can proceed, no further actions required
        }

        resp.sendRedirect(getServletContext().getContextPath() + "/order/pay?orderId="+orderId);
    }

    private Order pendingToOrder(PendingOrderBean pendingOrderBean) {
        Order order = new Order();
        ServicePackage servicePackage = servicePackageController.getServicePackageById(pendingOrderBean.getPackageId());
        order.set_package(servicePackage);
        List<OptionalProduct> optionalProductList = new ArrayList<>();
        servicePackage.getOptionalProducts().forEach(o -> {
            pendingOrderBean.getOptionalProducts().forEach(i -> {
                if(i.equals(o.getId())){
                    optionalProductList.add(o);
                }
            });
        });
        order.setStartDate(pendingOrderBean.getStartDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        order.setOptionalProducts(optionalProductList);
        order.setValidityPeriod(pendingOrderBean.getValidityPeriod());
        order.setPrice(calculatePrice(order));


        return order;
    }

    private Double calculatePrice(Order o) {
        AtomicReference<Double> tot = new AtomicReference<>(0.0);
        o.get_package().getPackagePrices().forEach(p -> {
            if(Objects.equals(p.getValidityPeriod(), o.getValidityPeriod())){
                tot.set(p.getPrice());
            }
        });
        o.getOptionalProducts().forEach(opt -> {
            tot.set(tot.get() + opt.getPrice());
        });
        tot.set(tot.get()*o.getValidityPeriod());
        return tot.get();
    }
}