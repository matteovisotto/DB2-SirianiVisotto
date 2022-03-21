package it.polimi.db2.telco.servlet;

import it.polimi.db2.telco.beans.PendingOrderBean;
import it.polimi.db2.telco.controllers.OrderController;
import it.polimi.db2.telco.controllers.ServicePackageController;
import it.polimi.db2.telco.entities.Order;
import it.polimi.db2.telco.entities.User;
import it.polimi.db2.telco.services.OrderService;
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
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@WebServlet("/order/create")
public class CreateOrderServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private TemplateEngine templateEngine;
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
        String path = "templates/buyService";
        ServletContext servletContext = getServletContext();
        final WebContext ctx = new WebContext(req, resp, servletContext, req.getLocale());
        if(req.getSession().getAttribute("user")!=null){
            ctx.setVariable("user", req.getSession().getAttribute("user"));
        }
        ctx.setVariable("packages", servicePackageController.getAllServicePackages());
        templateEngine.process(path, ctx, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer packageId = Integer.parseInt(req.getParameter("package"));
        Integer validityPeriod = Integer.parseInt(req.getParameter("validityPeriod"));
        Date startDate = new Date();
        try {
            startDate = new SimpleDateFormat("yyyy-MM-dd").parse((String) req.getParameter("startDate"));
        } catch (ParseException e) {}

        String[] optionalIdsStr = req.getParameterValues("optionalProduct");
        List<Integer> optionalsIds = new ArrayList<>();
        for (int i = 0; i < optionalIdsStr.length; i++) {
            optionalsIds.add(Integer.parseInt(optionalIdsStr[i]));
        }

        PendingOrderBean pendingOrderBean = new PendingOrderBean(0, packageId, validityPeriod, startDate, optionalsIds);
        req.getSession().setAttribute("pendingOrder", pendingOrderBean);
        resp.sendRedirect(getServletContext()+"/order/confirm");
    }
}