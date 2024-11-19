package com.contactos.contactos.infrastructure.persistence;

import com.contactos.contactos.domain.Contact;
import java.util.List;
import java.util.Optional;

public interface ContactRepository {
    Contact save(Contact contact);

    Optional<Contact> findById(Long id);

    List<Contact> findAll();

    boolean update(Contact contact);

    boolean delete(Long id);
}
