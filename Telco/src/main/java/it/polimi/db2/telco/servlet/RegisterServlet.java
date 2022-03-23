package it.polimi.db2.telco.servlet;

import it.polimi.db2.telco.controllers.OrderController;
import it.polimi.db2.telco.controllers.ServicePackageController;
import it.polimi.db2.telco.controllers.UserController;
import it.polimi.db2.telco.entities.User;
import it.polimi.db2.telco.exceptions.user.UserEmailAlreadyExistingException;
import it.polimi.db2.telco.exceptions.user.UserException;
import it.polimi.db2.telco.exceptions.user.UserUsernameAlreadyExistingException;
import it.polimi.db2.telco.services.OrderService;
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
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Locale;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private TemplateEngine templateEngine;
    @Inject
    UserController userController;

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
        resp.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, UserUsernameAlreadyExistingException, UserEmailAlreadyExistingException {
        String name = req.getParameter("r_name");
        String surname = req.getParameter("r_surname");
        String username = req.getParameter("r_username");
        String email = req.getParameter("r_email");
        String password = req.getParameter("r_password");
        String passwordCheck = req.getParameter("r_password_c");
        if(!password.equals(passwordCheck)){
            resp.sendRedirect(getServletContext().getContextPath()+"/?evn=error&err=not_equal_password");
            return;
        }
        if (username.contains("@")){
            resp.sendRedirect(getServletContext().getContextPath()+"/?evn=error&err=at_exception");
            return;
        }
        if(userController.checkIfUsernameIsAlreadyUsed(username)) {
            resp.sendRedirect(getServletContext().getContextPath()+"/?evn=error&err=username_existing");
            return;
        }

        if(userController.checkIfEmailIsAlreadyUsed(email)){
            resp.sendRedirect(getServletContext().getContextPath()+"/?evn=error&err=email_existing");
            return;
        }


        String sha256hex = DigestUtils.sha256Hex(password);

        User userToRegister = new User();
        userToRegister.setUsername(username);
        userToRegister.setEmail(email.toLowerCase(Locale.ROOT));
        userToRegister.setName(name);
        userToRegister.setPassword(sha256hex);
        userToRegister.setSurname(surname);
        userToRegister.setInsolvent(0);
        userController.createUser(userToRegister);


        String redirectTo = getServletContext().getContextPath()+"/";
        if(req.getParameter("returnTo") != null){
            redirectTo = req.getParameter("returnTo");
        }
        resp.sendRedirect(redirectTo);
    }
}