package com.contactos.contactos.infrastructure.api;

import javax.ws.rs.ApplicationPath;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import javax.inject.Singleton;

import com.contactos.contactos.infrastructure.persistence.ContactRepository;
import com.contactos.contactos.infrastructure.persistence.ContactRepositoryImpl;
import com.contactos.contactos.application.ContactService;
import com.mysql.cj.jdbc.MysqlDataSource;

@ApplicationPath("/api")
public class ApplicationConfig extends ResourceConfig {

    public ApplicationConfig() {

        packages("com.contactos.contactos.infrastructure.api");


        register(new AbstractBinder() {
            @Override
            protected void configure() {

                MysqlDataSource dataSource = new MysqlDataSource();
                dataSource.setUrl("jdbc:mysql://localhost:3306/contactos");
                dataSource.setUser("root");
                dataSource.setPassword("Admin");

                bind(dataSource)
                        .to(javax.sql.DataSource.class);


                // Bind Repository
                bind(ContactRepositoryImpl.class)
                        .to(ContactRepository.class)
                        .in(Singleton.class);

                // Bind Service
                bind(ContactService.class)
                        .to(ContactService.class)
                        .in(Singleton.class);
            }
        });
    }
}
