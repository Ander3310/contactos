package com.contactos.contactos.config;

import java.util.Set;
import javax.ws.rs.core.Application;

@javax.ws.rs.ApplicationPath("api")
public class Config extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(CORSFilter.class);
        resources.add(Intercepter.class);
        resources.add(com.contactos.contactos.infrastructure.api.ContactResource.class);
    }

}