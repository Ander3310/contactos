package com.contactos.contactos.config;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

@Provider
public class CORSFilter implements ContainerResponseFilter {

    @Override
    public void filter(final ContainerRequestContext requestContext,
                       final ContainerResponseContext cres) throws IOException {
        String origin = requestContext.getHeaderString("Origin");
        cres.getHeaders().add("Access-Control-Allow-Origin", "*");
        cres.getHeaders().add("Access-Control-Allow-Headers", "Origin, Content-Type,Accept,Authorization");
        cres.getHeaders().add("Access-Control-Allow-Credentials", "true");
        cres.getHeaders().add("Access-Control-Allow-Methods", "GET,POST,PUT,DELETE,OPTIONS,HEAD");

    }
}