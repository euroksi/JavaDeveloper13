package org.example;

import jakarta.transaction.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class TicketCrudService {

    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;

    public TicketCrudService() {
        this.entityManagerFactory = Persistence.createEntityManagerFactory("exampleUnit");
        this.entityManager = entityManagerFactory.createEntityManager();
    }

    @Transactional
    public Ticket saveTicket(Ticket ticket) {
        try {
            entityManager.getTransaction().begin();

            if (ticket.getClient() == null || ticket.getFromPlanet() == null || ticket.getToPlanet() == null) {
                throw new IllegalArgumentException("Client and Planets cannot be null.");
            }

            Client client = ticket.getClient();
            Client existingClient = entityManager.find(Client.class, client.getId());
            if (existingClient == null) {
                throw new IllegalArgumentException("Client with id " + client.getId() + " does not exist.");
            }

            Planet fromPlanet = ticket.getFromPlanet();
            Planet existingFromPlanet = entityManager.find(Planet.class, fromPlanet.getId());
            if (existingFromPlanet == null) {
                throw new IllegalArgumentException("From planet with id " + fromPlanet.getId() + " does not exist.");
            }

            Planet toPlanet = ticket.getToPlanet();
            Planet existingToPlanet = entityManager.find(Planet.class, toPlanet.getId());
            if (existingToPlanet == null) {
                throw new IllegalArgumentException("To planet with id " + toPlanet.getId() + " does not exist.");
            }

            entityManager.persist(ticket);
            entityManager.getTransaction().commit();
            return ticket;
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        }
    }

    public Ticket findTicketById(Long id) {
        return entityManager.find(Ticket.class, id);
    }

    @Transactional
    public Ticket updateTicket(Long id, Client client, Planet fromPlanet, Planet toPlanet) {
        try {
            entityManager.getTransaction().begin();

            Ticket ticket = entityManager.find(Ticket.class, id);
            if (ticket == null) {
                throw new IllegalArgumentException("Ticket with id " + id + " does not exist.");
            }

            ticket.setClient(client);
            ticket.setFromPlanet(fromPlanet);
            ticket.setToPlanet(toPlanet);

            Ticket updatedTicket = entityManager.merge(ticket);
            entityManager.getTransaction().commit();
            return updatedTicket;
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        }
    }

    @Transactional
    public void deleteTicket(Long id) {
        try {
            entityManager.getTransaction().begin();

            Ticket ticket = entityManager.find(Ticket.class, id);
            if (ticket != null) {
                entityManager.remove(ticket);
            }

            entityManager.getTransaction().commit();
        } catch (Exception e) {
            entityManager.getTransaction().rollback();
            throw e;
        }
    }

    public void close() {
        if (entityManager != null) {
            entityManager.close();
        }
        if (entityManagerFactory != null) {
            entityManagerFactory.close();
        }
    }
}
