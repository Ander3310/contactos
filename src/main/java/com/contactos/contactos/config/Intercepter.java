package com.contactos.contactos.config;

import java.io.IOException;
import javax.servlet.ServletContext;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.core.Context;
import javax.ws.rs.ext.Provider;

@Provider
@PreMatching
public class Intercepter implements ContainerRequestFilter {

    @Context
    private ServletContext servletContext;

    @Override
    public void filter(ContainerRequestContext request) throws IOException {
        String url = request.getUriInfo().getAbsolutePath().toString();

        if (request.getMethod().equals("OPTIONS")) {
            return;
        }

        if (request.getMethod().equals("GET")) {
            return;
        }

        if (request.getMethod().equals("POST")) {
            return;
        }

        if (request.getMethod().equals("DELETE")) {
            return;
        }
    }

}