package com.contactos.contactos.infrastructure.api;

import com.contactos.contactos.application.ContactService;
import com.contactos.contactos.domain.Contact;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Optional;

@Path("/contacts")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ContactResource {

    private final ContactService contactService;

    @Inject
    public ContactResource(ContactService contactService) {
        this.contactService = contactService;
    }


    @POST
    public Response saveOrUpdateContact(Contact contact) {
        try {

            Contact savedContact = contactService.saveOrUpdateContact(
                    contact.getId(),
                    contact.getNombre(),
                    contact.getEmail(),
                    contact.getTelefono(),
                    contact.getDireccion()
            );
            return Response.status(Response.Status.CREATED).entity(savedContact).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity("Error: " + e.getMessage()).build();
        }
    }


    @GET
    public List<Contact> getAllContacts() {
        return contactService.getAllContacts();
    }


    @GET
    @Path("/{id}")
    public Response getContactById(@PathParam("id") Long id) {
        Optional<Contact> contact = contactService.getContactById(id);
        if (contact.isPresent()) {
            return Response.ok(contact.get()).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Contact not found").build();
        }
    }

    @PUT
    @Path("/{id}")
    public Response updateContact(@PathParam("id") Long id, Contact contact) {
        try {

            contact.setId(id);
            contactService.saveOrUpdateContact(
                    id,
                    contact.getNombre(),
                    contact.getEmail(),
                    contact.getTelefono(),
                    contact.getDireccion()
            );
            return Response.ok().build();
        } catch (Exception e) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Contact with id " + id + " not found or error: " + e.getMessage()).build();
        }
    }


    @DELETE
    @Path("/{id}")
    public Response deleteContact(@PathParam("id") Long id) {
        boolean deleted = contactService.deleteContact(id);
        if (deleted) {
            return Response.status(Response.Status.NO_CONTENT).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("Contact with id " + id + " not found").build();
        }
    }
}
