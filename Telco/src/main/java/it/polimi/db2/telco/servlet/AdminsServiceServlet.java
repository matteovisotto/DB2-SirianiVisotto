package it.polimi.db2.telco.servlet;

import it.polimi.db2.telco.controllers.ServiceController;
import it.polimi.db2.telco.entities.Service;
import it.polimi.db2.telco.exceptions.service.ServiceException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/service/post")
public class AdminsServiceServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    @Inject
    ServiceController serviceController;

    @Override
    public void init() throws ServletException {}

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("s_name");
        Integer min = Integer.parseInt(req.getParameter("s_min"));
        Integer sms = Integer.parseInt(req.getParameter("s_sms"));
        Integer internet = Integer.parseInt(req.getParameter("s_internet"));
        Double extraMin = Double.parseDouble(req.getParameter("s_min_e"));
        Double extraSms = Double.parseDouble(req.getParameter("s_sms_e"));
        Double extraInternet = Double.parseDouble(req.getParameter("s_internet_e"));
        Service service = new Service();
        service.setName(name);
        service.setMin(min);
        service.setSms(sms);
        service.setInternet(internet);
        service.setExtraMin(extraMin);
        service.setExtraSms(extraSms);
        service.setExtraInternet(extraInternet);
        try {
            serviceController.createService(service);
        } catch (ServiceException e) {
            throw new ServiceException();
        }
        resp.sendRedirect(getServletContext().getContextPath() + "/admin");
    }
}
