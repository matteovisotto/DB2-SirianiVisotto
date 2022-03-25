package it.polimi.db2.telco.servlet;

import it.polimi.db2.telco.controllers.OptionalProductController;
import it.polimi.db2.telco.controllers.ServiceController;
import it.polimi.db2.telco.controllers.ServicePackageController;
import it.polimi.db2.telco.entities.OptionalProduct;
import it.polimi.db2.telco.entities.PackagePrice;
import it.polimi.db2.telco.entities.Service;
import it.polimi.db2.telco.entities.ServicePackage;
import it.polimi.db2.telco.exceptions.optionalProduct.OptionalProductException;
import it.polimi.db2.telco.exceptions.service.ServiceException;
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

    @Inject
    ServiceController serviceController;

    @Inject
    OptionalProductController optionalProductController;

    @Override
    public void init() throws ServletException {}

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("p_name");
        String temp = name.replaceAll("\\s+","");
        if (temp.equals("")) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid service package name");
            return;
        }
        String[] services = req.getParameterValues("p_services");
        if (services == null || services.length == 0) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "At least one service must be selected");
            return;
        }
        List<Service> selectedServices = new ArrayList<>();
        try {
            for (int i = 0; i < services.length; i++) {
                Service service = serviceController.getServiceById(Integer.parseInt(services[i]));
                selectedServices.add(service);
            }
        } catch (NumberFormatException e) {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid data format");
            return;
        } catch (ServiceException s){
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid data");
            return;
        }

        ServicePackage servicePackage = new ServicePackage();
        servicePackage.setName(name);
        servicePackage.setServices(selectedServices);

        String[] optionalIdsStr = req.getParameterValues("p_optionals");
        if (optionalIdsStr == null) {
            optionalIdsStr = new String[]{};
        }
        List<OptionalProduct> selectedOptionals = new ArrayList<>();
        try {
            for (int i = 0; i < optionalIdsStr.length; i++) {
                OptionalProduct optionalProduct = optionalProductController.getOptionalProductById(Integer.parseInt(optionalIdsStr[i]));
                selectedOptionals.add(optionalProduct);
            }
        } catch (NumberFormatException e){
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid data format");
            return;
        } catch (OptionalProductException o){
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid data");
            return;
        }
        servicePackage.setOptionalProducts(selectedOptionals);

        Double twelveMonth = 0.0;
        Double twentyFourMonth = 0.0;
        Double thirtySixMonth = 0.0;

        try {
            twelveMonth = Double.parseDouble(req.getParameter("p_p_12"));
            twentyFourMonth = Double.parseDouble(req.getParameter("p_p_24"));
            thirtySixMonth = Double.parseDouble(req.getParameter("p_p_36"));
        } catch (NumberFormatException e){
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid data format");
            return;
        }

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
