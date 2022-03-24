package it.polimi.db2.telco.servlet;

import it.polimi.db2.telco.controllers.ServicePackageController;
import it.polimi.db2.telco.entities.PackagePrice;
import it.polimi.db2.telco.entities.ServicePackage;
import it.polimi.db2.telco.exceptions.servicePackage.ServicePackageException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@WebServlet("/admin/package/post")
public class AdminsServicePackageServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    @Inject
    ServicePackageController servicePackageController;

    @Override
    public void init() throws ServletException {}

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("p_name");
        String[] services = req.getParameterValues("p_services");
        if (services == null) {
            services = new String[]{};
        }
        List<Integer> serviceIds = new ArrayList<>();
        for (int i = 0; i < services.length; i++) {
            serviceIds.add(Integer.parseInt(services[i]));
        }
        ServicePackage servicePackage = new ServicePackage();
        servicePackage.setName(name);
        //servicePackage.setServices(serviceIds);
        String[] optionalIdsStr = req.getParameterValues("p_optionals");
        if (optionalIdsStr == null) {
            optionalIdsStr = new String[]{};
        }
        List<Integer> optionalsIds = new ArrayList<>();
        for (int i = 0; i < optionalIdsStr.length; i++) {
            optionalsIds.add(Integer.parseInt(optionalIdsStr[i]));
        }
        //servicePackage.setOptionalProducts(optionalIdsStr);
        Double twelveMonth = Double.parseDouble(req.getParameter("p_p_12"));
        Double twentyFourMonth = Double.parseDouble(req.getParameter("p_p_24"));
        Double thirtySixMonth = Double.parseDouble(req.getParameter("p_p_36"));
        PackagePrice twelve = new PackagePrice();
        twelve.setPrice(twelveMonth);
        twelve.setValidityPeriod(12);
        PackagePrice twentyFour = new PackagePrice();
        twentyFour.setPrice(twentyFourMonth);
        twentyFour.setValidityPeriod(24);
        PackagePrice thirtySix = new PackagePrice();
        thirtySix.setPrice(thirtySixMonth);
        thirtySix.setValidityPeriod(36);
        List<PackagePrice> prices = new ArrayList<>();
        prices.add(twelve);
        prices.add(twentyFour);
        prices.add(thirtySix);
        servicePackage.setPackagePrices(prices);
        try {
            servicePackageController.createServicePackage(servicePackage);
        } catch (ServicePackageException e) {
            throw new ServicePackageException();
        }
        resp.sendRedirect(getServletContext().getContextPath() + "/admin");
    }
}
