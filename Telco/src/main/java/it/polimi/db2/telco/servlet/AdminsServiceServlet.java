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
        String temp = name.replaceAll("\\s+","");
        if (temp.equals("")) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid service name");
            return;
        }
        Integer min = 0;
        Integer sms = 0;
        Integer internet = 0;
        Double extraMin = 0.0;
        Double extraSms = 0.0;
        Double extraInternet = 0.0;
        try {
            min = parseIntOrNull(req.getParameter("s_min"));
            sms = parseIntOrNull(req.getParameter("s_sms"));
            internet = parseIntOrNull(req.getParameter("s_internet"));
            extraMin = parseDoubleOrNull(req.getParameter("s_min_e"));
            extraSms = parseDoubleOrNull(req.getParameter("s_sms_e"));
            extraInternet = parseDoubleOrNull(req.getParameter("s_internet_e"));
        } catch (NumberFormatException e){
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid data format");
            return;
        }
        if(req.getParameter("serviceType") != null && req.getParameter("serviceType").equals("fixedPhone")){
            extraMin = 0.0;
            min = 50000;
        }
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

    private Double parseDoubleOrNull(String param) {
        if (param == null  || param.isEmpty()) {
            return null;
        }
        try {
            return Double.parseDouble(param);
        } catch (NumberFormatException ignored){
            return null;
        }
    }

    private Integer parseIntOrNull(String param) {
        if (param == null  || param.isEmpty()) {
            return null;
        }
        try {
            return Integer.parseInt(param);
        } catch (NumberFormatException ignored){
            return null;
        }
    }
}
