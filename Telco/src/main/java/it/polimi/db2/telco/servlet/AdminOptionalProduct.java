package it.polimi.db2.telco.servlet;

import it.polimi.db2.telco.controllers.OptionalProductController;
import it.polimi.db2.telco.entities.OptionalProduct;
import it.polimi.db2.telco.exceptions.optionalProduct.OptionalProductException;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/optional/post")
public class AdminOptionalProduct extends HttpServlet {
    private static final long serialVersionUID = 1L;
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
        String name = req.getParameter("o_name");
        Double price = Double.parseDouble(req.getParameter("o_price"));
        OptionalProduct optionalProduct = new OptionalProduct();
        optionalProduct.setName(name);
        optionalProduct.setPrice(price);
        try {
            optionalProductController.createOptionalProduct(optionalProduct);
        } catch (OptionalProductException e) {
            throw new OptionalProductException();
        }
        resp.sendRedirect(getServletContext().getContextPath() + "/admin");
    }
}