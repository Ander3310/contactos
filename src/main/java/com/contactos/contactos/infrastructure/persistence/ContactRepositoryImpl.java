package com.contactos.contactos.infrastructure.persistence;

import com.contactos.contactos.domain.Contact;


import javax.inject.Inject;
import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ContactRepositoryImpl implements ContactRepository {
    private final DataSource dataSource;


    @Inject
    public ContactRepositoryImpl(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public Contact save(Contact contact) {
        String sql = "INSERT INTO contacto (nombre, email, telefono, direccion) VALUES (?, ?, ?, ?)";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {


            ps.setString(1, contact.getNombre());
            ps.setString(2, contact.getEmail());
            ps.setString(3, contact.getTelefono());
            ps.setString(4, contact.getDireccion());


            ps.executeUpdate();


            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    contact.setId(rs.getLong(1));
                }
            }

            return contact;
        } catch (SQLException e) {
            throw new RuntimeException("Error al guardar el contacto", e);
        }
    }

    @Override
    public Optional<Contact> findById(Long id) {
        String sql = "SELECT * FROM contacto WHERE id = ?";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    Contact contact = mapResultSetToContact(rs);
                    return Optional.of(contact);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al buscar el contacto", e);
        }
        return Optional.empty();
    }

    @Override
    public List<Contact> findAll() {
        String sql = "SELECT * FROM contacto";
        List<Contact> contacts = new ArrayList<>();

        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Contact contact = mapResultSetToContact(rs);
                contacts.add(contact);
            }
        } catch (SQLException e) {
            throw new RuntimeException("Error al obtener los contactos", e);
        }
        return contacts;
    }

    @Override
    public boolean update(Contact contact) {
        String sql = "UPDATE contacto SET nombre = ?, email = ?, telefono = ?, direccion = ? WHERE id = ?";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, contact.getNombre());
            ps.setString(2, contact.getEmail());
            ps.setString(3, contact.getTelefono());
            ps.setString(4, contact.getDireccion());
            ps.setLong(5, contact.getId());

            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Error al actualizar el contacto", e);
        }
    }

    @Override
    public boolean delete(Long id) {
        String sql = "DELETE FROM contacto WHERE id = ?";

        try (Connection conn = dataSource.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setLong(1, id);
            return ps.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new RuntimeException("Error al eliminar el contacto", e);
        }
    }


    private Contact mapResultSetToContact(ResultSet rs) throws SQLException {
        return new Contact.Builder()
                .setId(rs.getLong("id"))
                .setNombre(rs.getString("nombre"))
                .setEmail(rs.getString("email"))
                .setTelefono(rs.getString("telefono"))
                .setDireccion(rs.getString("direccion"))
                .build();
    }
}
