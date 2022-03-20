package it.polimi.db2.telco.filters;


import javax.annotation.security.RolesAllowed;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.Provider;
import java.io.IOException;
import java.util.Arrays;


@Provider
public class ApiIncomingFilter implements ContainerRequestFilter {
    @Context ResourceInfo resourceInfo;
    @Context HttpServletRequest request;

    @Override
    public void filter(ContainerRequestContext containerRequestContext) throws IOException {

            String role = "visitor";
            HttpSession session = request.getSession();
            if (!session.isNew() && session.getAttribute("user") != null) { //A logged user exists
                role = "user";
            }

            if(!session.isNew() && session.getAttribute("administrator") != null){
                role = "administrator";
            }



        if(resourceInfo.getResourceMethod() == null || resourceInfo.getResourceMethod().getAnnotation(RolesAllowed.class) == null){
            return; //No security constraints are required
        }

        RolesAllowed rolesAllowed = resourceInfo.getResourceMethod().getAnnotation(RolesAllowed.class);

        if (!Arrays.asList(rolesAllowed.value()).contains(role)) {
            containerRequestContext.abortWith(Response.status(Response.Status.FORBIDDEN).build());
        }

    }
}
