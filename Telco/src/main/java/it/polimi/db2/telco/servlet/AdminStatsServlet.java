package it.polimi.db2.telco.servlet;

import it.polimi.db2.telco.controllers.*;
import it.polimi.db2.telco.entities.OptionalProduct;
import it.polimi.db2.telco.entities.TotalPurchaseOptional;
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
import java.util.Comparator;
import java.util.stream.Collectors;

@WebServlet("/admin/stats")
public class AdminStatsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private TemplateEngine templateEngine;
    @Inject
    AveragePurchaseOptionalPackageController averagePurchaseOptionalPackageController;
    @Inject
    TotalPurchaseOptionalController totalPurchaseOptionalController;
    @Inject
    TotalPurchasePackageController totalPurchasePackageController;
    @Inject
    TotalPurchasePackageOptionalController totalPurchasePackageOptionalController;
    @Inject
    TotalPurchasePackageValidityController totalPurchasePackageValidityController;
    @Inject
    AlertController alertController;
    @Inject
    InsolventUserController insolventUserController;
    @Inject
    SuspendedOrderController suspendedOrderController;

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
        String path = "templates/adminStats";
        ServletContext servletContext = getServletContext();
        final WebContext ctx = new WebContext(req, resp, servletContext, req.getLocale());
        ctx.setVariable("user", req.getSession().getAttribute("administrator"));
        ctx.setVariable("averagePurchaseOptionalPackage", averagePurchaseOptionalPackageController.getAllAveragePurchaseOptionalPackage());
        ctx.setVariable("totalPurchaseOptional", totalPurchaseOptionalController.getAllTotalPurchaseOptionals());
        ctx.setVariable("totalPurchasePackage", totalPurchasePackageController.getAllTotalPurchasePackages());
        ctx.setVariable("totalPurchasePackageOptional", totalPurchasePackageOptionalController.getAllTotalPurchasePackageOptionals());
        ctx.setVariable("totalPurchasePackageValidity", totalPurchasePackageValidityController.getAllTotalPurchasePackageValidity());
        ctx.setVariable("alerts", alertController.getAllAlerts());
        ctx.setVariable("insolventUsers", insolventUserController.getAllInsolventUsers());
        ctx.setVariable("suspendedOrders", suspendedOrderController.getAllSuspendedOrders().stream().map(o -> suspendedOrderController.toBean(o)).collect(Collectors.toList()));
        TotalPurchaseOptional best = totalPurchaseOptionalController.getAllTotalPurchaseOptionals().stream().max(Comparator.comparing(TotalPurchaseOptional::getTotPurchase)).get();
        ctx.setVariable("bestOptional", best);

        templateEngine.process(path, ctx, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
    }
}