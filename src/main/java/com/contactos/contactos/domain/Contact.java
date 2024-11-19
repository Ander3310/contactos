package com.contactos.contactos.domain;

import com.contactos.contactos.domain.excepciones.ContactosExcep;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Contact {
    private Long id;
    private String nombre;
    private String email;
    private String telefono;
    private String direccion;


    private Contact(Builder builder) {
        this.id = builder.id;
        this.nombre = builder.nombre;
        this.email = builder.email;
        this.telefono = builder.telefono;
        this.direccion = builder.direccion;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }


    public static Contact createContact(String nombre, String email, String telefono, String direccion) throws ContactosExcep {

        if (nombre == null || nombre.isEmpty()) {
            throw new ContactosExcep("El nombre no puede estar vacío");
        }
        if (email == null || email.isEmpty()) {
            throw new ContactosExcep("El correo electrónico no puede estar vacío");
        }
        if (telefono == null || telefono.isEmpty()) {
            throw new ContactosExcep("El teléfono no puede estar vacío");
        }
        if (direccion == null || direccion.isEmpty()) {
            throw new ContactosExcep("La dirección no puede estar vacía");
        }


        return new Builder()
                .setNombre(nombre)
                .setEmail(email)
                .setTelefono(telefono)
                .setDireccion(direccion)
                .build();
    }


    @JsonCreator
    public static Contact fromJson(@JsonProperty("id") Long id,
                                   @JsonProperty("nombre") String nombre,
                                   @JsonProperty("email") String email,
                                   @JsonProperty("telefono") String telefono,
                                   @JsonProperty("direccion") String direccion) {

        return new Builder()
                .setId(id)
                .setNombre(nombre)
                .setEmail(email)
                .setTelefono(telefono)
                .setDireccion(direccion)
                .build();
    }


    public static class Builder {
        private Long id;
        private String nombre;
        private String email;
        private String telefono;
        private String direccion;


        public Builder() {}

        public Builder setId(Long id) {
            this.id = id;
            return this;
        }

        public Builder setNombre(String nombre) {
            this.nombre = nombre;
            return this;
        }

        public Builder setEmail(String email) {
            this.email = email;
            return this;
        }

        public Builder setTelefono(String telefono) {
            this.telefono = telefono;
            return this;
        }

        public Builder setDireccion(String direccion) {
            this.direccion = direccion;
            return this;
        }


        public Contact build() throws ContactosExcep {

            if (nombre == null || nombre.isEmpty()) {
                throw new ContactosExcep("El nombre no puede estar vacío");
            }
            if (email == null || email.isEmpty()) {
                throw new ContactosExcep("El correo electrónico no puede estar vacío");
            }
            if (telefono == null || telefono.isEmpty()) {
                throw new ContactosExcep("El teléfono no puede estar vacío");
            }
            if (direccion == null || direccion.isEmpty()) {
                throw new ContactosExcep("La dirección no puede estar vacía");
            }

            return new Contact(this);
        }
    }
}
