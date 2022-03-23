package it.polimi.db2.telco.servlet;

import it.polimi.db2.telco.controllers.OptionalProductController;
import it.polimi.db2.telco.controllers.OrderController;
import it.polimi.db2.telco.controllers.ServiceController;
import it.polimi.db2.telco.controllers.ServicePackageController;
import it.polimi.db2.telco.entities.*;
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
import java.util.ArrayList;
import java.util.List;

@WebServlet("/admin/dashboard")
public class AdminHomeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private TemplateEngine templateEngine;
    @Inject
    ServicePackageController servicePackageController;
    @Inject
    OptionalProductController optionalProductController;
    @Inject
    ServiceController serviceController;

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
        String path = "templates/adminDashboard";
        ServletContext servletContext = getServletContext();
        final WebContext ctx = new WebContext(req, resp, servletContext, req.getLocale());


        ctx.setVariable("user", req.getSession().getAttribute("administrator"));
        ctx.setVariable("servicePackage", servicePackageController.getAllServicePackages());
        ctx.setVariable("service", serviceController.getAllServices());
        ctx.setVariable("optionalProduct", optionalProductController.getAllOptionalProducts());

        templateEngine.process(path, ctx, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
    }
}