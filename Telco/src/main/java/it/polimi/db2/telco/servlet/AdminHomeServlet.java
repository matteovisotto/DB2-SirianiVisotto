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
        List<ServicePackage> servicePackages = servicePackageController.getAllServicePackages();
        List<OptionalProduct> optionalProducts = optionalProductController.getAllOptionalProducts();
        List<Service> services = serviceController.getAllServices();

        ctx.setVariable("user", req.getSession().getAttribute("administrator"));
        ctx.setVariable("servicePackage", req.getSession().getAttribute("servicePackage"));
        ctx.setVariable("service", req.getSession().getAttribute("service"));
        ctx.setVariable("optionalProduct", req.getSession().getAttribute("optionalProduct"));

        ServicePackage servicePackage = new ServicePackage();
        Service service = new Service();
        service.setId(1);
        List<Service> services1 = new ArrayList<>();
        services1.add(service);
        servicePackage.setServices(services1);
        PackagePrice p1 = new PackagePrice();
        p1.setValidityPeriod(12);
        p1.setPrice(12.5);
        PackagePrice p2 = new PackagePrice();
        p2.setValidityPeriod(24);
        p2.setPrice(24.5);
        List<PackagePrice> packagePrices = new ArrayList<>();
        packagePrices.add(p1);
        packagePrices.add(p2);
        servicePackage.setPackagePrices(packagePrices);
        servicePackage.setName("Proviamo");
        servicePackageController.createServicePackage(servicePackage);
        templateEngine.process(path, ctx, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
    }
}