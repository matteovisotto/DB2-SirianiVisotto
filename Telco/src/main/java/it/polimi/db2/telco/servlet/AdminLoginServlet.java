package it.polimi.db2.telco.servlet;

import it.polimi.db2.telco.controllers.AdministratorController;
import it.polimi.db2.telco.controllers.UserController;
import it.polimi.db2.telco.entities.Administrator;
import it.polimi.db2.telco.entities.User;
import it.polimi.db2.telco.exceptions.administrator.AdministratorException;
import it.polimi.db2.telco.exceptions.user.UserException;
import org.apache.commons.codec.digest.DigestUtils;
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

@WebServlet("/admin/login")
public class AdminLoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private TemplateEngine templateEngine;
    @Inject
    AdministratorController administratorController;

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
        String path = "templates/adminLogin";
        ServletContext servletContext = getServletContext();
        final WebContext ctx = new WebContext(req, resp, servletContext, req.getLocale());
        templateEngine.process(path, ctx, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("j_username");
        String password = req.getParameter("j_password");
        String sha256hex = DigestUtils.sha256Hex(password);
        Administrator administrator = null;
        try {
            administrator = administratorController.loginAdministrator(username, sha256hex);

        } catch (AdministratorException e) {
            resp.sendRedirect(getServletContext().getContextPath()+"/admin/login?evn=error&err=bad_credential");
            return;
        }

        req.getSession().setAttribute("administrator", administrator);
        resp.sendRedirect(getServletContext().getContextPath() + "/admin");
    }

}
