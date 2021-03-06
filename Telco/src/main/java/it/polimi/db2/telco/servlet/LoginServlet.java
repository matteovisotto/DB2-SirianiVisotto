package it.polimi.db2.telco.servlet;

import it.polimi.db2.telco.controllers.UserController;
import it.polimi.db2.telco.entities.User;
import it.polimi.db2.telco.exceptions.user.UserException;
import it.polimi.db2.telco.exceptions.user.UserNotFoundException;
import org.apache.commons.codec.digest.DigestUtils;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Inject
    UserController userController;

    @Override
    public void init() throws ServletException {
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("j_username");
        String password = req.getParameter("j_password");
        String sha256hex = DigestUtils.sha256Hex(password);
        User user = null;
        try {
            if(username.contains("@")){
                user = userController.loginUserByEmail(username, sha256hex);
            } else {
                user = userController.loginUserByUsername(username, sha256hex);
            }
        } catch (UserException e) {
            String redirectTo = getServletContext().getContextPath() + "/";
            if(req.getParameter("returnTo") != null){
                redirectTo = req.getParameter("returnTo");
                if (redirectTo.contains("&evn")){
                    redirectTo = redirectTo.split("&evn")[0];
                } else if (redirectTo.contains("evn")) {
                    redirectTo = redirectTo.split("evn")[0];
                }
            }
            if (redirectTo.endsWith("?")){
                resp.sendRedirect(redirectTo + "evn=error&err=bad_credential");
            } else {
                resp.sendRedirect(redirectTo + "&evn=error&err=bad_credential");
            }
            return;
        }
        req.getSession().setAttribute("user", user);
        String redirectTo = getServletContext().getContextPath()+"/";
        if(req.getParameter("returnTo") != null){
            redirectTo = req.getParameter("returnTo").split("(&|\\?)evn")[0];
            if (redirectTo.endsWith("?")){
                redirectTo = redirectTo.split("\\?")[0];
            }
        }
        resp.sendRedirect(redirectTo);
    }

}
