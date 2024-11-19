package com.contactos.contactos.application;

import com.contactos.contactos.domain.Contact;
import com.contactos.contactos.domain.excepciones.ContactosExcep;
import com.contactos.contactos.infrastructure.persistence.ContactRepository;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

public class ContactService {

    private final ContactRepository contactRepository;

    @Inject
    public ContactService(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }


    public Contact saveOrUpdateContact(Long id, String nombre, String email, String telefono, String direccion) {

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


        Contact contact = new Contact.Builder()
                .setId(id)
                .setNombre(nombre)
                .setEmail(email)
                .setTelefono(telefono)
                .setDireccion(direccion)
                .build();


        return saveOrUpdateContact(contact);
    }

    private Contact saveOrUpdateContact(Contact contact) {

        if (contact.getId() != null) {

            if (contactRepository.update(contact)) {
                return contact;
            } else {
                throw new RuntimeException("Error al actualizar el contacto");
            }
        } else {

            return contactRepository.save(contact);
        }
    }

    public List<Contact> getAllContacts() {
        return contactRepository.findAll();
    }

    public Optional<Contact> getContactById(Long id) {
        return contactRepository.findById(id);
    }

    public boolean deleteContact(Long id) {
        return contactRepository.delete(id);
    }
}
