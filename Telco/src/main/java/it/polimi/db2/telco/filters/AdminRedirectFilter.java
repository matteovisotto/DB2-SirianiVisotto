package it.polimi.db2.telco.filters;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Servlet Filter implementation class checker
 */

public class AdminRedirectFilter implements Filter {

    /**
     * Default constructor.
     */
    public AdminRedirectFilter() {
        // TODO Auto-generated constructor stub
    }

    /**
     * @see Filter#destroy()
     */
    public void destroy() {
        // TODO Auto-generated method stub
    }

    /**
     * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
     */
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        System.out.println(req.getRequestURI().toString());
        if(req.getRequestURI().toString().equals(req.getServletContext().getContextPath() + "/admin") || req.getRequestURI().toString().equals(req.getServletContext().getContextPath() +"/admin/")){
            res.sendRedirect(req.getServletContext().getContextPath() + "/admin/dashboard");
            return;
        }
        // pass the request along the filter chain
        chain.doFilter(request, response);
    }

    /**
     * @see Filter#init(FilterConfig)
     */
    public void init(FilterConfig fConfig) throws ServletException {
        // TODO Auto-generated method stub
    }

}
