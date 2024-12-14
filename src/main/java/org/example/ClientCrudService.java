package org.example;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class ClientCrudService {
    public Client saveClient(Client client) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            session.saveOrUpdate(client);
            transaction.commit();
            return client;
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw new RuntimeException("Failed to save client", e);
        } finally {
            session.close();
        }
    }

    public void deleteClient(Long clientId) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            Client client = session.get(Client.class, clientId);
            if (client != null) {
                session.delete(client);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw new RuntimeException("Failed to delete client", e);
        } finally {
            session.close();
        }
    }

    public Client findClientById(Long id) {
        Session session = HibernateUtil.getSession();
        try {
            return session.get(Client.class, id);
        } finally {
            session.close();
        }
    }

    public void updateClient(Long id, String newName) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = null;

        try {
            transaction = session.beginTransaction();
            Client client = session.get(Client.class, id);
            if (client != null) {
                client.setName(newName);
                session.update(client);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            throw new RuntimeException("Failed to update client", e);
        } finally {
            session.close();
        }
    }
}
